package p0.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import p0.pojos.User;
import p0.service.Login;
import p0.service.TheSystem;

@RunWith(MockitoJUnitRunner.class)
public class TheSystemTest2 {

	@Mock
	//Login login;
	//PcUserService mock = org.mockito.Mockito.mock(PcUserService.class);
	Login login = mock(Login.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		login = new Login();
	}

	@After
	public void tearDown() throws Exception {
	}

	public class TheSystemTest {

	}

	@Test
	public void UserImpl() {
		User user = new User();
		User user2 = new User();
		user2.setUsername("Bmoney");
		user2.setPassword("Password");
		//when(TheSystem.in.nextLine()).thenReturn("Bmoney");
		when(login.InitialLogin(user)).thenReturn(user2);
		assertEquals("Should be Bmoney, Password", user.getUsername(), login.InitialLogin(user));
		assertEquals("Should be Bmoney, Password", user.getPassword(), login.InitialLogin(user));
	}

}
