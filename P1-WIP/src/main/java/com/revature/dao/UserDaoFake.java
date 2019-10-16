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

	@Override
	public User getUser(String username, String password) {
		User user = new User();
		user.setPassword(password);
		user.setUsername(username);
		String sql = "select username, password from logincredentials where username = ? and password = ?";

		PreparedStatement stmt;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			ResultSet rs = stmt.executeQuery();

			if (!rs.next()) {
				return null;
			}
		} catch (SQLException e) {
			log.error(e);
			return null;
		}
		log.trace("User logged in");
		System.out.println(System.lineSeparator());
		return user;
	}

}
