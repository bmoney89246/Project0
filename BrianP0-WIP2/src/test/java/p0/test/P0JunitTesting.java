package p0.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.postgresql.jdbc.PgStatement;
import p0.pojos.Car;
import p0.pojos.Input;
import p0.pojos.User;
import p0.service.Customer;
import p0.service.DAO;
import p0.service.Employee;
import p0.service.Login;
import p0.service.Register;
import p0.service.TheSystem;
import p0.util.CallableDriver;
import p0.util.ConnectionFactory;

@RunWith(MockitoJUnitRunner.class)
public class P0JunitTesting {

	@Mock
	DAO dao;

	@Mock
	User user;

	@Mock
	Car car;

	@Mock
	Input input;

	@Mock
	private Connection conn;

	@Spy
	PreparedStatement stmt2 = ConnectionFactory.getConnection().prepareStatement("select * from carlot where vin = ?");

	@Spy
	PreparedStatement stmt3 = ConnectionFactory.getConnection()
			.prepareStatement("insert into carlot (vin, make, model, year) values (?,?,?,?)");

	@Spy
	PreparedStatement stmt4 = ConnectionFactory.getConnection().prepareStatement("delete from carlot where vin = ?");

	@Spy
	PreparedStatement stmt5 = ConnectionFactory.getConnection().prepareStatement(
			"update offers set accepted = TRUE where c_id = (select c_id from carlot where vin = ?) and amount = ? and username = ?");

	@Spy
	PreparedStatement stmt6 = ConnectionFactory.getConnection().prepareStatement(
			"delete from offers where c_id = (select c_id from carlot where vin = ?) and amount = ? and username = ?");

	@Spy
	PreparedStatement stmt7 = ConnectionFactory.getConnection().prepareStatement(
			"select vin, c.make, c.model, c.year, o.username, o.amount from carlot c inner join offers o on c.c_id = o.c_id where o.accepted = TRUE");

	@Spy
	PreparedStatement stmt8 = ConnectionFactory.getConnection().prepareStatement(
			"insert into offers (c_id, username, amount, accepted) values((select c_id from carlot where vin = ?), ?, ?, FALSE)");

	@Spy
	PreparedStatement stmt9 = ConnectionFactory.getConnection().prepareStatement(
			"select c.vin, c.make, c.model, c.year from carlot c left join offers o on c.c_id = o.c_id where o.accepted = FALSE or o.accepted is null");

	@Spy
	PreparedStatement stmt10 = ConnectionFactory.getConnection().prepareStatement(
			"select c.vin, c.make, c.model, c.year from carlot c inner join offers o on c.c_id = o.c_id where o.username = ?");

	@Spy
	PreparedStatement stmt11 = ConnectionFactory.getConnection().prepareStatement(
			"select c.vin, c.make, c.model, c.year, o.amount from carlot c inner join offers o on c.c_id = o.c_id where o.username = ?");

	@Spy
	private PgStatement stmt = (PgStatement) ConnectionFactory.getConnection().createStatement();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		when(conn.createStatement()).thenReturn(stmt);
		dao.setConn(conn);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void LoginOrRegisterTest() {
		CallableDriver cd = new CallableDriver();
		cd.deleteTestCredentials();
		User user = new User();
		when(input.getUserInput()).thenReturn("1");
		TheSystem.LoginOrRegister(user, input);
		assertEquals("1", user.getLoginOrRegisterChoice());
		when(input.getUserInput()).thenReturn("2");
		TheSystem.LoginOrRegister(user, input);
		assertEquals("2", user.getLoginOrRegisterChoice());
	}

	@Test
	public void CustomerOrEmployeeTest() {
		User user = new User();
		when(input.getUserInput()).thenReturn("1");
		TheSystem.CustomerOrEmployee(user, input);
		assertEquals("1", user.getCustomerOrEmployeeChoice());
		when(input.getUserInput()).thenReturn("2");
		TheSystem.CustomerOrEmployee(user, input);
		assertEquals("2", user.getCustomerOrEmployeeChoice());
	}

	@Test
	public void RegisterTest() {
		when(input.getUserInput()).thenReturn("");
		when(user.getUsername()).thenReturn("cuser");
		when(user.getPassword()).thenReturn("cpass");
		when(dao.loginDao(user)).thenReturn(true);
		when(dao.registerDao(user)).thenReturn(true);
		assertTrue(Register.Registration(user, dao, input));
		when(user.getUsername()).thenReturn("rand");
		when(user.getPassword()).thenReturn("rand");
		when(dao.loginDao(user)).thenReturn(false);
		when(dao.registerDao(user)).thenReturn(false);
		assertFalse(Register.Registration(user, dao, input));
	}

	@Test
	public void LoginTest() {
		when(input.getUserInput()).thenReturn("");
		when(user.getUsername()).thenReturn("cuser");
		when(user.getPassword()).thenReturn("cpass");
		when(dao.loginDao(user)).thenReturn(true);
		assertTrue(Login.tryLogin(user, dao, input));
		when(user.getUsername()).thenReturn("rand");
		when(user.getPassword()).thenReturn("rand");
		when(dao.loginDao(user)).thenReturn(false);
		assertFalse(Login.tryLogin(user, dao, input));
	}

	@Test
	public void employeeMenuTest() {
		when(input.getUserInput()).thenReturn("1");
		Employee.employeeMenu(user, dao, input);
		assertEquals(Input.getCurrentMenu(), "1");
		when(input.getUserInput()).thenReturn("2");
		Employee.employeeMenu(user, dao, input);
		assertEquals(Input.getCurrentMenu(), "2");
		when(input.getUserInput()).thenReturn("3");
		Employee.employeeMenu(user, dao, input);
		assertEquals(Input.getCurrentMenu(), "3");
		when(input.getUserInput()).thenReturn("4");
		Employee.employeeMenu(user, dao, input);
		assertEquals(Input.getCurrentMenu(), "4");
		when(input.getUserInput()).thenReturn("5");
		Employee.employeeMenu(user, dao, input);
		assertEquals(Input.getCurrentMenu(), "5");
		when(input.getUserInput()).thenReturn("6");
		Employee.employeeMenu(user, dao, input);
		assertEquals(Input.getCurrentMenu(), "6");
	}

	@Test
	public void customerMenuTest() {
		when(input.getUserInput()).thenReturn("1");
		Customer.customerMenu(user, dao, input);
		assertEquals(Input.getCurrentMenu(), "1");
		when(input.getUserInput()).thenReturn("2");
		Customer.customerMenu(user, dao, input);
		assertEquals(Input.getCurrentMenu(), "2");
		when(input.getUserInput()).thenReturn("3");
		Customer.customerMenu(user, dao, input);
		assertEquals(Input.getCurrentMenu(), "3");
		when(input.getUserInput()).thenReturn("4");
		Customer.customerMenu(user, dao, input);
		assertEquals(Input.getCurrentMenu(), "4");
		when(input.getUserInput()).thenReturn("5");
		Customer.customerMenu(user, dao, input);
		assertEquals(Input.getCurrentMenu(), "5");
		when(input.getUserInput()).thenReturn("6");
		Customer.customerMenu(user, dao, input);
		assertEquals(Input.getCurrentMenu(), "6");
	}

	@Test
	public void addCarTest() {
		Car car = new Car();
		DAO dao = new DAO();
		String sql = "select * from carlot where vin = ?";
		String sql2 = "insert into carlot (vin, make, model, year) values (?,?,?,?)";
		when(input.getUserInput()).thenReturn("");
		car.setVin("testVin");
		car.setMake("testMake");
		car.setModel("testModel");
		car.setYear("2019");
		try {
			when(conn.prepareStatement(sql)).thenReturn(stmt2);
			when(conn.prepareStatement(sql2)).thenReturn(stmt3);
			dao.setConn(conn);
			dao.addCarDao(car);
			Mockito.verify(stmt2).executeQuery();
			Mockito.verify(stmt3).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void viewLotTest() {
		DAO dao = new DAO();
		String sql = "select c.vin, c.make, c.model, c.year from carlot c left join offers o on c.c_id = o.c_id where o.accepted = FALSE or o.accepted is null";
		when(input.getUserInput()).thenReturn("");
		try {
			when(conn.prepareStatement(sql)).thenReturn(stmt9);
			dao.setConn(conn);
			dao.viewLotDao();
			Mockito.verify(stmt9).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void makeOfferTest() {
		User user = new User();
		Car car = new Car();
		DAO dao = new DAO();
		String sql = "insert into offers (c_id, username, amount, accepted) values((select c_id from carlot where vin = ?), ?, ?, FALSE)";
		when(input.getUserInput()).thenReturn("");
		user.setUsername("testUser");
		car.setVin("testVin");
		car.setPayment("60000");
		try {
			when(conn.prepareStatement(sql)).thenReturn(stmt8);
			dao.setConn(conn);
			dao.makeOfferDao(user, car);
			Mockito.verify(stmt8).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void acceptRejectOfferTest() {
		User user = new User();
		Car car = new Car();
		DAO dao = new DAO();
		String sql = "update offers set accepted = TRUE where c_id = (select c_id from carlot where vin = ?) and amount = ? and username = ?";
		when(input.getUserInput()).thenReturn("");
		user.setUsername("testUser");
		car.setVin("testVin");
		car.setPayment("60000");
		try {
			when(conn.prepareStatement(sql)).thenReturn(stmt5);
			dao.setConn(conn);
			dao.acceptOfferDao(user, car);
			Mockito.verify(stmt5).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void viewPaymentsTest() {
		DAO dao = new DAO();
		String sql = "select vin, c.make, c.model, c.year, o.username, o.amount from carlot c inner join offers o on c.c_id = o.c_id where o.accepted = TRUE";
		try {
			when(conn.prepareStatement(sql)).thenReturn(stmt7);
			dao.setConn(conn);
			dao.viewPaymentsDao();
			Mockito.verify(stmt7).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void viewOwnedCarsTest() {
		User user = new User();
		DAO dao = new DAO();
		String sql = "select c.vin, c.make, c.model, c.year from carlot c inner join offers o on c.c_id = o.c_id where o.username = ?";
		when(input.getUserInput()).thenReturn("");
		user.setUsername("testUser");
		try {
			when(conn.prepareStatement(sql)).thenReturn(stmt10);
			dao.setConn(conn);
			dao.viewOwnedCarsDao(user);
			Mockito.verify(stmt10).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void viewRemPaymentsTest() {
		User user = new User();
		DAO dao = new DAO();
		String sql = "select c.vin, c.make, c.model, c.year, o.amount from carlot c inner join offers o on c.c_id = o.c_id where o.username = ?";
		when(input.getUserInput()).thenReturn("");
		user.setUsername("testUser");
		try {
			when(conn.prepareStatement(sql)).thenReturn(stmt11);
			dao.setConn(conn);
			dao.viewRemPaymentsDao(user);
			Mockito.verify(stmt11).executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void acceptRejectOffer2Test() {
		User user = new User();
		Car car = new Car();
		DAO dao = new DAO();
		String sql = "delete from offers where c_id = (select c_id from carlot where vin = ?) and amount = ? and username = ?";
		when(input.getUserInput()).thenReturn("");
		user.setUsername("testUser");
		car.setVin("testVin");
		car.setPayment("60000");
		try {
			when(conn.prepareStatement(sql)).thenReturn(stmt6);
			dao.setConn(conn);
			dao.rejectOfferDao(user, car);
			Mockito.verify(stmt6).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void removeCarTest() {
		String sql = "delete from carlot where vin = ?";
		Car car = new Car();
		DAO dao = new DAO();
		when(input.getUserInput()).thenReturn("");
		car.setVin("testVin");
		try {
			when(conn.prepareStatement(sql)).thenReturn(stmt4);
			dao.setConn(conn);
			dao.removeCarDao(car);
			Mockito.verify(stmt4).executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public P0JunitTesting() throws SQLException {
		super();
	}

}
