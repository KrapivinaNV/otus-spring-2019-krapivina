import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import otus.data.questionnaire.Answer;
import otus.data.questionnaire.QuestionInfo;
import otus.data.questionnaire.Questionnaire;
import otus.service.parser.CsvParser;
import otus.service.parser.QuizCsvParser;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuizCsvParser.class)
public class CsvParserTest {

	@Autowired
	private CsvParser csvParser;

	@Test
	public void parse() {
		Questionnaire actualParse = csvParser.parse("/quiz-test.csv");

		List<QuestionInfo> questionInfos =
				Stream.of(
						new QuestionInfo(
								"Question 1", Stream.of(
								new Answer("Answer 1-1", true),
								new Answer("Answer 1-2", false)
						).collect(Collectors.toList())),
						new QuestionInfo(
								"Question 2", Stream.of(
								new Answer("Answer 2-1", false),
								new Answer("Answer 2-2", true)
						).collect(Collectors.toList()))
				).collect(Collectors.toList());
		assertEquals(questionInfos, actualParse.getQuestionInfos());
	}

	@Test(expected = IllegalStateException.class)
	public void parseNoFileException() {
		csvParser.parse("/quiz-test2.csv");
	}

}
