package p0.pojos;

import java.util.Scanner;

public class Input {
	private static String result;
	private static Scanner in = new Scanner(System.in);

	public static String getUserInput() {
		String input = in.nextLine();
		return input;
	}

	public static void SetScanner(Scanner in) {
		Input.in = in;
	}

	public static String getResult() {
		return Input.result;
	}

	public static void setResult(String result) {
		Input.result = result;
	}
}
