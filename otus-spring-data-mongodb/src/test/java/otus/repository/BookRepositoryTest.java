package otus.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import otus.domain.Book;

@DataMongoTest
@RunWith(SpringRunner.class)
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private MongoTemplate mongoTemplate;


	@Test
	public void getBookTest() {
		Book book = mongoTemplate.save(new Book("book_1", ImmutableSet.of(), ImmutableSet.of()));
		String bookId = book.getId();
		assertEquals(book, bookRepository.findById(bookId).get());
	}

	@Test
	public void getAllBooksTest() {
		Book book = mongoTemplate.save(new Book("book_2", ImmutableSet.of(), ImmutableSet.of()));
		String id = book.getId();
		ImmutableSet<Book> books = ImmutableSet.copyOf(bookRepository.findAllById(ImmutableSet.of(id)));
		assertTrue(books.contains(book));
	}

	@Test
	public void saveBookTest() {
		Book book = mongoTemplate.save(new Book("book_3", ImmutableSet.of(), ImmutableSet.of()));
		Book savedBook = bookRepository.save(book);
		Book findBook = mongoTemplate.findById(savedBook.getId(), Book.class);
		assertEquals(savedBook, findBook);
	}
}
