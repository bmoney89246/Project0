package p0.service;

import java.util.Scanner;

public class TheSystem {
	static Scanner in = new Scanner(System.in);
	static String result="";
	public static void main(String args []) {
		
		//Login or Register
		if(LoginOrRegister() == true) {
			Login l1 = new Login();
		} else {
			Register.Registration();
		}
		
	}
	
	public static boolean LoginOrRegister() {
		System.out.println("1: Login\n2: Register");
		result = in.nextLine();
		if("1".equals(result)) {
			return true;
		} else if("2".equals(result)) {
			return false;
		} else {
			System.out.println("Invalid input. Please try again.");
			LoginOrRegister();
		}
		//Should never be reached
		return true;
	}
	
}
