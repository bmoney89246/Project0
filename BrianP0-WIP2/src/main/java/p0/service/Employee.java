package p0.service;

import p0.pojos.Car;
import p0.pojos.FileInfo;
import p0.pojos.Input;
import p0.pojos.User;

public class Employee {

	public static boolean employeeMenu(User user, Input input, DAO dao, FileInfo fileInfo) {
		Car car = new Car();
		System.out.println("1: Add Car\n2: Remove Car\n3: Accept Offer\n4: View All Payments\n5: Logout");
		input.setCurrentMenu(input.getUserInput());
		if ("1".equals(input.getCurrentMenu())) {
			addCar(car, user, input, dao, fileInfo);
		} else if ("2".equals(input.getCurrentMenu())) {
			removeCar(user, input, dao, fileInfo);
		} else if ("3".equals(input.getCurrentMenu())) {
			acceptOffer(car, user, input, dao, fileInfo);
		} else if ("4".equals(input.getCurrentMenu())) {
			viewPayments(car, user, input, dao, fileInfo);
		} else if ("5".equals(input.getCurrentMenu())) {
			input.setReturnToLogin(true);
			return false;
		} else {
			System.out.println("Invalid command");
		}
		return true;
	}

	public static boolean addCar(Car car, User user, Input input, DAO dao, FileInfo fileInfo) {

		// Create car and add it to the lot
		getCarInfo(car, input);
		fileInfo.setFile(".//src//main//resources//cars//" + car.getVin() + ".dat");
		if (dao.fileExists(fileInfo.getFile())) {
			System.out.println("This car is already in the lot.");
			return false;
		}
		if (!dao.createFile(fileInfo.getFile())) {
			return false;
		}

		if (!dao.writeToFile(fileInfo.getFile(), car.getMake() + " " + car.getModel() + " " + car.getYear())) {
			return false;
		}
		System.out.println("Car successfully added");
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

	public static boolean removeCar(User user, Input input, DAO dao, FileInfo fileInfo) {
		System.out.println("Vin number:");
		fileInfo.setFile(".//src//main//resources//cars//" + input.getUserInput() + ".dat");
		if (!dao.fileExists(fileInfo.getFile())) {
			System.out.println("Could not find car");
			return false;
		}
		dao.deleteFile(fileInfo.getFile());
		System.out.println("Car successfully deleted");
		return true;
	}

	public static boolean acceptOffer(Car car, User user, Input input, DAO dao, FileInfo fileInfo) {
		System.out.println("Vin number:");
		car.setVin(input.getUserInput());
		System.out.println("Customer Username:");
		String customerUserName = input.getUserInput();
		fileInfo.setFile(".//src//main//resources//offers//" + car.getVin() + "-" + customerUserName + ".dat");
		if (!dao.fileExists(fileInfo.getFile())) {
			System.out.println("Could not find that offer");
			return false;
		}
		fileInfo.setFile2(
				".//src//main//resources//accepted_offers//" + car.getVin() + "-" + customerUserName + ".dat");
		if (dao.fileExists(fileInfo.getFile2())) {
			System.out.println("This offer has already been accepted");
			return false;
		}
		if(!dao.copyFile(fileInfo.getFile(), fileInfo.getFile2())) {
			System.out.println("Error creating accepted offer");
			return false;
		}
		dao.deleteFile(fileInfo.getFile());
		fileInfo.setFile(".//src//main//resources//offers//");
		if (!dao.iterateAndDeleteFiles(fileInfo.getFile(), fileInfo.getFile().getName())) {
			return false;
		}
		System.out.println("Offer accepted");
		return true;
	}

	public static boolean viewPayments(Car car, User user, Input input, DAO dao, FileInfo fileInfo) {
		System.out.println("Vin number:");
		car.setVin(input.getUserInput());
		System.out.println("Customer Username:");
		user.setUsername(input.getUserInput());
		fileInfo.setFile(".//src//main//resources//accpeted_offers//" + user.getUsername() + ".dat");
		if(!fileInfo.getFile().exists()) {
			System.out.println("No cars are sold");
			return false;
		}
		try {
			car.setPayment(Integer.parseInt(dao.readFromFile(fileInfo.getFile())));
		} catch (Exception e) {
			System.out.println("Error reading payment");
			return false;
		}
			System.out.println("For a 5 year period, the current payment is $" + car.getPayment() / 60 + " per month");
		return true;

	}
}
