package otus.service.main;

import otus.service.person.PersonService;
import otus.service.printer.PrinterService;
import otus.service.questioning.QuestionnaireService;

public class MainQuestionnaireImpl implements MainQuestionnaire {

	private final PersonService personService;

	private final QuestionnaireService questionnaireService;

	private final PrinterService printerService;

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
	public void start() {
		printerService.print(
				personService.inputPersonData(),
				questionnaireService.getScore()
		);
	}
}
