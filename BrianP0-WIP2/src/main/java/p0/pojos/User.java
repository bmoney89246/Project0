package p0.pojos;

public class User {
	private String username;
	private String password;
	private String customerOrEmployeeChoice;
	private String LoginOrRegisterChoice;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustomerOrEmployeeChoice() {
		return customerOrEmployeeChoice;
	}

	public void setCustomerOrEmployeeChoice(String customerOrEmployeeChoice) {
		this.customerOrEmployeeChoice = customerOrEmployeeChoice;
	}

	public String getLoginOrRegisterChoice() {
		return LoginOrRegisterChoice;
	}

	public void setLoginOrRegisterChoice(String LoginOrRegisterChoice) {
		this.LoginOrRegisterChoice = LoginOrRegisterChoice;
	}
}
