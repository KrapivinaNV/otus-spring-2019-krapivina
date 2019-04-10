package otus.dao;

import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import otus.domain.Book;
import otus.mapper.BookMapExtractor;

@Repository
public class BookDaoJdbc implements BookDao {


	private final NamedParameterJdbcOperations jdbc;

	@Autowired
	public BookDaoJdbc(NamedParameterJdbcOperations jdbc) {

		this.jdbc = jdbc;
	}

	@Override
	public Book getBookById(UUID id) {
		ImmutableMap<String, ?> bookParams = ImmutableMap.of("id", id);
		List<Book> books = jdbc.query("select book.id, book.name, author.id, author.full_name, genre.id, genre.name\n"
				+ " from book\n"
				+ " left join book_author on book.id = book_author.id_book\n"
				+ " left join author on author.id = book_author.id_author\n"
				+ " left join book_genre on book.id = book_genre.id_book\n"
				+ " left join genre on genre.id = book_genre.id_genre"
				+ " where book.id = :id", bookParams, new BookMapExtractor());
		if (books == null || books.isEmpty()) {
			throw new IllegalArgumentException();
		}
		return books.get(0);
	}

	@Override
	public List<Book> getAllBooks() {
		return jdbc.query("select book.id, book.name, author.id, author.full_name, genre.id, genre.name\n"
				+ " from book\n"
				+ " left join book_author on book.id = book_author.id_book\n"
				+ " left join author on author.id = book_author.id_author\n"
				+ " left join book_genre on book.id = book_genre.id_book\n"
				+ " left join genre on genre.id = book_genre.id_genre", new BookMapExtractor());

	}

	@Override
	public UUID addBook(String name, List<UUID> authors, List<UUID> genres) {
		UUID bookId = UUID.randomUUID();
		ImmutableMap<String, ?> bookParams = ImmutableMap.of("id", bookId, "name", name);
		jdbc.update("insert into book (id, name) values (:id, :name)", bookParams);

		authors.forEach(author -> {
			ImmutableMap<String, ?> authorParams = ImmutableMap.of("id_book", bookId, "id_author", author);
			jdbc.update("insert into book_author (id_book, id_author) values (:id_book, :id_author)", authorParams);
		});

		genres.forEach(genre -> {
			ImmutableMap<String, ?> genreParams = ImmutableMap.of("id_book", bookId, "id_genre", genre);
			jdbc.update("insert into book_genre (id_book, id_genre) values (:id_book, :id_genre)", genreParams);
		});

		return bookId;
	}
}
