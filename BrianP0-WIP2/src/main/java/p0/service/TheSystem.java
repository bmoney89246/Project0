package p0.service;

import p0.pojos.Input;
import p0.pojos.User;

public class TheSystem {

	public static void main(String args[]) {
		User user = new User();
		DAO dao = new DAO();
		Input input = new Input();
		while (true) {
			while (true) {
				LoginOrRegister(user, input);
				if ("1".equals(user.getLoginOrRegisterChoice())) {
					if (Login.tryLogin(user, dao, input)) {
						break;
					}
				} else if ("2".equals(user.getLoginOrRegisterChoice())) {
					Register.Registration(user, dao, input);
				} else {
					System.out.println("Invalid input. Please try again\n");
				}
			}
			CustomerOrEmployee(user, input);
			while (true) {
				if ("1".equals(user.getCustomerOrEmployeeChoice())) {
					Customer.customerMenu(user, dao, input);
				} else if ("2".equals(user.getCustomerOrEmployeeChoice())) {
					Employee.employeeMenu(user, dao, input);
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

	public static User LoginOrRegister(User user, Input input) {
		System.out.println("1: Login" + System.lineSeparator() + "2: Register" + System.lineSeparator());
		user.setLoginOrRegisterChoice(input.getUserInput());
		return user;
	}

	public static User CustomerOrEmployee(User user, Input input) {
		System.out.println("1: Customer:" + System.lineSeparator() + "2: Employee" + System.lineSeparator());
		user.setCustomerOrEmployeeChoice(input.getUserInput());
		return user;
	}
}
