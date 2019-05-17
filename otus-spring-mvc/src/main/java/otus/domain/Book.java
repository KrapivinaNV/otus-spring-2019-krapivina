package otus.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Book {

	@Id
	@GeneratedValue
	private UUID id;

	private String name;

	@Fetch(FetchMode.SUBSELECT)
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	@JoinTable(
			name = "BOOK_AUTHOR",
			joinColumns = {@JoinColumn(name = "id_book")},
			inverseJoinColumns = {@JoinColumn(name = "id_author")}
	)
	private Set<Author> authors = new HashSet<>();

	@Fetch(FetchMode.SUBSELECT)
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	@JoinTable(
			name = "BOOK_GENRE",
			joinColumns = {@JoinColumn(name = "id_book")},
			inverseJoinColumns = {@JoinColumn(name = "id_genre")}
	)
	private Set<Genre> genres = new HashSet<>();

	public Book(String name, Set<Author> authors, Set<Genre> genres) {
		this.name = name;
		this.authors = authors;
		this.genres = genres;
	}

	public Book() {
	}

	public UUID getId() {
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

}
