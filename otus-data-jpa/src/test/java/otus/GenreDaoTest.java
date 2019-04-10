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
import otus.dao.GenreDao;
import otus.dao.GenreDaoImp;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import({GenreDaoImp.class})
public class GenreDaoTest {

	@Autowired
	GenreDao genreDao;

	@Test
	public void addAndGetAuthorTest() {
		UUID genreId = genreDao.addGenre("new_genre_1");
		assertEquals(genreId, genreDao.getGenreById(genreId).getId());
	}

	@Test
	public void addAndGetAllAuthorTest() {
		UUID genreId = genreDao.addGenre("new_genre_2");
		assertTrue(genreDao.getAllGenres().stream()
				.anyMatch(genre -> genre.getId().equals(genreId)));
	}
}
