package otus.data.scanner;

import java.util.Scanner;

public class QuestionInputScanner {

	private final Scanner scanner;

	public QuestionInputScanner() {
		this.scanner = new Scanner(System.in);
	}

	public Scanner getScanner() {
		return scanner;
	}
}
