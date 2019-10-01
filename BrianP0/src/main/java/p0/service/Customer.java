package p0.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import org.apache.log4j.Logger;

import p0.pojos.Car;

public class Customer {
	private static Logger log = Logger.getRootLogger();

	public static boolean customerMenu() {
		System.out.println("1: View Owned Cars\n2: View Remaining payments\n3: View Car Lot\n4: Make Offer\n5: Exit");
		TheSystem.result = TheSystem.in.nextLine();
		if ("1".equals(TheSystem.result)) {
			viewOwnedCars();
		} else if ("2".equals(TheSystem.result)) {
			viewRemPayments();
		} else if ("3".equals(TheSystem.result)) {
			viewLot();
		} else if ("4".equals(TheSystem.result)) {
			makeOffer();
		} else if ("5".equals(TheSystem.result)) {
			return false;
		} else {
			System.out.println("Invalid command");
		}
		return true;
	}

	public static boolean makeOffer() {
		System.out.println("Enter Vin:");
		TheSystem.result = TheSystem.in.nextLine();
		File file = new File(".//src//main/resources//cars//" + TheSystem.result + ".dat");
		if (!file.exists()) {
			System.out.println("Could not find car");
			return false;
		}
		file = new File(".//src//main/resources//offers//" + TheSystem.result + "-" + Login.username + ".dat");
		System.out.println("Enter Offer:");
		double offer = Double.valueOf(TheSystem.in.nextLine());
		if (file.exists()) {
			System.out.println("You already made an offer on this car");
			return false;
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Error adding offer");
				log.error(e);
				return false;
			}
			try {
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(offer);
				fos.close();
				oos.close();
			} catch (IOException e) {
				log.error(e);
				System.out.println("Error adding car details");
				return false;
			}
		}
		System.out.println("Offer successfully made");
		return true;
	}

	public static boolean viewLot() {
		Car c1 = new Car();
		File dir = new File(".//src//main/resources//cars//");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				c1.setVin(child.getName());
				try {
					FileInputStream file = new FileInputStream(child);
					ObjectInputStream in = new ObjectInputStream(file);
					String carDetails = (String) in.readObject();
					String[] carDetailsArray = carDetails.split(" ");
					System.out.println(Arrays.toString(carDetailsArray));
					in.close();
				} catch (Exception e) {
					log.error(e);
					System.out.println("Error in printing out car lot");
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	public static boolean viewOwnedCars() {
		File dir = new File(".//src//main/resources//accpeted_offers//");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				if (child.getName().contains(Login.username)) {
					String vinNum = child.getName().replaceAll("-" + Login.username, "");
					File dir2 = new File(".//src//main/resources//cars//" + vinNum);
					try {
						FileInputStream file = new FileInputStream(dir2);
						ObjectInputStream in = new ObjectInputStream(file);
						String carDetails = (String) in.readObject();
						System.out.println(carDetails);
						in.close();
					} catch (Exception e) {
						log.error(e);
						System.out.println("Error in printing out car lot");
						return false;

					}
				}
			}
		} else {
			return false;
		}

		return true;
	}

	public static boolean viewRemPayments() {
		double payment = 0;
		System.out.println("Vin number:");
		String vinNum = TheSystem.in.nextLine();
		TheSystem.result = vinNum + "-" + Login.username;
		File dest = new File(".//src//main/resources//accpeted_offers//" + TheSystem.result + ".dat");
		if (!dest.exists()) {
			System.out.println("Incorrect Vin Number or car does not exist");
			return false;
		}
		try {
			FileInputStream file = new FileInputStream(dest);
			ObjectInputStream in = new ObjectInputStream(file);
			payment = (double) in.readObject();
			in.close();
		} catch (Exception e) {
			log.error(e);
			System.out.println("Error reading payment");
		}
		System.out.println("How many years for the payment?");
		int years = Integer.parseInt(TheSystem.in.nextLine());
		try {
			System.out.println("For a " + years + " year period, the current payment is $" + payment / (years*12) + " per month");
		} catch (Exception e) {
			System.out.println("Error calculating payment");
			return false;
		}
		return true;
	}
}
