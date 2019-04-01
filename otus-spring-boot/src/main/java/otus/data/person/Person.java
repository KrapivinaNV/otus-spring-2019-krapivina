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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Person person = (Person) o;

		if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) {
			return false;
		}
		return lastName != null ? lastName.equals(person.lastName) : person.lastName == null;
	}

	@Override
	public int hashCode() {
		int result = firstName != null ? firstName.hashCode() : 0;
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		return result;
	}
}
