package otus.service.person;

import java.util.Scanner;
import otus.data.person.Person;
import otus.data.scanner.QuestionInputScanner;
import otus.service.localization.MessageResolver;

public class PersonServiceImpl implements PersonService {

	private final QuestionInputScanner questionInputScanner;

	private final MessageResolver messageResolver;

	public PersonServiceImpl(QuestionInputScanner questionInputScanner, MessageResolver messageResolver) {
		this.questionInputScanner = questionInputScanner;
		this.messageResolver = messageResolver;
	}

	@Override
	public Person inputPersonData() {
		Scanner scanner = this.questionInputScanner.getScanner();
		System.out.println(messageResolver.getMessage("text.fname"));
		String firstName = scanner.nextLine();
		System.out.println(messageResolver.getMessage("text.sname"));
		String lastName = scanner.nextLine();

		return new Person(firstName, lastName);
	}

}
