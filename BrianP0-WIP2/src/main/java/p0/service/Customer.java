package p0.service;

import java.io.File;
import java.util.Arrays;
import org.apache.log4j.Logger;
import p0.pojos.Car;
import p0.pojos.FileInfo;
import p0.pojos.Input;
import p0.pojos.User;

public class Customer {
	private static Logger log = Logger.getRootLogger();

	public static boolean customerMenu(User user, DAO dao) {
		Car car = new Car();
		System.out.println(
				"1: View Owned Cars\n2: View Remaining payments\n3: View Car Lot\n4: Make Offer\n5: Logout\n6: Exit System");
		Input.setCurrentMenu(Input.getUserInput());
		if ("1".equals(Input.getCurrentMenu())) {
			viewOwnedCars(user, dao);
		} else if ("2".equals(Input.getCurrentMenu())) {
			viewRemPayments(user, dao);
		} else if ("3".equals(Input.getCurrentMenu())) {
			viewLot(user, car, dao);
		} else if ("4".equals(Input.getCurrentMenu())) {
			makeOffer(user, car, dao);
		} else if ("5".equals(Input.getCurrentMenu())) {
			Input.setReturnToLogin(true);
			return false;
		} else if ("6".equals(Input.getCurrentMenu())) {
			Input.setExitSystem(true);
			return false;
		} else {
			System.out.println("Invalid command");
		}
		return true;
	}

	public static boolean makeOffer(User user, Car car, DAO dao) {
		System.out.println("Enter vin number");
		car.setVin(Input.getUserInput());
		System.out.println("Enter payment offer:");
		car.setPayment(Input.getUserInput());
		if (!dao.makeOfferDao(user, car)) {
			System.out.println("Could not find car");
			return false;
		}
		return true;
	}

	public static boolean viewLot(User user, Car car, DAO dao) {
		if (!dao.viewLotDao()) {
			System.out.println("Could not find cars");
			return false;
		}
		return true;
	}

	public static boolean viewOwnedCars(User user, DAO dao) {
		if (!dao.viewOwnedCarsDao(user)) {
			System.out.println("Could not find owned cars");
			return false;
		}
		return true;
	}

	public static boolean viewRemPayments(User user, DAO dao) {
		if (!dao.viewRemPaymentsDao(user)) {
			System.out.println("Could not find any payments");
			return false;
		}
		return true;
	}
}
