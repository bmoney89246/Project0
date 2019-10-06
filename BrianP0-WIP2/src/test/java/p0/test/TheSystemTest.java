package p0.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import p0.pojos.FileInfo;
import p0.pojos.Input;
import p0.pojos.User;
import p0.service.DAO;
import p0.service.TheSystem;

@RunWith(MockitoJUnitRunner.class)
public class TheSystemTest {

	@Mock
	Input input;
	@Mock
	User user;
	@Mock
	DAO dao;
	@Mock
	FileInfo fileInfo;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void LoginOrRegisterTest() {
//		user = new User();
//		when(input.getUserInput()).thenReturn("1");
//		TheSystem.LoginOrRegister(user, input);
//	    assertEquals("1", user.getLoginOrRegisterChoice());
//		when(input.getUserInput()).thenReturn("2");
//		TheSystem.LoginOrRegister(user, input);
//	    assertEquals("2", user.getLoginOrRegisterChoice());
	}

	@Test
	public void CustomerOrEmployeeTest() {
//		user = new User();
//		when(input.getUserInput()).thenReturn("1");
//		TheSystem.CustomerOrEmployee(user, input);
//	    assertEquals("1", user.getCustomerOrEmployeeChoice());
//		when(input.getUserInput()).thenReturn("2");
//		TheSystem.LoginOrRegister(user, input);
//	    assertEquals("2", user.getCustomerOrEmployeeChoice());
	}
	
	@Test
	public void RegisterTest() {
		//user = new User();
//		dao = new DAO();
//		FileInfo fileInfo = new FileInfo();
//		when(input.getUserInput()).thenReturn("bmoney");
//		File file = new File(".//src//main//resources//accounts//" + input.getUserInput() + ".dat");
//		when(fileInfo.getFile()).thenReturn(null);
//		assertTrue(dao.fileExists(file));
//		file = new File(".//src//main//resources//accounts//" + input.getUserInput() + ".dat");
//		when(input.getUserInput()).thenReturn("asd123");
//		assertFalse(dao.fileExists(file));
	}
	@Test
	public void LoginTest() {
//		//user = new User();
//		dao = new DAO();
//		FileInfo fileInfo = new FileInfo();
//		when(input.getUserInput()).thenReturn("mwxdydkchvh1");
//		File file = new File(".//src//main//resources//accounts//" + input.getUserInput() + ".dat");
//		when(fileInfo.getFile()).thenReturn(null);
//		assertTrue(dao.fileExists(file));
//		file = new File(".//src//main//resources//accounts//" + input.getUserInput() + ".dat");
//		when(input.getUserInput()).thenReturn("asd123");
//		assertFalse(dao.fileExists(file));
	}
	
	
	

}
