package p0.service;

import p0.pojos.FileInfo;
import p0.pojos.Input;
import p0.pojos.User;

public class Register {

	public static boolean Registration(User user, Input input, DAO dao, FileInfo fileInfo) {

		// Set the username and password
		System.out.println("Enter username:");
		user.setUsername(input.getUserInput());
		fileInfo.setFile(".//src//main//resources//accounts//" + user.getUsername() + ".dat");
		if (dao.fileExists(fileInfo.getFile())) {
			System.out.println("This user already exists");
			return false;
		}
		System.out.println("Enter password:");
		user.setPassword(input.getUserInput());
		if (!dao.createFile(fileInfo.getFile()) || !dao.writeToFile(fileInfo.getFile(), user.getPassword())) {
			return false;
		}
		System.out.println("Success");
		return true;
	}
}
