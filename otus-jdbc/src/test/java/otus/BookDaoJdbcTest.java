package otus;


import com.google.common.collect.ImmutableList;
import java.util.UUID;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import otus.repository.BookDao;
import otus.repository.BookDaoJdbc;

@RunWith(SpringRunner.class)
@JdbcTest
@Import(BookDaoJdbc.class)
public class BookDaoJdbcTest {

	@Autowired
	private BookDao bookDao;

	@Test
	public void addAndGetBookTest() {
		UUID bookId = bookDao.addBook(
				"Test",
				ImmutableList.of(UUID.fromString("00000000-0000-0000-0000-000000000001")),
				ImmutableList.of(UUID.fromString("00000000-0000-0000-0000-000000000001"))
		);

		Assert.assertEquals(bookId, bookDao.getBookById(bookId).getId());
	}

}
