package otus;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import otus.data.questionnaire.Answer;
import otus.data.questionnaire.QuestionInfo;
import otus.data.questionnaire.Questionnaire;
import otus.data.scanner.Reader;
import otus.service.localization.MessageResolver;
import otus.service.parser.CsvParser;
import otus.service.questioning.QuestionnaireService;
import otus.service.questioning.QuestionnaireServiceImpl;
import otus.service.questioning.ScoreResults;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {QuestionnaireServiceImpl.class})
public class QuestionnaireServiceTest {

	@Autowired
	QuestionnaireService questionnaireService;

	@MockBean
	private CsvParser csvParser;

	@MockBean
	private Reader reader;

	@MockBean
	private Questionnaire questionnaire;

	@MockBean
	private MessageResolver messageResolver;

	@Before
	public void setUp() {

		doReturn("1", "1").when(reader).readLine();
		when(questionnaire.getQuestionInfos()).thenReturn(Stream.of(
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
		).collect(Collectors.toList()));

		when(csvParser.parse()).thenReturn(questionnaire);
	}

	@Test
	public void processAndGetScoreTest() {
		ScoreResults results = questionnaireService.processAndGetScore();
		assertEquals(new ScoreResults(1, 2), results);
	}

}
