package p0.service;

import p0.pojos.Input;
import p0.pojos.User;

public class TheSystem {

	public static void main(String args[]) {

		// Ask to login or register and send to appropriate screen
		while (LoginOrRegister() != 1 || LoginOrRegister() != 2) {
			User.setlogin_Register(CustomerOrEmployee());
		}
		if (User.getlogin_Register() == 2) {
			Register.Registration();
		} else if (User.getlogin_Register() == 1) {
			Login.tryLogin();
		}

		// Once logged in, check if employee or customer and send to appropriate menu
		while (CustomerOrEmployee() != 1 || CustomerOrEmployee() != 2) {
			User.setCustomer_Employee(CustomerOrEmployee());
		}
		if (User.getCustomer_Employee() == 2) {
			System.out.println("Please enter employee password:");
			if ("#JavaJesusRulez".contentEquals(Input.getUserInput())) {
				Employee.employeeMenu();
			}
		} else {
			Customer.customerMenu();
		}

	}

	public static int LoginOrRegister() {
		System.out.println("1: Login\n2: Register");
		Input.setResult(Input.getUserInput());
		if ("1".equals(Input.getResult())) {
			return 1;
		} else if ("2".equals(Input.getResult())) {
			return 2;
		} else {
			System.out.println("Invalid input. Please try again.");
			return 3;
		}
	}

	public static int CustomerOrEmployee() {
		System.out.println("1: Customer\n2: Employee");
		Input.setResult(Input.getUserInput());
		if ("1".equals(Input.getResult())) {
			return 1;
		} else if ("2".equals(Input.getResult())) {
			return 2;
		}
		System.out.println("Invalid input. Please try again.");
		return 3;
	}

}
