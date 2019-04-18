package otus.domain;

import java.util.UUID;


public class Author {

	private UUID id;

	private String fullName;

	public Author(UUID id, String fullName) {
		this.id = id;
		this.fullName = fullName;
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
