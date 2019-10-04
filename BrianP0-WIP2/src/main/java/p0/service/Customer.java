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

	public static boolean customerMenu(User user, Input input, DAO dao, FileInfo fileInfo) {
		Car car = new Car();
		System.out.println("1: View Owned Cars\n2: View Remaining payments\n3: View Car Lot\n4: Make Offer\n5: Logout");
		input.setCurrentMenu(input.getUserInput());
		if ("1".equals(input.getCurrentMenu())) {
			viewOwnedCars(user, input, dao, fileInfo, car);
		} else if ("2".equals(input.getCurrentMenu())) {
			viewRemPayments(user, input, dao, fileInfo, car);
		} else if ("3".equals(input.getCurrentMenu())) {
			viewLot(user, input, dao, fileInfo, car);
		} else if ("4".equals(input.getCurrentMenu())) {
			makeOffer(user, input, dao, fileInfo, car);
		} else if ("5".equals(input.getCurrentMenu())) {
			input.setReturnToLogin(true);
			return false;
		} else {
			System.out.println("Invalid command");
		}
		return true;
	}

	public static boolean makeOffer(User user, Input input, DAO dao, FileInfo fileInfo, Car car) {
		System.out.println("Enter Vin:");
		car.setVin(input.getUserInput());
		fileInfo.setFile(".//src//main/resources//cars//" + car.getVin() + ".dat");
		if (!dao.fileExists(fileInfo.getFile())) {
			System.out.println("Car does not exist");
			return false;
		}
		fileInfo.setFile(".//src//main/resources//offers//" + car.getVin() + "-" + user.getUsername() + ".dat");
		if (dao.fileExists(fileInfo.getFile())) {
			System.out.println("You already made an offer on this car");
			return false;
		} else {
			System.out.println("Enter Offer:");
			dao.createFile(fileInfo.getFile());
			dao.writeToFile(fileInfo.getFile(), input.getUserInput());
		}
		System.out.println("Offer successfully made");
		return true;
	}

	public static boolean viewLot(User user, Input input, DAO dao, FileInfo fileInfo, Car car) {
		fileInfo.setFile(".//src//main/resources//cars//");
		if (fileInfo.getFile().listFiles() != null) {
			for (File child : fileInfo.getFile().listFiles()) {
				car.setVin(child.getName());
				try {
					System.out.println(Arrays.toString(dao.readFromFile(child).split(" ")));
				} catch (Exception e) {
					log.error(e);
					System.out.println("Error in printing out car lot");
					return false;
				}
			}
		} else {
			System.out.println("No cars on lot.");
			return false;
		}
		return true;
	}

	public static boolean viewOwnedCars(User user, Input input, DAO dao, FileInfo fileInfo, Car car) {
		boolean checkIfFound=false;
		fileInfo.setFile(".//src//main/resources//accpeted_offers//");
		if (fileInfo.getFile().listFiles().length != 0) {
			for (File child : fileInfo.getFile().listFiles()) {
				if (child.getName().contains(user.getUsername())) {
					checkIfFound = true;
					car.setVin(child.getName().replaceAll("-" + user.getUsername(), ""));
					fileInfo.setFile2(".//src//main/resources//cars//" + car.getVin());
					dao.readFromFile(fileInfo.getFile2());
				}
			}
		} else {
			System.out.println("No owned cars found");
			return false;
		}
		if(!checkIfFound) {
			System.out.println("No owned cars found");
		}
		return true;
	}

	public static boolean viewRemPayments(User user, Input input, DAO dao, FileInfo fileInfo, Car car) {
		System.out.println("Vin number:");
		car.setVin(input.getUserInput());
		fileInfo.setFile(".//src//main/resources//accpeted_offers//" + car.getVin() + ".dat");
		if (!dao.fileExists(fileInfo.getFile())) {
			System.out.println("No owned cars found");
			return false;
		}
		if ("".equals(dao.readFromFile(fileInfo.getFile()))) {
			System.out.println("No payment found");
			return false;
		}
		System.out.println("For a 5 year period, the current payment is $" + car.getPayment() / 60 + " per month");
		return true;
	}
}
