package p0.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.apache.log4j.Logger;

public class Login {
	
	private static Logger log = Logger.getRootLogger();
	
	static String username="";
	static String passwordOnFile="";

	// Determine if employee or customer
	public static boolean tryLogin() {
		System.out.println("Username:");
		username = TheSystem.in.nextLine();
		String fileName = ".//src//main/resources//accounts//" + username + ".dat";
		File fileCheck = new File(".//src//main/resources//accounts//" + username + ".dat");
		if (!fileCheck.exists()) {
			System.out.println("User does not exist");
			return false;
		}
		try {
			// Reading the object from a file
			FileInputStream file = new FileInputStream(fileName);
			if(!fileCheck.exists()) {
				System.out.println("incorrect username");
				file.close();
				return false;
			}
			ObjectInputStream in = new ObjectInputStream(file);

			// Method for deserialization of object
			System.out.println("Password:");
			String passwordInput = TheSystem.in.nextLine();
			passwordOnFile = (String) in.readObject();
			if(!passwordInput.equals(passwordOnFile)) {
				System.out.println("incorrect password");
				in.close();
				return false;
			}
			System.out.println("login successful");
			in.close();
		} catch (IOException | ClassNotFoundException e) {
			log.error(e);
			System.out.println("login error");
		}	
		return true;
	}
}
