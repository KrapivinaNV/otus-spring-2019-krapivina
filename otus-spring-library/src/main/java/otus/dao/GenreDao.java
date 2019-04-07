package otus.dao;

import java.util.List;
import java.util.UUID;
import otus.domain.Genre;

public interface GenreDao {

	//TODO: Not implemented yet
	Genre getGenreById(UUID id);

	List<Genre> getAllGenres();
}
