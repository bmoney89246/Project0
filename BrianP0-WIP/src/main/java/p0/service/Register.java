package p0.service;

import java.io.File;
import p0.pojos.Input;
import p0.pojos.User;

public class Register {

	public static boolean Registration() {

		// Set the username and password
		System.out.println("Enter username:");
		User.setUsername(Input.getUserInput());
		File file = new File(".//src//main/resources//accounts//" + User.getUsername() + ".dat");
		if (DAO.fileExists(file)) {
			System.out.println("This user already exists");
			return false;
		}
		User.setPassword(Input.getUserInput());
		if (!DAO.createFile(file) || !DAO.writeToFile(file, User.getPassword())) {
			return false;
		}
		return true;
	}
}
