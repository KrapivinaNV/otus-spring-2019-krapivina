package otus.service.printer;

import otus.data.person.Person;
import otus.service.questioning.ScoreResults;

public interface PrinterService {

	void print(Person person, ScoreResults scoreResults);

}
