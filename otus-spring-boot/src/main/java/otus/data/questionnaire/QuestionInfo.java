package otus.data.questionnaire;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class QuestionInfo {

	private final Map<Integer, Answer> answers;
	private final String text;

	public QuestionInfo(String text, List<Answer> answers) {
		this.text = text;
		this.answers = new HashMap<>();
		answers.forEach(this::addAnswer);
	}

	private void addAnswer(Answer answer) {
		this.answers.put(this.answers.size() + 1, answer);
	}

	public Integer getAnswersCount() {
		return this.answers.size();
	}

	public boolean checkAnswer(Integer answerNumber){
		return this.answers.get(answerNumber).isCorrect();
	}

	@Override
	public String toString() {
		return text +
				" " +
				answers;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		QuestionInfo that = (QuestionInfo) o;
		return Objects.equals(answers, that.answers) &&
				Objects.equals(text, that.text);
	}

	@Override
	public int hashCode() {
		return Objects.hash(answers, text);
	}
}
