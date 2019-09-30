package p0.pojos;

public class Car {
		private String Vin;
		private String Make;
		private String Model;
		private String year;
		public String getVin() {
			return Vin;
		}
		public void setVin(String vin) {
			Vin = vin;
		}
		public String getMake() {
			return Make;
		}
		public void setMake(String make) {
			Make = make;
		}
		public String getModel() {
			return Model;
		}
		public void setModel(String model) {
			Model = model;
		}
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public Car() {
			super();
		}

}
