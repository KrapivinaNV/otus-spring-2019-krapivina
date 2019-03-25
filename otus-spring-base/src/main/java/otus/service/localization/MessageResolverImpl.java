package otus.service.localization;

import java.util.Locale;
import org.springframework.context.MessageSource;

public class MessageResolverImpl implements MessageResolver {

	private final MessageSource messageSource;

	private final Locale locale;

	public MessageResolverImpl(MessageSource messageSource, Locale locale) {
		this.messageSource = messageSource;
		this.locale = locale;
	}

	@Override
	public String getMessage(String property) {
		return messageSource.getMessage(property, null, this.locale);
	}

	@Override
	public String getMessage(String property, String[] params) {
		return messageSource.getMessage(property, params, this.locale);
	}
}
