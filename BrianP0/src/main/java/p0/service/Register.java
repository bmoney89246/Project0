package p0.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import p0.pojos.User;

public class Register {
	private static Logger log = Logger.getRootLogger();
	static User newUser = new User();

	public static boolean Registration() {

		// Set the username and password
		String user = setUsername();
		File file = new File(".//src//main/resources//accounts//" + user + ".dat");
		if(file.exists()) {
			System.out.println("Username already exists");
			return false;
		}
		String writeString =  setPassword();
		try {
			file.createNewFile();
		} catch (IOException e) {
			log.error(e);
			System.out.println("Username and Password already exists");
			return false;
		}
		
		//Write credentials to file
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(writeString);
			fos.close();
			oos.close();
		}catch (IOException e) {
			log.error(e);
			System.out.println("Could not add credentials at this time.");
			return false;
		}
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
