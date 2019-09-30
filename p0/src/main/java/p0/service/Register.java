package p0.service;

import p0.pojos.User;

public class Register {
	static User newUser = new User();

	public static boolean Registration() {

		// Set the username and password
		setUsername();
		setPassword() ;
		return true;
	}

	public static String setUsername() {
		System.out.println("Enter username:");
		TheSystem.result = TheSystem.in.nextLine();
		newUser.setUsername(TheSystem.result);
		return TheSystem.result;
	}

	public static String setPassword() {
		System.out.println("Enter password:");
		TheSystem.result = TheSystem.in.nextLine();
		newUser.setUsername(TheSystem.result);
		return TheSystem.result;
	}
}
