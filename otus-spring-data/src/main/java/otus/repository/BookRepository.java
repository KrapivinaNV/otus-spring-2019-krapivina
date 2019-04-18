package otus.repository;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import otus.domain.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, UUID> {

}
