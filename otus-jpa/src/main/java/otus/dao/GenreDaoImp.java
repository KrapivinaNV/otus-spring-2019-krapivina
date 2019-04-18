package otus.repository;

import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import otus.domain.Genre;

@Repository
public class GenreDaoImp implements GenreDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Genre getGenreById(UUID id) {
		return em.find(Genre.class, id);
	}

	@Override
	public List<Genre> getAllGenres() {
		return em.createQuery("select b from Genre b", Genre.class)
				.getResultList();
	}

	@Override
	public UUID addGenre(String name) {
		Genre genre = new Genre(name);
		em.persist(genre);
		return genre.getId();
	}
}
