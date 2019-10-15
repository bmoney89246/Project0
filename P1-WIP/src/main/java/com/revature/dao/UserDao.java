package com.revature.dao;

import java.util.List;

import com.revature.pojo.User;

public interface UserDao {
	
	public User getUser(String username);
	
	public void createUser(User user);
	
	public List<User> getAllUsers();
	
	public void updateUser(User user);
	
	public void deleteUser(User user);
}
