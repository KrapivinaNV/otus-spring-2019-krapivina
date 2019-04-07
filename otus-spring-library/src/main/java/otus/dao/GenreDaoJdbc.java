package otus.dao;

import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import otus.domain.Genre;
import otus.mapper.GenreMapper;

@Repository
public class GenreDaoJdbc implements GenreDao {

	private final NamedParameterJdbcOperations jdbc;

	@Autowired
	public GenreDaoJdbc(NamedParameterJdbcOperations jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Genre getGenreById(UUID id) {
		ImmutableMap<String, ?> genreParams = ImmutableMap.of("id", id);
		return jdbc.queryForObject("select * from genre where id = :id", genreParams, new GenreMapper());
	}

	@Override
	public List<Genre> getAllGenres() {
		return jdbc.query("select * from genre", new GenreMapper());
	}
}
