package otus.service.questioning;

import otus.data.questionnaire.QuestionInfo;
import otus.data.questionnaire.Questionnaire;
import otus.data.scanner.Reader;
import otus.service.localization.MessageResolver;

public class QuestionnaireServiceImpl implements QuestionnaireService {

	private final Questionnaire questionnaire;

	private final Reader reader;

	private final MessageResolver messageResolver;

	public QuestionnaireServiceImpl(Questionnaire questionnaire, Reader reader,
			MessageResolver messageResolver) {
		this.questionnaire = questionnaire;
		this.reader = reader;
		this.messageResolver = messageResolver;
	}

	private static boolean isAnswerRight(QuestionInfo questionInfo, Integer answerNumberGot) {
		return questionInfo.checkAnswer(answerNumberGot);
	}

	@Override
	public ScoreResults processAndGetScore() {
		return new ScoreResults(
				(int) questionnaire.getQuestionInfos().stream()
						.filter(this::isAnswerRight)
						.count(),
				questionnaire.getQuestionInfos().size()
		);
	}

	private boolean isAnswerRight(QuestionInfo questionInfo) {
		System.out.println(questionInfo);
		return isAnswerRight(questionInfo, getAnswerFromUser(questionInfo));
	}

	private Integer getAnswerFromUser(QuestionInfo questionInfo) {
		while (true) {
			System.out.println(messageResolver.getMessage("text.answer"));
			Integer input;
			try {
				input = Integer.valueOf(reader.readLine());
				if (isNumberValid(input, questionInfo.getAnswersCount())) {
					return input;
				}
			} catch (NumberFormatException e) {
				System.out.println(messageResolver.getMessage("text.error.number"));
			}
		}
	}

	private boolean isNumberValid(int answer, int maxAnswersCountQuestion) {
		if (answer > maxAnswersCountQuestion || answer < 1) {
			System.out.println(messageResolver
					.getMessage("text.error.range", new String[]{String.valueOf(maxAnswersCountQuestion)}));
			return false;
		}
		return true;
	}

}
