package p0.service;

import java.util.Scanner;

import p0.pojos.User;

public class TheSystem {
	public static Scanner in = new Scanner(System.in);
	static User user = new User();
	public static void main(String args[]) {
		Login l1 = new Login();
		l1.InitialLogin(user);
		
	}
}
