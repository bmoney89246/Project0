package p0.service;

import java.util.Scanner;

public class TheSystem {

	static Scanner in = new Scanner(System.in);
	static String result = "";

	public static void main(String args[]) {

		// Login or Register
		if (LoginOrRegister() == true) {
			if (Login.tryLogin() == false) {
				LoginOrRegister();
			}
		} else {

			// register, if fails, user already exists and ask to login or register again
			if (Register.Registration() == false) {
				LoginOrRegister();
			}
			Login.tryLogin();
		}

		if (CustomerOrEmployee() == true) {
			// employee
			while (true) {
				Employee.employeeMenu();
			}
		} else {
			// customer
			while (true) {
				Customer.customerMenu();
			}
		}

	}

	public static boolean LoginOrRegister() {
		System.out.println("1: Login\n2: Register");
		result = in.nextLine();
		if ("1".equals(result)) {
			return true;
		} else if ("2".equals(result)) {
			return false;
		} else {
			System.out.println("Invalid input. Please try again.");
			LoginOrRegister();
		}
		// Should never be reached
		return true;
	}

	public static boolean CustomerOrEmployee() {
		System.out.println("1: Customer\n2: Employee");
		TheSystem.result = TheSystem.in.nextLine();
		if ("1".equals(TheSystem.result)) {
			return false;
		} else {
			return true;
		}
	}

}
