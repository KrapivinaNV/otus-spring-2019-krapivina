package otus.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import otus.service.main.MainQuestionnaire;

@ShellComponent
public class QuizCommands {

	private final MainQuestionnaire mainQuestionnaire;

	@Autowired
	public QuizCommands(MainQuestionnaire mainQuestionnaire) {
		this.mainQuestionnaire = mainQuestionnaire;
	}

	@ShellMethod("Start quiz")
	public void start() {
		mainQuestionnaire.start();
	}

}
