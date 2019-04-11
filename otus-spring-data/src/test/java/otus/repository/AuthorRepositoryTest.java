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
import otus.domain.Author;
import otus.repository.AuthorRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AuthorRepositoryTest {

	@Autowired
	private TestEntityManager em;

	@Autowired
	private AuthorRepository authorRepository;

	@Test
	public void getAuthorTest() {
		Author author = em.persist(new Author("author_1"));
		UUID authorId = author.getId();
		assertEquals(author, authorRepository.findById(authorId).get());
	}

	@Test
	public void getAllAuthorsTest() {
		Author author = em.persist(new Author("author_2"));
		UUID authorId = author.getId();
		ImmutableSet<Author> authors = ImmutableSet.copyOf(authorRepository.findAllById(ImmutableSet.of(authorId)));
		assertTrue(authors.contains(author));
	}

	@Test
	public void saveAuthorTest() {
		Author author = new Author("author_3");
		Author savedAuthor = authorRepository.save(author);
		Author findAuthor = em.find(Author.class, savedAuthor.getId());
		assertEquals(savedAuthor, findAuthor);
	}

}
