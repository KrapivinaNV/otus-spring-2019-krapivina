package otus.domain;

import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Book {

	@Id
	private String id;

	private String name;

	@DBRef
	private Set<Author> authors = new HashSet<>();

	@DBRef
	private Set<Genre> genres = new HashSet<>();

	public Book(String name, Set<Author> authors, Set<Genre> genres) {
		this.name = name;
		this.authors = authors;
		this.genres = genres;
	}

	public Book() {
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}


	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", name='" + name + '\'' +
				", authors=" + authors +
				", genres=" + genres +
				"}\n";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Book book = (Book) o;

		if (!id.equals(book.id)) {
			return false;
		}
		if (!name.equals(book.name)) {
			return false;
		}
		if (!authors.equals(book.authors)) {
			return false;
		}
		return genres.equals(book.genres);
	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + name.hashCode();
		result = 31 * result + authors.hashCode();
		result = 31 * result + genres.hashCode();
		return result;
	}
}
