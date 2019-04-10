package otus.dao;

import java.util.List;
import java.util.UUID;
import otus.domain.Author;

public interface AuthorDao {

	Author getAuthorById(UUID id);

	List<Author> getAllAuthors();

	UUID addAuthor(String name);
}
