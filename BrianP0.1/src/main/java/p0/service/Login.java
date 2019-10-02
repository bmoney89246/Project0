package p0.service;

import p0.pojos.User;

public class Login {
	
	public User InitialLogin(User user) {
		System.out.println("Enter Username");
		if(TheSystem.in.nextLine().equals("Bmoney")) {
			user.setUsername("Bmoney");
		}
		System.out.println("Enter Password");
		if(TheSystem.in.nextLine().equals("Password")) {
			user.setPassword("Password");
		}
		return user;
	}
	
}
