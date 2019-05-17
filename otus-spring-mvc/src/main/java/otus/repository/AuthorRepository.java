package otus.repository;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import otus.domain.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, UUID> {

}
