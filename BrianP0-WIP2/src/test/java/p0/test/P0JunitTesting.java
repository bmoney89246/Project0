package p0.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import java.sql.Connection;
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
import org.mockito.runners.MockitoJUnitRunner;
import org.postgresql.core.BaseStatement;
import p0.pojos.Car;
import p0.pojos.Input;
import p0.pojos.User;
import p0.service.Customer;
import p0.service.DAO;
import p0.service.Employee;
import p0.service.TheSystem;
import p0.util.ConnectionFactory;

@RunWith(MockitoJUnitRunner.class)
public class P0JunitTesting {
	private DAO dao = new DAO();

	@Mock
	User user;
	
	@Mock
	Car car;

	@Mock
	private Connection conn;
	
	@Spy
	private BaseStatement stmt = (BaseStatement) ConnectionFactory.getConnection().createStatement();

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
		user = new User();
		car = new Car();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void LoginOrRegisterTest() {
		when(Input.getUserInput()).thenReturn("1");
		TheSystem.LoginOrRegister(user);
		assertEquals("1", user.getLoginOrRegisterChoice());
		when(Input.getUserInput()).thenReturn("2");
		TheSystem.LoginOrRegister(user);
		assertEquals("2", user.getLoginOrRegisterChoice());
	}

	@Test
	public void CustomerOrEmployeeTest() {
		user = new User();
		when(Input.getUserInput()).thenReturn("1");
		TheSystem.CustomerOrEmployee(user);
		assertEquals("1", user.getCustomerOrEmployeeChoice());
		when(Input.getUserInput()).thenReturn("2");
		TheSystem.LoginOrRegister(user);
		assertEquals("2", user.getCustomerOrEmployeeChoice());
	}

	@Test
	public void RegisterTest() {
		when(Input.getUserInput()).thenReturn("cuser");
		assertTrue(dao.registerDao(user));
		when(Input.getUserInput()).thenReturn("asd123");
		assertFalse(dao.registerDao(user));
	}

	@Test
	public void LoginTest() {
		when(Input.getUserInput()).thenReturn("cuser");
		assertTrue(dao.registerDao(user));
		when(Input.getUserInput()).thenReturn("asd123");
		assertFalse(dao.registerDao(user));
	}

	@Test
	public void employeeMenuTest() {
		when(Input.getUserInput()).thenReturn("1");
		Employee.employeeMenu(user, dao);
		assertEquals(Input.getCurrentMenu(), "1");
		when(Input.getUserInput()).thenReturn("2");
		Employee.employeeMenu(user, dao);
		assertEquals(Input.getCurrentMenu(), "2");
		when(Input.getUserInput()).thenReturn("3");
		Employee.employeeMenu(user, dao);
		assertEquals(Input.getCurrentMenu(), "3");
		when(Input.getUserInput()).thenReturn("4");
		Employee.employeeMenu(user, dao);
		assertEquals(Input.getCurrentMenu(), "4");
		when(Input.getUserInput()).thenReturn("5");
		Employee.employeeMenu(user, dao);
		assertEquals(Input.getCurrentMenu(), "5");
		when(Input.getUserInput()).thenReturn("6");
		Employee.employeeMenu(user, dao);
		assertEquals(Input.getCurrentMenu(), "6");
	}

	@Test
	public void addCarTest() {
		when(Input.getUserInput()).thenReturn("test1");
		car.setMake("test");
		car.setModel("test");
		car.setPayment("100000");
		car.setYear("2019");
		dao.addCarDao(car);
		try {
			Mockito.verify(stmt).executeUpdate("select * from carlot where vin = ?");
		} catch (SQLException e) {
		}

	}
	
	@Test
	public void removeCarTest() {
		when(Input.getUserInput()).thenReturn("test1");
		dao.addCarDao(car);
		try {
			Mockito.verify(stmt).executeUpdate("delete from carlot where vin = ?");
		} catch (SQLException e) {
		}

	}
	@Test
	public void acceptOfferTest() {
		car.setVin("test1");
		car.setPayment("60000");
		when(Input.getUserInput()).thenReturn("test1");
		dao.acceptOfferDao(car);
		try {
			Mockito.verify(stmt).executeUpdate("update offers set accepted = TRUE where c_id = (select c_id from carlot where vin = ?) and amount = ?");
		} catch (SQLException e) {
		}
	}
	@Test
	public void viewPaymentsTest() {
		dao.viewPaymentsDao();
		try {
			Mockito.verify(stmt).executeUpdate("select vin, c.make, c.model, c.year, o.username, o.amount from carlot c inner join offers o on c.c_id = o.c_id where o.accepted = TRUE");
		} catch (SQLException e) {
		}

	}
	
	@Test
	public void customerMenuTest() {
		when(Input.getUserInput()).thenReturn("1");
		Customer.customerMenu(user, dao);
		assertEquals(Input.getCurrentMenu(), "1");
		when(Input.getUserInput()).thenReturn("2");
		Customer.customerMenu(user, dao);
		assertEquals(Input.getCurrentMenu(), "2");
		when(Input.getUserInput()).thenReturn("3");
		Customer.customerMenu(user, dao);
		assertEquals(Input.getCurrentMenu(), "3");
		when(Input.getUserInput()).thenReturn("4");
		Customer.customerMenu(user, dao);
		assertEquals(Input.getCurrentMenu(), "4");
		when(Input.getUserInput()).thenReturn("5");
		Customer.customerMenu(user, dao);
		assertEquals(Input.getCurrentMenu(), "5");
		when(Input.getUserInput()).thenReturn("6");
		Customer.customerMenu(user, dao);
		assertEquals(Input.getCurrentMenu(), "6");
	}
	@Test
	public void makeOfferTest() {
		when(Input.getUserInput()).thenReturn("test1");
		dao.makeOfferDao(user, car);
		car.setVin("test1");
		car.setPayment("60000");
		user.setUsername("testuser");
		try {
			Mockito.verify(stmt).executeUpdate("insert into offers (c_id, username, amount, accepted) values((select c_id from carlot where vin = ?), ?, ?, FALSE)");
		} catch (SQLException e) {
		}
	}
	@Test
	public void viewLotTest() {
		when(Input.getUserInput()).thenReturn("test1");
		dao.viewLotDao();
		car.setVin("test1");
		car.setPayment("60000");
		user.setUsername("testuser");
		try {
			Mockito.verify(stmt).executeUpdate("select c.vin, c.make, c.model, c.year from carlot c inner join offers o on c.c_id = o.c_id where o.accepted = FALSE");
		} catch (SQLException e) {
		}
	}
	@Test
	public void viewOwnedCarsTest() {
		user.setUsername("testuser");
		dao.viewOwnedCarsDao(user);
		try {
			Mockito.verify(stmt).executeUpdate("select c.vin, c.make, c.model, c.year from carlot c inner join offers o on c.c_id = o.c_id where o.username = ?");
		} catch (SQLException e) {
		}
	}
	@Test
	public void viewRemPaymentsTest() {
		user.setUsername("testuser");
		dao.viewRemPaymentsDao(user);
		try {
			Mockito.verify(stmt).executeUpdate("select c.vin, c.make, c.model, c.year, o.amount from carlot c inner join offers o on c.c_id = o.c_id where o.username = ?");
		} catch (SQLException e) {
		}
	}

	public P0JunitTesting() throws SQLException {
		super();
	}

}
