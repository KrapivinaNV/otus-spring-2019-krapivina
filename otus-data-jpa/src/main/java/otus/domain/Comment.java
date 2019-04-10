package otus.domain;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	private UUID id;

	private String text;

	@ManyToOne
	@JoinColumn(name = "ID_BOOK")
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

	public UUID getId() {
		return id;
	}

	public String getText() {
		return text;
	}

}
