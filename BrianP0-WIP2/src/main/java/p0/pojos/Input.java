package p0.pojos;

import java.util.Scanner;

public class Input {
	private Scanner in = new Scanner(System.in);
	private boolean returnToLogin;
	private String currentMenu;

	public String getUserInput() {
		String input = in.nextLine();
		return input;
	}

	public void SetScanner(Scanner in) {
		this.in = in;
	}

	public boolean getReturnToLogin() {
		return returnToLogin;
	}

	public void setReturnToLogin(boolean returnToLogin) {
		this.returnToLogin = returnToLogin;
	}
	public String getCurrentMenu() {
		return currentMenu;
	}

	public void setCurrentMenu(String currentMenu) {
		this.currentMenu = currentMenu;
	}
}
