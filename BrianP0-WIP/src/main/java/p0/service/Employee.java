package p0.service;

import p0.pojos.Car;
import p0.pojos.FileInfo;
import p0.pojos.Input;
import p0.pojos.User;

public class Employee {

	public static boolean employeeMenu() {
		System.out.println("1: Add Car\n2: Remove Car\n3: Accept Offer\n4: View All Payments\n5: Exit");
		if ("1".equals(Input.getUserInput())) {
			addCar();
		} else if ("2".equals(Input.getUserInput())) {
			removeCar();
		} else if ("3".equals(Input.getUserInput())) {
			acceptOffer();
		} else if ("4".equals(Input.getUserInput())) {
			viewPayments();
		} else if ("5".equals(Input.getUserInput())) {
			return false;
		} else {
			System.out.println("Invalid command");
		}
		return true;
	}

	public static boolean addCar() {

		// Create car and add it to the lot
		getCarInfo();
		FileInfo.setFile(".//src//main/resources//cars//" + Car.getVin() + ".dat");
		if (DAO.fileExists(FileInfo.getFile())) {
			System.out.println("This car is already in the lot.");
			return false;
		}
		if (!DAO.createFile(FileInfo.getFile())) {
			return false;
		}

		if (!DAO.writeToFile(FileInfo.getFile(), Car.getMake() + " " + Car.getModel() + " " + Car.getYear())) {
			return false;
		}
		return true;
	}

	public static boolean getCarInfo() {
		System.out.println("Vin number:");
		Car.setVin(Input.getUserInput());
		System.out.println("Make:");
		Car.setMake(Input.getUserInput());
		System.out.println("Model:");
		Car.setModel(Input.getUserInput());
		System.out.println("Year:");
		Car.setYear(Input.getUserInput());
		return true;
	}

	public static boolean removeCar() {
		System.out.println("Vin number:");
		FileInfo.setFile(".//src//main/resources//cars//" + Input.getUserInput() + ".dat");
		if (!DAO.fileExists(FileInfo.getFile())) {
			System.out.println("Could not find car");
			return false;
		}
		DAO.deleteFile(FileInfo.getFile());
		return true;
	}

	public static boolean acceptOffer() {
		System.out.println("Vin number:");
		Car.setVin(Input.getUserInput());
		System.out.println("Customer Username:");
		FileInfo.setFile(".//src//main/resources//offers//" + Car.getVin() + "-" + Input.getUserInput() + ".dat");
		if (!DAO.fileExists(FileInfo.getFile())) {
			System.out.println("Could not find that offer");
			return false;
		}
		FileInfo.setFile2(
				".//src//main/resources//accepted_offers//" + Car.getVin() + "-" + Input.getUserInput() + ".dat");
		if (!DAO.fileExists(FileInfo.getFile())) {
			System.out.println("This offer has already been accepted");
			return false;
		}
		DAO.copyFile(FileInfo.getFile(), FileInfo.getFile2());
		DAO.deleteFile(FileInfo.getFile());
		FileInfo.setFile(".//src//main/resources//offers//");
		if (!DAO.iterateAndDeleteFiles(FileInfo.getFile(), FileInfo.getFile().getName())) {
			return false;
		}
		;
		System.out.println("Offer accepted");
		return true;
	}

	public static boolean viewPayments() {
		System.out.println("Vin number:");
		Car.setVin(Input.getUserInput());
		System.out.println("Customer Username:");
		User.setUsername(Input.getUserInput());
		FileInfo.setFile(".//src//main/resources//accpeted_offers//" + User.getUsername() + ".dat");
		try {
			Car.setPayment(Integer.parseInt(DAO.readFromFile(FileInfo.getFile())));
		} catch (Exception e) {
			return false;
		}
		try {
			System.out.println("For a 5 year period, the current payment is $" + Car.getPayment() / 60 + " per month");
		} catch (Exception e) {
			System.out.println("Error calculating payment");
			return false;
		}
		return true;

	}
}
