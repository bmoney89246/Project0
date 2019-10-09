package p0.pojos;

import java.util.Scanner;

public class Input {
	private static Scanner in = new Scanner(System.in);
	private static boolean returnToLogin;
	private static String currentMenu;
	private static boolean exitSystem;

	public String getUserInput() {
		String input = in.nextLine();
		return input;
	}

	public void SetScanner(Scanner in) {
		Input.in = in;
	}

	public static boolean getReturnToLogin() {
		return returnToLogin;
	}

	public static void setReturnToLogin(boolean returnToLogin) {
		Input.returnToLogin = returnToLogin;
	}
	public static boolean getExitSystem() {
		return exitSystem;
	}

	public static void setExitSystem(boolean exitSystem) {
		Input.exitSystem = exitSystem;
	}
	public static String getCurrentMenu() {
		return currentMenu;
	}

	public static void setCurrentMenu(String currentMenu) {
		Input.currentMenu = currentMenu;
	}
}
