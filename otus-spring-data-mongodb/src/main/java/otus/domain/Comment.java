package otus.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Comment {

	@Id
	private String id;

	private String text;

	private Book book;

	public Comment() {
	}

	public Comment(String text, Book book) {
		this.text = text;
		this.book = book;
	}

	@Override
	public String toString() {
		return "Comment{" +
				"id=" + id +
				", text='" + text + '\'' +
				'}';
	}

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

}
