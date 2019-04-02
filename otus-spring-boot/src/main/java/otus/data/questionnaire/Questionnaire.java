package otus.data.questionnaire;

import java.util.List;

public class Questionnaire {

	private final List<QuestionInfo> questionInfos;

	public Questionnaire(List<QuestionInfo> questionInfos) {
		this.questionInfos = questionInfos;
	}

	public List<QuestionInfo> getQuestionInfos() {
		return this.questionInfos;
	}
}
