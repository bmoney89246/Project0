package p0.service;

import p0.pojos.FileInfo;
import p0.pojos.Input;
import p0.pojos.User;

public class TheSystem {

	public static void main(String args[]) {
		User user = new User();
		Input input = new Input();
		DAO dao = new DAO();
		FileInfo fileInfo = new FileInfo();

		// Registration check and initial login
		while (true) {
			LoginOrRegister(user, input);
			if ("1".equals(user.getLoginOrRegisterChoice())) {
				if (Login.tryLogin(user, input, dao, fileInfo)) {
					break;
				}
			} else if ("2".equals(user.getLoginOrRegisterChoice())) {
				Register.Registration(user, input, dao);
			} else {
				System.out.println("Invalid input. Please try again");
			}
		}
		CustomerOrEmployee(user, input);
		while (true) {
			if ("1".equals(user.getCustomerOrEmployeeChoice())) {
				Customer.customerMenu(user, input, dao, fileInfo);
			} else if ("2".equals(user.getCustomerOrEmployeeChoice())) {
				Employee.employeeMenu(user, input, dao, fileInfo);
			} else {
				System.out.println("Invalid input. Please try again");
			}
		}
	}

	public static User LoginOrRegister(User user, Input input) {
		System.out.println("1: Login\n2: Register");
		user.setLoginOrRegisterChoice(input.getUserInput());
		return user;
	}

	public static User CustomerOrEmployee(User user, Input input) {
		System.out.println("1: Customer\n2: Employee");
		user.setCustomerOrEmployeeChoice(input.getUserInput());
		return user;
	}
}
