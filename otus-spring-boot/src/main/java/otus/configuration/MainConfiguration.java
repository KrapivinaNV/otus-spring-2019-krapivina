package otus.configuration;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import otus.data.scanner.QuestionInputScanner;
import otus.service.localization.MessageResolver;
import otus.service.localization.MessageResolverImpl;
import otus.service.main.MainQuestionnaire;
import otus.service.main.MainQuestionnaireImpl;
import otus.service.parser.CsvParser;
import otus.service.parser.DefaultCsvParser;
import otus.service.person.PersonService;
import otus.service.person.PersonServiceImpl;
import otus.service.printer.PrinterQuestionnaireService;
import otus.service.printer.PrinterService;
import otus.service.questioning.QuestionnaireService;
import otus.service.questioning.QuestionnaireServiceImpl;


@Configuration
@EnableConfigurationProperties(QuizProperties.class)
public class MainConfiguration {

	private final String filename;

	private final Locale locale;

	@Autowired
	public MainConfiguration(QuizProperties properties) {
		this.locale = properties.getLocale();
		this.filename = String.format(properties.getFilename(), this.locale);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
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
		return new MessageResolverImpl(messageSource, this.locale);
	}

	@Bean
	public CsvParser csvParser(MessageResolver messageResolver) {
		return new DefaultCsvParser(this.filename);
	}

	@Bean
	public QuestionInputScanner scanner() {
		return new QuestionInputScanner();
	}

	@Bean
	public PersonService personService(QuestionInputScanner scanner, MessageResolver messageResolver) {
		return new PersonServiceImpl(scanner, messageResolver);
	}

	@Bean
	public QuestionnaireService questionnaireService(QuestionInputScanner scanner, MessageResolver messageResolver) {
		return new QuestionnaireServiceImpl(csvParser(messageResolver), scanner, messageResolver);
	}

	@Bean
	public PrinterService printerService(MessageResolver messageResolver) {
		return new PrinterQuestionnaireService(messageResolver);
	}

	@Bean
	public MainQuestionnaire mainQuestionnaire(QuestionInputScanner scanner, MessageResolver messageResolver) {
		return new MainQuestionnaireImpl(
				personService(scanner, messageResolver),
				questionnaireService(scanner, messageResolver),
				printerService(messageResolver)
		);
	}

}
