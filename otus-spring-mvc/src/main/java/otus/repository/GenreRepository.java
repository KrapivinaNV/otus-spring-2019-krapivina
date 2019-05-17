package otus.repository;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import otus.domain.Genre;

@Repository
public interface GenreRepository extends CrudRepository<Genre, UUID> {

}
