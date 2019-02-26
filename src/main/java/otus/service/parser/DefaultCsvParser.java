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

public class DefaultCsvParser implements CsvParser {

	private final String fileName;

	public DefaultCsvParser(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public Questionnaire parse() {
		InputStream stream = DefaultCsvParser.class.getResourceAsStream(this.fileName);
		Questionnaire questionnaire = new Questionnaire();

		try {
			Reader in = new StringReader(IOUtils.toString(stream));
			for (CSVRecord record : CSVFormat.newFormat(';').parse(in)) {
				QuestionInfo questionInfo = new QuestionInfo(record.get(0));
				for (int i = 1; i < record.size() - 1; i += 2) {
					Answer answer = new Answer(record.get(i), Boolean.valueOf(record.get(i + 1)));
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
