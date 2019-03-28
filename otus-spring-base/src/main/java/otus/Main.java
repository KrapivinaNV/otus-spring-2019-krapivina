package otus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import otus.configuration.MainConfiguration;
import otus.service.main.MainQuestionnaire;

public class Main {

	public static void main(String[] args) {
		ApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfiguration.class);
		MainQuestionnaire mainQuestionnaire = annotationConfigApplicationContext.getBean(MainQuestionnaire.class);
		mainQuestionnaire.start();
	}
}
