package otus.dao;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import otus.domain.Author;
import otus.domain.Book;
import otus.domain.Comment;
import otus.domain.Genre;

@Repository
public class BookDaoImpl implements BookDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Book getBookById(UUID id) {
		return em.find(Book.class, id);
	}

	@Override
	public List<Book> getAllBooks() {
		return em.createQuery("select b from Book b", Book.class)
				.getResultList();
	}

	@Override
	public UUID addBook(String name, Set<UUID> authors, Set<UUID> genres) {
		Set<Author> authorList = authors.stream().map(id_author -> em.find(Author.class, id_author))
				.collect(Collectors.toSet());
		Set<Genre> genreList = genres.stream().map(id_genre -> em.find(Genre.class, id_genre))
				.collect(Collectors.toSet());

		Book book = new Book(name, authorList, genreList);
		em.persist(book);
		return book.getId();
	}

	@Override
	public UUID addComment(UUID bookId, String textComment) {
		Book book = em.find(Book.class, bookId);
		Comment comment = new Comment(textComment, book);
		em.persist(comment);
		book.getComments().add(comment);
		return comment.getId();
	}
}
