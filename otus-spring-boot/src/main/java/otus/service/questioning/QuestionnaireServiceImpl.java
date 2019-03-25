package otus.service.questioning;

import java.util.Scanner;
import otus.data.questionnaire.QuestionInfo;
import otus.data.questionnaire.Questionnaire;
import otus.data.scanner.QuestionInputScanner;
import otus.service.localization.MessageResolver;
import otus.service.parser.CsvParser;

public class QuestionnaireServiceImpl implements QuestionnaireService {

	private final Questionnaire questionnaire;

	private final QuestionInputScanner questionInputScanner;

	private final MessageResolver messageResolver;

	public QuestionnaireServiceImpl(CsvParser csvParser, QuestionInputScanner questionInputScanner,
			MessageResolver messageResolver) {
		this.questionnaire = csvParser.parse();
		this.questionInputScanner = questionInputScanner;
		this.messageResolver = messageResolver;
	}

	private static boolean isAnswerRight(QuestionInfo questionInfo, int answerNumberGot) {
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

	private int getAnswerFromUser(QuestionInfo questionInfo) {
		Scanner scanner = this.questionInputScanner.getScanner();
		while (true) {
			System.out.println(messageResolver.getMessage("text.answer"));
			if (!scanner.hasNextInt()) {
				System.out.println(messageResolver.getMessage("text.error.number"));
				scanner.next();
				continue;
			}
			int input = scanner.nextInt();
			if (isNumberValid(input, questionInfo.getAnswersCount())) {
				return input;
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
