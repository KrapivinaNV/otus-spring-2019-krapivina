package otus.service.localization;

public interface MessageResolver {

	String getMessage(String property);

	String getMessage(String property, String[] params);

}