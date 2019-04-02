package otus;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import otus.data.person.Person;
import otus.data.scanner.Reader;
import otus.service.localization.MessageResolver;
import otus.service.person.PersonService;
import otus.service.person.PersonServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PersonServiceImpl.class})
public class PersonServiceTest {

	@Autowired
	private PersonService personService;

	@MockBean
	private MessageResolver messageResolver;

	@MockBean
	private Reader reader;

	@Before
	public void setUp() {
		doReturn("Test name", "Test surname").when(reader).readLine();
	}

	@Test
	public void createPerson() {
		Person person = personService.inputPersonData();
		assertEquals(new Person("Test name", "Test surname"), person);
	}
}
