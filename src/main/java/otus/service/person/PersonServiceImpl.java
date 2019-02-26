package otus.service.person;

import java.util.Scanner;
import otus.data.person.Person;
import otus.data.scanner.QuestionInputScanner;

public class PersonServiceImpl implements PersonService {

	private final QuestionInputScanner questionInputScanner;

	public PersonServiceImpl(QuestionInputScanner questionInputScanner) {
		this.questionInputScanner = questionInputScanner;
	}

	@Override
	public Person inputPersonData() {
		Scanner scanner = this.questionInputScanner.getScanner();
		System.out.println("Введите имя : ");
		String firstName = scanner.nextLine();
		System.out.println("Введите фамилию : ");
		String lastName = scanner.nextLine();

		return new Person(firstName, lastName);
	}

}
