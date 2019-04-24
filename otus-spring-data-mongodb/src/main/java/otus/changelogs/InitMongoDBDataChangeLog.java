package otus.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.google.common.collect.ImmutableSet;
import com.mongodb.client.MongoDatabase;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.mongodb.core.MongoTemplate;
import otus.domain.Author;
import otus.domain.Book;
import otus.domain.Genre;

@ChangeLog
public class InitMongoDBDataChangeLog {

	private Set<Author> authors = new HashSet<>();

	private Set<Genre> genres = new HashSet<>();

	@ChangeSet(order = "000", id = "dropDB", author = "nkrapivina", runAlways = true)
	public void dropDB(MongoDatabase database){
		database.drop();
	}

	@ChangeSet(order = "001", id = "initGenres", author = "nkrapivina", runAlways = true)
	public void initGenres(MongoTemplate template) {
		for (int i = 1; i <= 2; i++) {
			Genre genre = new Genre("Genre" + i);
			genres.add(genre);
			template.save(genre);
		}
	}

	@ChangeSet(order = "002", id = "initAuthors", author = "nkrapivina", runAlways = true)
	public void initAuthors(MongoTemplate template) {
		for (int i = 1; i <= 2; i++) {
			Author author = new Author("Author" + i);
			authors.add(author);
			template.save(author);
		}
	}

	@ChangeSet(order = "003", id = "initBook", author = "nkrapivina", runAlways = true)
	public void initBook(MongoTemplate template) {
		for (int i = 1; i <= 2; i++) {
			Book book = new Book("book" + i, ImmutableSet.copyOf(authors), ImmutableSet.copyOf(genres));
			template.save(book);
		}
	}

}
