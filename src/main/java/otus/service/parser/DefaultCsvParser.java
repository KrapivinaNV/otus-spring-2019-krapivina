package otus.service.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import otus.data.questionnaire.Answer;
import otus.data.questionnaire.QuestionInfo;
import otus.data.questionnaire.Questionnaire;
import otus.service.localization.MessageResolver;

public class DefaultCsvParser implements CsvParser {

	private final String fileName;

	private final MessageResolver messageResolver;

	public DefaultCsvParser(String fileName, MessageResolver messageResolver) {
		if (fileName == null) {
			throw new IllegalStateException();
		}

		this.fileName = fileName;
		this.messageResolver = messageResolver;
	}

	@Override
	public Questionnaire parse() {
		InputStream stream = DefaultCsvParser.class.getResourceAsStream(fileName);

		if (stream == null) {
			throw new IllegalStateException(String.format("File %s not found", fileName));
		}

		Questionnaire questionnaire = new Questionnaire();
		try {
			Reader in = new StringReader(IOUtils.toString(stream));
			for (CSVRecord record : CSVFormat.newFormat(';').parse(in)) {

				String textQuestion = messageResolver.getMessage(record.get(0));

				QuestionInfo questionInfo = new QuestionInfo(textQuestion);
				for (int i = 1; i < record.size() - 1; i += 2) {
					String textAnswer = messageResolver.getMessage(record.get(i));
					Answer answer = new Answer(textAnswer, Boolean.valueOf(record.get(i + 1)));
					questionInfo.addAnswer(answer);
				}
				questionnaire.addQuestion(questionInfo);
			}
			return questionnaire;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
