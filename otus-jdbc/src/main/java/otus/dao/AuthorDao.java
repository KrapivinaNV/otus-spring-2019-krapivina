package otus.repository;

import java.util.List;
import java.util.UUID;
import otus.domain.Author;

public interface AuthorDao {

	//TODO: Not implemented yet
	Author getAuthorById(UUID id);

	List<Author> getAllAuthors();
}
