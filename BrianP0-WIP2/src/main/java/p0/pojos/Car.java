package p0.pojos;

public class Car {
	private  String Vin;
	private  String Make;
	private  String Model;
	private  String year;
	private  int payment;

	public  String getVin() {
		return Vin;
	}

	public  void setVin(String vin) {
		this.Vin = vin;
	}

	public  String getMake() {
		return Make;
	}

	public  void setMake(String make) {
		this.Make = make;
	}

	public  String getModel() {
		return Model;
	}

	public  void setModel(String model) {
		this.Model = model;
	}

	public  String getYear() {
		return year;
	}

	public  void setYear(String year) {
		this.year = year;
	}

	public  int getPayment() {
		return payment;
	}

	public  void setPayment(int payment) {
		this.payment = payment;
	}
}
