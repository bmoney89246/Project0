package p0.pojos;

public class User {
	private static String username;
	private static String password;
	private static int customer_Employee;

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		User.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		User.password = password;
	}
	public static int getlogin_Register() {
		return customer_Employee;
	}

	public static void setlogin_Register(int login_Register) {
		User.customer_Employee = login_Register;
	}
	public static int getCustomer_Employee() {
		return customer_Employee;
	}

	public static void setCustomer_Employee(int login_Register) {
		User.customer_Employee = login_Register;
	}
}
