package otus.repository;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import otus.domain.Genre;

@Repository
public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {

}
