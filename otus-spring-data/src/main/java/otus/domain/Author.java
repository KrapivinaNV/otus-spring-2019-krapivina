package otus.domain;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Author {

	@Id
	@GeneratedValue
	private UUID id;

	private String fullName;

	public Author(String fullName) {
		this.fullName = fullName;
	}

	public Author() {
	}


	public UUID getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	@Override
	public String toString() {
		return "Author{" +
				"id=" + id +
				", fullName='" + fullName + '\'' +
				'}';
	}
}
