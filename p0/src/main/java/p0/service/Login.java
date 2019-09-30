package p0.service;

public class Login {
	
	//Determine if employee or customer
	public boolean tryLogin() {
		
		if(CustomerOrEmployee() == true) {
			//employee
	
		}else {
			//customer
		}
		return true;
}
public static boolean CustomerOrEmployee() {
	System.out.println("1: New Customer\n2: New Employee");
	TheSystem.result = TheSystem.in.nextLine();
	if("1".equals(TheSystem.result)) {
		return true;
	}else {
		return false;
	}
}
}
