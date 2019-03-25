package otus.data.questionnaire;


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
}
