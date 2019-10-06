package p0.service;

import p0.pojos.Input;
import p0.pojos.User;

public class Login {

	// Determine if employee or customer
	public static boolean tryLogin(User user, DAO dao) {
		System.out.println("Username:" + System.lineSeparator());
		user.setUsername(Input.getUserInput());
		System.out.println("Password:" + System.lineSeparator());
		user.setPassword(Input.getUserInput());
		if (!dao.loginDao(user)) {
			System.out.println("Username already taken. please try again");
			return false;
		}
		System.out.println("Login successful.");
		return true;
	}
}