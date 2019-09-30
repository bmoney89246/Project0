package p0.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

import org.apache.log4j.Logger;

import p0.pojos.Car;

public class Employee {
	private static Logger log = Logger.getRootLogger();
	public static boolean employeeMenu() {
		System.out.println("1: Add Car\n2: Remove Car\n3: Accept Offer\n4: View All Payments");
		TheSystem.result = TheSystem.in.nextLine();
		if("1".equals(TheSystem.result)) {
			addCar();
		} else if("2".equals(TheSystem.result)){
			removeCar();
		} else if("3".equals(TheSystem.result)){
			acceptOffer();
		} else if("4".equals(TheSystem.result)){
			viewPayments();
		}
		return true;	
	}
	
	public static boolean addCar() {
		Car c1 = new Car();
		
		// Set the car info
		getCarInfo(c1);
		File file = new File(".//src//main/resources//cars//" + c1.getVin() + ".dat");
		if(file.exists()) {
			System.out.println("Car already exists");
			return false;
		}
		String writeString =  c1.getMake() + " " + c1.getModel() + " " + c1.getYear();
		try {
			file.createNewFile();
		} catch (IOException e) {
			log.error(e);
			System.out.println("Error adding car to inventory");
			return false;
		}
		
		//Write car info to file
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(writeString);
			fos.close();
			oos.close();
		}catch (IOException e) {
			log.error(e);
			System.out.println("Error adding car details");
			return false;
		}
		return true;
	}
	
	public static boolean getCarInfo(Car c1) {
		System.out.println("Vin number:");
		TheSystem.result = TheSystem.in.nextLine();
		c1.setVin(TheSystem.result);
		System.out.println("Make:");
		TheSystem.result = TheSystem.in.nextLine();
		c1.setMake(TheSystem.result);
		System.out.println("Model:");
		TheSystem.result = TheSystem.in.nextLine();
		c1.setModel(TheSystem.result);
		System.out.println("Year:");
		TheSystem.result = TheSystem.in.nextLine();
		c1.setYear(TheSystem.result);
		return true;		
	}
	
	public static boolean removeCar() {
		System.out.println("Vin number:");
		TheSystem.result = TheSystem.in.nextLine();
		File file = new File(".//src//main/resources//cars//" + TheSystem.result + ".dat");
		if(file.exists()) {
			file.delete();
			System.out.println("Car successfully removed");
			return true;
		}
		System.out.println("Could not find car");
		return false;
	}
	
	public static boolean acceptOffer() {
		System.out.println("Vin number:");
		String vinNum = TheSystem.in.nextLine();
		System.out.println("Customer Username:");
		TheSystem.result = vinNum + "-" + TheSystem.in.nextLine();
		File file = new File(".//src//main/resources//offers//" + TheSystem.result + ".dat");
		File dest = new File(".//src//main/resources//accpeted_offers//" +TheSystem.result + ".dat");
		if(!file.exists()) {
			System.out.println("Could not find that offer");
			return false;
		}
		if(dest.exists()) {
			System.out.println("This offer has already been accepted");
		}
		try {
			Files.copy(file.toPath(), dest.toPath());
		} catch (IOException e) {
			log.error(e);
			System.out.println("Error copying file");
			return false;
		}
		file.delete();
		file = new File(".//src//main/resources//offers//");
		File[] directoryListing = file.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				if(child.getName().contains(vinNum)) {
					child.delete();
				}
			}
		}
		return true;
	}
	
	public static boolean viewPayments() {
		double payment =0;
		System.out.println("Vin number:");
		String vinNum = TheSystem.in.nextLine();
		System.out.println("Customer Username:");
		TheSystem.result = vinNum + "-" + TheSystem.in.nextLine();
		File dest = new File(".//src//main/resources//accpeted_offers//" +TheSystem.result + ".dat");
		try {
		FileInputStream file = new FileInputStream(dest);
		ObjectInputStream in = new ObjectInputStream(file);
		//String carDetails = (String) in.readObject();
		payment = (double) in.readObject();
		in.close();
		} catch(Exception e) {
			log.error(e);
			System.out.println("Error reading payment");
		}
		System.out.println("How many years for the payment?");
		System.out.println("For a 2 year period, the current payment is $" + payment/24 + " per month");
		return true;
		
	}
}
