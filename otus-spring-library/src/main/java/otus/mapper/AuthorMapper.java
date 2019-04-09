package otus.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.springframework.jdbc.core.RowMapper;
import otus.domain.Author;

public class AuthorMapper implements RowMapper<Author> {

	@Override
	public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
		UUID id = rs.getObject("id", UUID.class);
		String fullName = rs.getString("fullName");
		return new Author(id, fullName);
	}
}
