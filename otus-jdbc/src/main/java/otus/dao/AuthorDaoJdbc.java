package otus.repository;

import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import otus.domain.Author;
import otus.mapper.AuthorMapper;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

	private final NamedParameterJdbcOperations jdbc;

	@Autowired
	public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Author getAuthorById(UUID id) {
		ImmutableMap<String, ?> authorParams = ImmutableMap.of("id", id);
		return jdbc.queryForObject("select * from author where id = :id", authorParams, new AuthorMapper());
	}

	@Override
	public List<Author> getAllAuthors() {
		return jdbc.query("select * from author", new AuthorMapper());
	}
}
