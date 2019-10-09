package p0.service;

import p0.pojos.Car;
import p0.pojos.Input;
import p0.pojos.User;

public class Employee {

	public static boolean employeeMenu(User user, DAO dao, Input input) {
		Car car = new Car();
		System.out.println(
				"1: Add Car\n2: Remove Car\n3: Accept/ Reject Offer\n4: View All Payments\n5: Logout\n6: Exit System");
		Input.setCurrentMenu(input.getUserInput());
		if ("1".equals(Input.getCurrentMenu())) {
			addCar(car, dao, input);
		} else if ("2".equals(Input.getCurrentMenu())) {
			removeCar(car, dao, input);
		} else if ("3".equals(Input.getCurrentMenu())) {
			acceptRejectOffer(user, car, dao, input);
		} else if ("4".equals(Input.getCurrentMenu())) {
			viewPayments(dao);
		} else if ("5".equals(Input.getCurrentMenu())) {
			Input.setReturnToLogin(true);
			return false;
		} else if ("6".equals(Input.getCurrentMenu())) {
			Input.setExitSystem(true);
			return false;
		} else {
			System.out.println("Invalid command");
			return false;
		}
		return true;
	}

	public static boolean addCar(Car car, DAO dao, Input input) {

		// Create car and add it to the lot
		getCarInfo(car, input);
		if (!dao.addCarDao(car)) {
			System.out.println("Car already exists on system");
			return false;
		}
		return true;
	}

	public static boolean getCarInfo(Car car, Input input) {
		System.out.println("Vin number:");
		car.setVin(input.getUserInput());
		System.out.println("Make:");
		car.setMake(input.getUserInput());
		System.out.println("Model:");
		car.setModel(input.getUserInput());
		System.out.println("Year:");
		car.setYear(input.getUserInput());
		return true;
	}

	public static boolean removeCar(Car car, DAO dao, Input input) {
		System.out.println("Please enter the vin number:");
		car.setVin(input.getUserInput());
		if (!dao.removeCarDao(car)) {
			System.out.println("Can't find car to delete.");
			return false;
		}
		return true;
	}

	public static boolean acceptOrReject(Input input) {
		System.out.println("1: Accept Offer\n2: Reject Offer");
		if ("2".equals(input.getUserInput())) {
			return false;
		}
		return true;
	}

	public static boolean acceptRejectOffer(User user, Car car, DAO dao, Input input) {
		System.out.println("Please enter the vin number:");
		car.setVin(input.getUserInput());
		System.out.println("Please enter the payment offer:");
		car.setPayment(input.getUserInput());
		System.out.println("Please enter the username of customer:");
		user.setUsername(input.getUserInput());
		if (acceptOrReject(input)) {
			if (!dao.acceptOfferDao(user, car)) {
				System.out.println("Can't find offer to accept.");
				return false;
			}
		} else {
			if (!dao.rejectOfferDao(user, car)) {
				System.out.println("Can't find offer to reject.");
				return false;
			}
		}
		return true;
	}

	public static boolean viewPayments(DAO dao) {
		if (!dao.viewPaymentsDao()) {
			System.out.println("Can't find any payments");
			return false;
		}
		return true;

	}
}
