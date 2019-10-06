package p0.service;

import p0.pojos.Input;
import p0.pojos.User;

public class TheSystem {

	public static void main(String args[]) {
		User user = new User();
		DAO dao = new DAO();
		while (true) {
			// Registration check and initial login
			while (true) {
				LoginOrRegister(user);
				if ("1".equals(user.getLoginOrRegisterChoice())) {
					if (Login.tryLogin(user, dao)) {
						break;
					}
				} else if ("2".equals(user.getLoginOrRegisterChoice())) {
					Register.Registration(user, dao);
				} else {
					System.out.println("Invalid input. Please try again\n");
				}
			}
			CustomerOrEmployee(user);
			while (true) {
				if ("1".equals(user.getCustomerOrEmployeeChoice())) {
					Customer.customerMenu(user, dao);
				} else if ("2".equals(user.getCustomerOrEmployeeChoice())) {
					Employee.employeeMenu(user, dao);
				} else {
					System.out.println("Invalid input. Please try again\n");
				}
				if(Input.getReturnToLogin()) {
					Input.setReturnToLogin(false);
					break;
				}
				if(Input.getExitSystem()) {
					Input.setExitSystem(false);
					System.exit(0);
				}
			}
		}
	}

	public static User LoginOrRegister(User user) {
		System.out.println("1: Login" + System.lineSeparator() + "2: Register" + System.lineSeparator());
		user.setLoginOrRegisterChoice(Input.getUserInput());
		return user;
	}

	public static User CustomerOrEmployee(User user) {
		System.out.println("1: Customer:" + System.lineSeparator() + "Employee" + System.lineSeparator());
		user.setCustomerOrEmployeeChoice(Input.getUserInput());
		return user;
	}
}
