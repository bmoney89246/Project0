package com.revature.dao;

import java.util.List;

import com.revature.pojo.User;

public interface UserDao {
	
	public User getUser(String username, String password);
	
}
