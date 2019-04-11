package otus.domain;

import java.util.List;
import java.util.UUID;

public class Book {

	private UUID id;

	private String name;

	private List<Author> authors;

	private List<Genre> genres;

	public Book(UUID id, String name, List<Author> authors, List<Genre> genres) {
		this.id = id;
		this.name = name;
		this.authors = authors;
		this.genres = genres;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public List<Genre> getGenres() {
		return genres;
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
}
