package p0.pojos;

public class Car {
	private static String Vin;
	private static String Make;
	private static String Model;
	private static String year;
	private static int payment;

	public static String getVin() {
		return Vin;
	}

	public static void setVin(String vin) {
		Car.Vin = vin;
	}

	public static String getMake() {
		return Make;
	}

	public static void setMake(String make) {
		Car.Make = make;
	}

	public static String getModel() {
		return Model;
	}

	public static void setModel(String model) {
		Car.Model = model;
	}

	public static String getYear() {
		return year;
	}

	public static void setYear(String year) {
		Car.year = year;
	}
	public static int getPayment() {
		return payment;
	}

	public static void setPayment(int payment) {
		Car.payment = payment;
	}
}
