package p0.service;

import p0.pojos.Input;
import p0.pojos.User;

public class Register {

	public static boolean Registration(User user, DAO dao, Input input) {

		// Set the username and password
		System.out.println("Enter username:\n");
		user.setUsername(input.getUserInput());
		System.out.println("\n");
		System.out.println("Enter password:\n");
		user.setPassword(input.getUserInput());
		System.out.println("\n");
		if(!dao.registerDao(user)) {
			System.out.println("login credentials already taken. Please try again" + System.lineSeparator());
			return false;
		}

		System.out.println("Credentials sucessfully created." + System.lineSeparator());
		return true;
	}
}
