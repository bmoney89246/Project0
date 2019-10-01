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
import p0.service.Register;

@RunWith(MockitoJUnitRunner.class)
public class TheSystemTest {

	@Mock
	TheSystem sys = mock(TheSystem.class);
	Register reg = mock(Register.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		sys = new TheSystem();
		reg = new Register();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void TheSystemTest() {
		//type 1
		assertTrue(sys.LoginOrRegister());
		//type 2
		assertFalse(sys.LoginOrRegister());
		//type 2
		assertTrue(sys.CustomerOrEmployee());
		//type 1
		assertFalse(sys.CustomerOrEmployee());
	}
	
	@Test
	public void Register() {
		//type bmoney
		//assertFalse(reg.Registration());
		
		//type asdfg, asdfg
		//assertTrue(reg.Registration());
		
		//type bmoney
		//assertEquals("bmoney", reg.setUsername(),"bmoney");
	}

}
