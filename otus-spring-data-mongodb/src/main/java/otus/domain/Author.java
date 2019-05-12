package otus.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Author {

	@Id
	private String id;

	private String fullName;

	public Author(String fullName) {
		this.fullName = fullName;
	}

	public Author() {
	}


	public String getId() {
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
