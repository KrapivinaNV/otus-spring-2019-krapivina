package otus.service.parser;

import otus.data.questionnaire.Questionnaire;

public interface CsvParser {

	Questionnaire parse(String filename);

}
