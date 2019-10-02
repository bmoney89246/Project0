package p0.service;

import p0.pojos.FileInfo;
import p0.pojos.Input;
import p0.pojos.User;

public class Login {

	// Determine if employee or customer
	public static boolean tryLogin() {
		System.out.println("Username:");
		User.setUsername(Input.getUserInput());
		FileInfo.setFile(".//src//main/resources//accounts//" + User.getUsername() + ".dat");
		if (!DAO.fileExists(FileInfo.getFile())) {
			System.out.println("User does not exist. please try again.");
			return false;
		}
		System.out.println("Password:");
		User.setPassword(Input.getUserInput());
		if (!DAO.readFromFile(FileInfo.getFile()).equals(User.getPassword())) {
			System.out.println("incorrect password. Please try again");
			return false;
		}
		return true;
	}
}