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
import otus.domain.Genre;
import otus.repository.GenreRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GenreRepositoryTest {

	@Autowired
	private TestEntityManager em;

	@Autowired
	private GenreRepository genreRepository;

	@Test
	public void getGenreTest() {
		Genre genre = em.persist(new Genre("genre_1"));
		UUID genreId = genre.getId();
		assertEquals(genre, genreRepository.findById(genreId).get());
	}

	@Test
	public void getAllGenresTest() {
		Genre genre = em.persist(new Genre("genre_2"));
		UUID genreId = genre.getId();
		ImmutableSet<Genre> genres = ImmutableSet.copyOf(genreRepository.findAllById(ImmutableSet.of(genreId)));
		assertTrue(genres.contains(genre));
	}

	@Test
	public void saveGenreTest() {
		Genre genre = em.persist(new Genre("genre_3"));
		Genre savedGenre = genreRepository.save(genre);
		Genre findGenre = em.find(Genre.class, savedGenre.getId());
		assertEquals(savedGenre, findGenre);
	}
}
