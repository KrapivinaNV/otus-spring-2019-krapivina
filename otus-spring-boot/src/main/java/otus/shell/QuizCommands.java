package otus.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import otus.service.main.MainQuestionnaire;

@ShellComponent
public class QuizCommands {

	private final MainQuestionnaire mainQuestionnaire;

	private boolean isLogin;

	private boolean isTest;

	@Autowired
	public QuizCommands(MainQuestionnaire mainQuestionnaire) {
		this.mainQuestionnaire = mainQuestionnaire;
	}

	@ShellMethod("login")
	public void login() {
		mainQuestionnaire.login();
		this.isLogin = true;
	}

	@ShellMethodAvailability("testAvailability")
	@ShellMethod("test")
	public void test() {
		mainQuestionnaire.test();
		this.isTest = true;
	}

	@ShellMethodAvailability("resultAvailability")
	@ShellMethod("result")
	public void result() {
		mainQuestionnaire.result();
	}

	@SuppressWarnings("unused")
	private Availability testAvailability() {
		return this.isLogin ? Availability.available() : Availability.unavailable("Try 'login' for start");
	}

	@SuppressWarnings("unused")
	private Availability resultAvailability() {
		return this.isLogin && this.isTest
				? Availability.available()
				: Availability.unavailable("You need 'login' and 'test' for start");
	}

}
