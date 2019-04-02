package otus.service.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import otus.data.questionnaire.Answer;
import otus.data.questionnaire.QuestionInfo;
import otus.data.questionnaire.Questionnaire;

public class QuizCsvParser implements CsvParser {

	private final String fileName;

	public QuizCsvParser(String fileName) {
		if (fileName == null) {
			throw new IllegalArgumentException();
		}
		this.fileName = fileName;
	}

	@Override
	public Questionnaire parse() {
		InputStream stream = QuizCsvParser.class.getResourceAsStream(fileName);
		if (stream == null) {
			throw new IllegalStateException();
		}

		List<QuestionInfo> questionInfoList = new ArrayList<>();
		try {
			Reader in = new StringReader(IOUtils.toString(stream));
			for (CSVRecord record : CSVFormat.newFormat(';').parse(in)) {
				String textQuestion = record.get(0);
				List<Answer> answers = new ArrayList<>();
				for (int i = 1; i < record.size() - 1; i += 2) {
					String textAnswer = record.get(i);
					Answer answer = new Answer(textAnswer, Boolean.valueOf(record.get(i + 1)));
					answers.add(answer);
				}
				questionInfoList.add(new QuestionInfo(textQuestion, answers));
			}
			return new Questionnaire(questionInfoList);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
