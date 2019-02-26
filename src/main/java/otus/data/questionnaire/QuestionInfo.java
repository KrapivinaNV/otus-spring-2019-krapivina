package otus.data.questionnaire;

import java.util.HashMap;
import java.util.Map;

public class QuestionInfo {

	private final Map<Integer, Answer> answers;
	private final String text;

	public QuestionInfo(String text) {
		this.text = text;
		this.answers = new HashMap<>();
	}

	public void addAnswer(Answer answer) {
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
				"\nВарианты ответов :\n" +
				answers;
	}
}
