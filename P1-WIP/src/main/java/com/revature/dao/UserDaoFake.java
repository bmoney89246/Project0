package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.revature.pojo.User;
import com.revature.util.ConnectionFactory;

public class UserDaoFake implements UserDao {
	private Connection conn = ConnectionFactory.getConnection();

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	private static Logger log = Logger.getRootLogger();

	public boolean loginDao(User user) {
		
		String sql = "select username, password from logincredentials where username = ? and password = ?";

		PreparedStatement stmt;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			ResultSet rs = stmt.executeQuery();

			if (!rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			log.error(e);
			return false;
		}
		log.trace("User logged in");
		System.out.println(System.lineSeparator());
		return true;
	}
//	List<User> userRepository;
//	
//	{
//		userRepository = new ArrayList<User>();
//		
//		userRepository.add(new User("user@gmail.com", "pass", "MyUser"));
//		userRepository.add(new User("brian.money92@yahoo.com", "password", "Brian M"));
//	}

	public void createUser(User user) {
		// TODO Auto-generated method stub

	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	public void deleteUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
