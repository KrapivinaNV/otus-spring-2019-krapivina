package otus.repository;

import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import otus.domain.Author;

@Repository
public class AuthorDaoImpl implements AuthorDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Author getAuthorById(UUID id) {
		return em.find(Author.class, id);
	}

	@Override
	public List<Author> getAllAuthors() {
		return em.createQuery("select b from Author b", Author.class)
				.getResultList();
	}

	@Override
	public UUID addAuthor(String name) {
		Author author = new Author(name);
		em.persist(author);
		return author.getId();
	}
}
