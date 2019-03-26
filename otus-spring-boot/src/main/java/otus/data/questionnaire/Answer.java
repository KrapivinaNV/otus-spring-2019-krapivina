package otus.data.questionnaire;


import java.util.Objects;

public class Answer {

	private boolean isCorrect;

	private String text;

	public Answer(String text, boolean isCorrect) {
		this.isCorrect = isCorrect;
		this.text = text;
	}

	public boolean isCorrect() {
		return this.isCorrect;
	}

	@Override
	public String toString() {
		return text;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Answer answer = (Answer) o;
		return isCorrect == answer.isCorrect &&
				Objects.equals(text, answer.text);
	}

	@Override
	public int hashCode() {
		return Objects.hash(isCorrect, text);
	}
}
