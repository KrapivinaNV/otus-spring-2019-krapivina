package otus;

import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class LibraryMain {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(LibraryMain.class, args);
	}
}
