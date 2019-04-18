package otus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.google.common.collect.ImmutableSet;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import otus.repository.BookDao;
import otus.repository.BookDaoImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import({BookDaoImpl.class})
public class BookDaoTest {

	@Autowired
	BookDao bookDao;

	@Test
	public void addAndGetBookTest() {
		UUID bookId = bookDao.addBook(
				"Test_1",
				ImmutableSet.of(UUID.fromString("00000000-0000-0000-0000-000000000001")),
				ImmutableSet.of(UUID.fromString("00000000-0000-0000-0000-000000000001"))
		);
		assertEquals(bookId, bookDao.getBookById(bookId).getId());
	}

	@Test
	public void addAndGetAllBookTest() {
		UUID bookId = bookDao.addBook(
				"Test_2",
				ImmutableSet.of(UUID.fromString("00000000-0000-0000-0000-000000000002")),
				ImmutableSet.of(UUID.fromString("00000000-0000-0000-0000-000000000002"))
		);
		assertTrue(bookDao.getAllBooks().stream()
				.anyMatch(book -> book.getId().equals(bookId)));
	}

	@Test
	public void addNewCommentTest() {
		UUID bookId = UUID.fromString("00000000-0000-0000-0000-000000000003");
		UUID commentId = bookDao.addComment(bookId, "test_comment");
		assertTrue(bookDao.getBookById(bookId).getComments().stream()
				.anyMatch(comment -> comment.getId().equals(commentId)));
	}
}
