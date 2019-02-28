package otus.service.printer;

import otus.data.person.Person;
import otus.service.questioning.ScoreResults;

public class PrinterQuestionnaireService implements PrinterService {

	@Override
	public void print(Person person, ScoreResults scoreResults) {
		System.out.println(
				"Результат:: " +
						person +
						" ответил(а) верно на " +
						scoreResults.getCorrectCount() +
						" вопрос(а) из " +
						scoreResults.getSize());
	}
}
