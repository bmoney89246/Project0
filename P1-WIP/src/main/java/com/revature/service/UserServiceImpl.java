package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoFake;
import com.revature.pojo.User;

public class UserServiceImpl implements UserService {
	
	private static UserDao userDao = new UserDaoFake();
	
	public User loginUser(String username, String password) {
		System.out.println("Attempted login with credentials: Username - " + username + " Password - " + password); //Use log 4 j
		User user = userDao.getUser(username);
		if( user != null && user.getPassword().contentEquals(password)) {
			return user;
		}
		return null;
	}

}
