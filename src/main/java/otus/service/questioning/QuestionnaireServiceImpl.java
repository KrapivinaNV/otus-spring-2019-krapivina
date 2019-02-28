package otus.service.questioning;

import java.util.Scanner;
import otus.data.questionnaire.QuestionInfo;
import otus.data.questionnaire.Questionnaire;
import otus.data.scanner.QuestionInputScanner;

public class QuestionnaireServiceImpl implements QuestionnaireService {

	private final Questionnaire questionnaire;

	private final QuestionInputScanner questionInputScanner;

	public QuestionnaireServiceImpl(Questionnaire questionnaire, QuestionInputScanner questionInputScanner) {
		this.questionnaire = questionnaire;
		this.questionInputScanner = questionInputScanner;
	}

	@Override
	public ScoreResults getScore() {
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

	private int getAnswerFromUser(QuestionInfo questionInfo) {
		Scanner scanner = this.questionInputScanner.getScanner();
		while (true) {
			System.out.println("Введите номер ответа : ");
			if (!scanner.hasNextInt()) {
				System.out.println("Номер ответа должен быть числом");
				scanner.next();
				continue;
			}
			int input = scanner.nextInt();
			if (isNumberValid(input, questionInfo.getAnswersCount())) {
				return input;
			}
		}
	}

	private static boolean isAnswerRight(QuestionInfo questionInfo, int answerNumberGot) {
		return questionInfo.checkAnswer(answerNumberGot);
	}

	private static boolean isNumberValid(int answer, int maxAnswersCountQuestion) {
		if (answer > maxAnswersCountQuestion || answer < 1) {
			System.out.println("Номер ответа должен быть в диапазоне от 1 до " + maxAnswersCountQuestion);
			return false;
		}
		return true;
	}

}
