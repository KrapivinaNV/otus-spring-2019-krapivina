package otus.service.printer;

import otus.data.person.Person;
import otus.service.localization.MessageResolver;
import otus.service.questioning.ScoreResults;

public class PrinterQuestionnaireService implements PrinterService {

	private final MessageResolver messageResolver;

	public PrinterQuestionnaireService(MessageResolver messageResolver) {
		this.messageResolver = messageResolver;
	}

	@Override
	public void print(Person person, ScoreResults scoreResults) {
		System.out.println(messageResolver.getMessage("text.result", new String[]{
				String.valueOf(person),
				String.valueOf(scoreResults.getCorrectCount()),
				String.valueOf(scoreResults.getSize())}
		));
	}
}
