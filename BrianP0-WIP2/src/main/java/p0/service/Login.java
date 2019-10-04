package p0.service;

import p0.pojos.FileInfo;
import p0.pojos.Input;
import p0.pojos.User;

public class Login {

	// Determine if employee or customer
	public static boolean tryLogin(User user, Input input, DAO dao, FileInfo fileInfo) {
		System.out.println("Username:");
		user.setUsername(input.getUserInput());
		fileInfo.setFile(".//src//main//resources//accounts//" + user.getUsername() + ".dat");
		if (!dao.fileExists(fileInfo.getFile())) {
			System.out.println("User does not exist. please try again.");
			return false;
		}
		System.out.println("Password:");
		user.setPassword(input.getUserInput());
		if (!dao.readFromFile(fileInfo.getFile()).equals(user.getPassword())) {
			System.out.println("incorrect password. Please try again");
			return false;
		}
		System.out.println("Success");
		return true;
	}
}