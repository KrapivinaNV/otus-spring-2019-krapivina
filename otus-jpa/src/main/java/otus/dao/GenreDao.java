package otus.repository;

import java.util.List;
import java.util.UUID;
import otus.domain.Genre;

public interface GenreDao {

	Genre getGenreById(UUID id);

	List<Genre> getAllGenres();

	UUID addGenre(String name);
}
