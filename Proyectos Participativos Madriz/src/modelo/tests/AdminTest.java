package modelo.tests;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import modelo.entities.individuals.*;


public class AdminTest {
	
	private Admin admin;
	
	@Before
	public void setUp() throws Exception {
		admin = new Admin("Password");
	}
	
	@Test
	public void testToString() {
		assertEquals("Name: Administrator. Password: Password.", admin.toString());
	}
	
	@Test
	public void testLogin() {
		assertEquals(true, admin.login("Administrator", admin.getPwd()));
		/*We wont be able to login if we try to do it with an incorrect password"*/
		assertEquals(false, admin.login("Administrator", "Hola"));
	}

}
