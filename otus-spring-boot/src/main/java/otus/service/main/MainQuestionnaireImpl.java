package otus.service.main;

import otus.data.person.Person;
import otus.service.person.PersonService;
import otus.service.printer.PrinterService;
import otus.service.questioning.QuestionnaireService;
import otus.service.questioning.ScoreResults;

public class MainQuestionnaireImpl implements MainQuestionnaire {

	private final PersonService personService;

	private final QuestionnaireService questionnaireService;

	private final PrinterService printerService;

	private Person person;

	private ScoreResults results;


	public MainQuestionnaireImpl(
			PersonService personService,
			QuestionnaireService questionnaireService,
			PrinterService printerService
	) {
		this.personService = personService;
		this.questionnaireService = questionnaireService;
		this.printerService = printerService;
	}


	@Override
	public void login() {
		this.person = personService.inputPersonData();
	}

	@Override
	public void test() {
		this.results = questionnaireService.processAndGetScore();
	}

	@Override
	public void result() {
		printerService.print(this.person, this.results);
	}
}
