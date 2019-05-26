package otus.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import otus.domain.Author;

@Repository
public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

}
