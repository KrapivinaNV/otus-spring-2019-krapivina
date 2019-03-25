package otus.data.questionnaire;

import java.util.ArrayList;
import java.util.List;

public class Questionnaire {

	private List<QuestionInfo> questionInfos;

	public Questionnaire() {
		this.questionInfos = new ArrayList<>();
	}

	public void addQuestion(QuestionInfo questionInfo) {
		this.questionInfos.add(questionInfo);
	}

	public List<QuestionInfo> getQuestionInfos() {
		return this.questionInfos;
	}
}
