package otus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import otus.dao.AuthorDao;
import otus.dao.AuthorDaoImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import({AuthorDaoImpl.class})
public class AuthorDaoTest {

	@Autowired
	AuthorDao authorDao;

	@Test
	public void addAndGetAuthorTest() {
		UUID authorId = authorDao.addAuthor("new_author_1");
		assertEquals(authorId, authorDao.getAuthorById(authorId).getId());
	}

	@Test
	public void addAndGetAllAuthorTest() {
		UUID authorId = authorDao.addAuthor("new_author_2");
		assertTrue(authorDao.getAllAuthors().stream()
				.anyMatch(author -> author.getId().equals(authorId)));
	}

}
