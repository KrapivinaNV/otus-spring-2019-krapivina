package otus.data.scanner;

import java.util.Scanner;

public class ScannerReader implements Reader {

	private final Scanner scanner;

	public ScannerReader() {
		this.scanner = new Scanner(System.in);
	}

	public String readLine() {
		return scanner.nextLine();
	}

	public boolean isNumber() {
		return scanner.hasNextInt();
	}

}
