package otus.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.google.common.collect.ImmutableSet;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import otus.domain.Book;
import otus.repository.BookRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private TestEntityManager em;

	@Autowired
	private BookRepository bookRepository;

	@Test
	public void getBookTest() {
		Book book = em.persist(new Book("book_1", ImmutableSet.of(), ImmutableSet.of()));
		UUID bookId = book.getId();
		assertEquals(book, bookRepository.findById(bookId).get());
	}

	@Test
	public void getAllBooksTest() {
		Book book = em.persist(new Book("book_2", ImmutableSet.of(), ImmutableSet.of()));
		UUID bookId = book.getId();
		ImmutableSet<Book> books = ImmutableSet.copyOf(bookRepository.findAllById(ImmutableSet.of(bookId)));
		assertTrue(books.contains(book));
	}

	@Test
	public void saveBookTest() {
		Book book = em.persist(new Book("book_3", ImmutableSet.of(), ImmutableSet.of()));
		Book savedBook = bookRepository.save(book);
		Book findBook = em.find(Book.class, savedBook.getId());
		assertEquals(savedBook, findBook);
	}

}
