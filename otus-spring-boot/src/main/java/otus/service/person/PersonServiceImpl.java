package otus.service.person;

import otus.data.person.Person;
import otus.data.scanner.Reader;
import otus.service.localization.MessageResolver;

public class PersonServiceImpl implements PersonService {

	private final Reader reader;

	private final MessageResolver messageResolver;

	public PersonServiceImpl(Reader questionInputReader, MessageResolver messageResolver) {
		this.reader = questionInputReader;
		this.messageResolver = messageResolver;
	}

	@Override
	public Person inputPersonData() {
		System.out.println(messageResolver.getMessage("text.fname"));
		String firstName = reader.readLine();
		System.out.println(messageResolver.getMessage("text.sname"));
		String lastName = reader.readLine();

		return new Person(firstName, lastName);
	}

}
