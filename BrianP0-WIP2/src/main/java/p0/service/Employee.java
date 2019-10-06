package p0.service;

import p0.pojos.Car;
import p0.pojos.Input;
import p0.pojos.User;

public class Employee {

	public static boolean employeeMenu(User user, DAO dao) {
		Car car = new Car();
		System.out.println("1: Add Car\n2: Remove Car\n3: Accept Offer\n4: View All Payments\n5: Logout\n6: Exit System");
		Input.setCurrentMenu(Input.getUserInput());
		if ("1".equals(Input.getCurrentMenu())) {
			addCar(car, dao);
		} else if ("2".equals(Input.getCurrentMenu())) {
			removeCar(car, dao);
		} else if ("3".equals(Input.getCurrentMenu())) {
			acceptOffer(car, dao);
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
	
	public static boolean addCar(Car car, DAO dao) {

		// Create car and add it to the lot
		getCarInfo(car);
		if (!dao.addCarDao(car)) {
			System.out.println("Car already exists on system");
			return false;
		}
		return true;
	}

	public static boolean getCarInfo(Car car) {
		System.out.println("Vin number:");
		car.setVin(Input.getUserInput());
		System.out.println("Make:");
		car.setMake(Input.getUserInput());
		System.out.println("Model:");
		car.setModel(Input.getUserInput());
		System.out.println("Year:");
		car.setYear(Input.getUserInput());
		return true;
	}

	public static boolean removeCar(Car car, DAO dao) {
		if (!dao.removeCarDao(car)) {
			System.out.println("Can't find car to delete.");
			return false;
		}
		return true;
	}

	public static boolean acceptOffer(Car car, DAO dao) {
		System.out.println("Please enter the vin number:");
		car.setVin(Input.getUserInput());
		System.out.println("Please enter the payment offer:");
		car.setPayment(Input.getUserInput());
		if (!dao.acceptOfferDao(car)) {
			System.out.println("Can't find offer to accept.");
			return false;
		}
		System.out.println("Offer accepted.");
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
