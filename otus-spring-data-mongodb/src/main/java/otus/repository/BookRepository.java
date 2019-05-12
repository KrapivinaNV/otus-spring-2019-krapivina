package otus.repository;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import otus.domain.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

}
