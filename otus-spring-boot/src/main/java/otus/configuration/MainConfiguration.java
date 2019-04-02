package otus.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import otus.data.scanner.Reader;
import otus.data.scanner.ScannerReader;
import otus.service.localization.MessageResolver;
import otus.service.localization.MessageResolverImpl;
import otus.service.main.MainQuestionnaire;
import otus.service.main.MainQuestionnaireImpl;
import otus.service.parser.CsvParser;
import otus.service.parser.QuizCsvParser;
import otus.service.person.PersonService;
import otus.service.person.PersonServiceImpl;
import otus.service.printer.PrinterQuestionnaireService;
import otus.service.printer.PrinterService;
import otus.service.questioning.QuestionnaireService;
import otus.service.questioning.QuestionnaireServiceImpl;


@Configuration
@EnableConfigurationProperties(QuizProperties.class)
public class MainConfiguration {

	private final QuizProperties properties;

	@Autowired
	public MainConfiguration(QuizProperties properties) {
		this.properties = properties;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		ms.setBasename("/il8n/bundle");
		ms.setDefaultEncoding("windows-1251");
		return ms;
	}

	@Bean
	public MessageResolver messageResolver(MessageSource messageSource) {
		return new MessageResolverImpl(messageSource, this.properties.getLocale());
	}

	@Bean
	public CsvParser csvParser() {
		return new QuizCsvParser(String.format(this.properties.getFilename(), this.properties.getLocale()));
	}

	@Bean
	public Reader reader() {
		return new ScannerReader();
	}

	@Bean
	public PersonService personService(Reader reader, MessageResolver messageResolver) {
		return new PersonServiceImpl(reader, messageResolver);
	}

	@Bean
	public QuestionnaireService questionnaireService(CsvParser parser, Reader reader,
			MessageResolver messageResolver) {
		return new QuestionnaireServiceImpl(
				parser.parse(),
				reader,
				messageResolver
		);
	}

	@Bean
	public PrinterService printerService(MessageResolver messageResolver) {
		return new PrinterQuestionnaireService(messageResolver);
	}

	@Bean
	public MainQuestionnaire mainQuestionnaire(CsvParser parser, Reader reader,
			MessageResolver messageResolver) {
		return new MainQuestionnaireImpl(
				personService(reader, messageResolver),
				questionnaireService(parser, reader, messageResolver),
				printerService(messageResolver)
		);
	}

}
