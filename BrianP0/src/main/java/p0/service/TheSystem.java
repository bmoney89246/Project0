package p0.service;

import java.util.Scanner;

public class TheSystem {

	static Scanner in = new Scanner(System.in);

	public static void SetScanner(Scanner in) {
		TheSystem.in = in;
	}

	static String result = "";

	public static void main(String args[]) {

		// Login or Register
		if (LoginOrRegister() == true) {
			while (Login.tryLogin() == false) {
				LoginOrRegister();
			}
		} else {

			// register, if fails, ask to login or register again
			while (Register.Registration() == false) {
				LoginOrRegister();
			}
			while (Login.tryLogin() == false) {
				LoginOrRegister();
			}
		}

		if (CustomerOrEmployee() == true) {
			// employee
			while (Employee.employeeMenu()) {
				Employee.employeeMenu();
			}
			System.out.println("Exiting system");
		} else {
			// customer
			while (Customer.customerMenu()) {
				Customer.customerMenu();
			}
			System.out.println("Exiting system");
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
			return false;
		}
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
