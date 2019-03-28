package otus.data.person;

public class Person {

	private final String firstName;

	private final String lastName;

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return this.firstName +
				" " +
				this.lastName;
	}

}
