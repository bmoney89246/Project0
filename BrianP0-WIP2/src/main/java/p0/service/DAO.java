package p0.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import p0.pojos.Car;
import p0.pojos.User;
import p0.util.ConnectionFactory;

public class DAO implements DAOpersistable {
	private Connection conn = ConnectionFactory.getConnection();

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	private static Logger log = Logger.getRootLogger();

	public boolean registerDao(User user) {
		if (!loginDao(user)) {
			String sql = "insert into logincredentials (username, password) values (?,?)";

			PreparedStatement stmt;

			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, user.getUsername());
				stmt.setString(2, user.getPassword());
				stmt.executeUpdate();

			} catch (SQLException e) {
				log.error(e);
				return false;
			}
			log.trace("User registered");
			System.out.println(System.lineSeparator());
			return true;
		} else {
			return false;
		}
	}

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

	public boolean addCarDao(Car car) {
		String sql = "select * from carlot where vin = ?";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, car.getVin());
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			log.error(e);
			return false;
		}
		sql = "insert into carlot (vin, make, model, year) values (?,?,?,?)";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, car.getVin());
			stmt.setString(2, car.getMake());
			stmt.setString(3, car.getModel());
			stmt.setInt(4, Integer.parseInt(car.getYear()));
			stmt.executeUpdate();

		} catch (SQLException e) {
			log.error(e);
			return false;
		}
		log.trace("Car added to lot");
		System.out.println(System.lineSeparator());
		return true;
	}
	
	public boolean removeCarDao(Car car) {
		String sql = "delete from carlot where vin = ?";

		PreparedStatement stmt;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, car.getVin());
			stmt.executeUpdate();

		} catch (SQLException e) {
			log.error(e);
			return false;
		}
		log.trace("Car removed");
		System.out.println(System.lineSeparator());
		return true;
	}
	
	public boolean acceptOfferDao(User user, Car car) {
		String sql ="update offers set accepted = TRUE where c_id = (select c_id from carlot where vin = ?) and amount = ? and username = ?";
		PreparedStatement stmt;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, car.getVin());
			stmt.setInt(2, Integer.parseInt(car.getPayment()));
			stmt.setString(3, user.getUsername());
			stmt.executeUpdate();

		} catch (SQLException e) {
			log.error(e);
			return false;
		}
		log.trace("Offer accepted");
		System.out.println(System.lineSeparator());
		return true;
	}
	
	public boolean rejectOfferDao(User user, Car car) {
		String sql = "delete from offers where c_id = (select c_id from carlot where vin = ?) and amount = ? and username = ?";
		PreparedStatement stmt;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, car.getVin());
			stmt.setInt(2, Integer.parseInt(car.getPayment()));
			stmt.setString(3, user.getUsername());
			stmt.executeUpdate();

		} catch (SQLException e) {
			log.error(e);
			return false;
		}
		log.trace("Offer rejected");
		System.out.println(System.lineSeparator());
		return true;
	}
	
	public boolean makeOfferDao(User user, Car car) {
		String sql = "insert into offers (c_id, username, amount, accepted) values((select c_id from carlot where vin = ?), ?, ?, FALSE)";
		PreparedStatement stmt;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, car.getVin());
			stmt.setString(2, user.getUsername());
			stmt.setInt(3, Integer.parseInt(car.getPayment()));
			stmt.executeUpdate();

		} catch (SQLException e) {
			log.error(e);
			return false;
		}
		log.trace("Offer made");
		System.out.println(System.lineSeparator());
		return true;
	}
	
	public boolean viewPaymentsDao() {
		String sql = "select vin, c.make, c.model, c.year, o.username, o.amount from carlot c inner join offers o on c.c_id = o.c_id where o.accepted = TRUE";
		ResultSet rs;
		PreparedStatement stmt;

		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			int counter = 0;
			while(rs.next()) {
				System.out.println("VIN: " + rs.getString(1) + " MAKE: " + rs.getString(2) + " MODEL: " + rs.getString(3) + " YEAR: " + rs.getInt(4) + " USER: " + rs.getString(5) + " AMOUNT: " + rs.getInt(6));
				counter++;
			}
			log.trace(counter + " record(s) found.");
			System.out.println(System.lineSeparator());
		} catch (SQLException e) {
			log.error(e);
			return false;
		}
		return true;
	}
	public boolean viewLotDao() {
		String sql = "select c.vin, c.make, c.model, c.year from carlot c left join offers o on c.c_id = o.c_id where o.accepted = FALSE or o.accepted is null";
		ResultSet rs;
		PreparedStatement stmt;

		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			int counter = 0;
			while(rs.next()) {
				System.out.println("VIN: " + rs.getString(1) + " MAKE: " + rs.getString(2) + " MODEL: " + rs.getString(3) + " YEAR: " + rs.getInt(4));
				counter++;
			}
			log.trace(counter + " record(s) found.");
			System.out.println(System.lineSeparator());
		} catch (SQLException e) {
			log.error(e);
			return false;
		}
		return true;
	}
	
	public boolean viewOwnedCarsDao(User user) {
		String sql = "select c.vin, c.make, c.model, c.year from carlot c inner join offers o on c.c_id = o.c_id where o.username = ?";
		ResultSet rs;
		PreparedStatement stmt;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			rs = stmt.executeQuery();
			int counter = 0;
			while(rs.next()) {
				System.out.println("VIN: " + rs.getString(1) + " MAKE: " + rs.getString(2) + " MODEL: " + rs.getString(3) + " YEAR: " + rs.getInt(4));
				counter++;
			}
			log.trace(counter + " record(s) found.");
			System.out.println(System.lineSeparator());
		} catch (SQLException e) {
			log.error(e);
			return false;
		}
		return true;
	}
	
	public boolean viewRemPaymentsDao(User user) {
		String sql = "select c.vin, c.make, c.model, c.year, o.amount from carlot c inner join offers o on c.c_id = o.c_id where o.username = ?";
		ResultSet rs;
		PreparedStatement stmt;

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			rs = stmt.executeQuery();
			int counter = 0;
			while(rs.next()) {
				System.out.println("VIN: " + rs.getString(1) + " MAKE: " + rs.getString(2) + " MODEL: " + rs.getString(3) + " YEAR: " + rs.getInt(4) + " AMOUNT: " + rs.getInt(5));
				counter++;
			}
			log.trace(counter + " record(s) found.");
			System.out.println(System.lineSeparator());
		} catch (SQLException e) {
			log.error(e);
			return false;
		}
		return true;
	}
}
