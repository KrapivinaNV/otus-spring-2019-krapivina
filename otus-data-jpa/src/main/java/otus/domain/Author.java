package otus.domain;

import java.util.List;
import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import org.springframework.beans.factory.annotation.Value;

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
