package otus.domain;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Genre {

	@Id
	@GeneratedValue
	private UUID id;

	private String name;

	public Genre(String name) {
		this.name = name;
	}

	public Genre() {
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Genre{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Genre genre = (Genre) o;

		if (!id.equals(genre.id)) {
			return false;
		}
		return name.equals(genre.name);
	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + name.hashCode();
		return result;
	}
}
