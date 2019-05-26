package otus.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Genre {

	@Id
	private String id;

	private String name;

	public Genre(String name) {
		this.name = name;
	}

	public Genre() {
	}

	public String getId() {
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
}
