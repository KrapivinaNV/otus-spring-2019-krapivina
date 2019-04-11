package otus.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import otus.domain.Author;
import otus.domain.Book;
import otus.domain.Genre;

public class BookMapExtractor implements ResultSetExtractor<List<Book>> {

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<UUID, Map<UUID, String>> booksAuthors = new HashMap<>();
		Map<UUID, Map<UUID, String>> booksGenres = new HashMap<>();
		Map<UUID, String> books = new HashMap<>();
		while (rs.next()) {
			UUID id = rs.getObject("book.id", UUID.class);
			String name = rs.getString("book.name");
			books.put(id, name);

			UUID authorId = rs.getObject("author.id", UUID.class);
			String authorFullName = rs.getString("author.full_name");
			Map<UUID, String> authorsMap = booksAuthors.computeIfAbsent(id, k -> new HashMap<>());
			authorsMap.put(authorId, authorFullName);

			UUID genreId = rs.getObject("genre.id", UUID.class);
			String genreName = rs.getString("genre.name");
			Map<UUID, String> genresMap = booksGenres.computeIfAbsent(id, k -> new HashMap<>());
			genresMap.put(genreId, genreName);
		}
		return books.entrySet().stream().map(
				book ->
						new Book(
								book.getKey(),
								book.getValue(),
								(booksAuthors.get(book.getKey()).entrySet().stream()
										.map(author -> new Author(author.getKey(), author.getValue())))
										.collect(Collectors.toList()),
								(booksGenres.get(book.getKey()).entrySet().stream()
										.map(genre -> new Genre(genre.getKey(), genre.getValue())))
										.collect(Collectors.toList())
						))
				.collect(Collectors.toList());
	}

}
