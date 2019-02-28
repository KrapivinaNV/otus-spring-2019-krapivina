package otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import otus.service.main.MainQuestionnaire;


public class Main {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
		MainQuestionnaire mainQuestionnaire = context.getBean(MainQuestionnaire.class);
		mainQuestionnaire.start();
	}
}
