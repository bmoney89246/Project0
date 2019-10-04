package p0.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

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
	//TheSystem sys;
//@Mock
//TheSystem sys;
//	Register reg = mock(Register.class);
InputStream in;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
//sys = new TheSystem();
//		reg = new Register();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void TheSystemT() {
	    String input = "1";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
//	    assertTrue(TheSystem.LoginOrRegister());
//	    input = "2";
//	    System.setIn(in);
//	    assertFalse(TheSystem.LoginOrRegister());
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
