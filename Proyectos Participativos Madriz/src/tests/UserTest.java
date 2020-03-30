package tests;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
import entities.Collective;
import entities.individuals.*;
import projects.*;

public class UserTest {	
	private User u1;
	private User u2;
	private User u3;
	private User u4;
	private User u5;
	private User u6;
	private Collective c1;
	private Collective c2;
	private Collective c3;
	private Collective c4;
	private Collective c5;
	private Collective c6;
	private Collective c7;
	private Collective c8;
	private Project p1;
	private Project p2;
	
	@Before
	public void setup() throws Exception {
		u1 = new User("Luis", "qwerty", "0123456H");
		u2 = new User("Juan", "poiuy", "6543210H");
		u3 = new User("Antonio", "bye", "76589395S");
		u4 = new User("Luis", "bye", "1234567A");
		u5 = new User("Sara", "bye", "1234567B");
		u6 = new User("Jaime", "bye", "1234567C");
		c1 = new Collective("Colectivo1", "poco que contar", u1);
		c2 = new Collective("Colectivo2", "poco que contar", u2);
		c3 = new Collective("Colectivo3", "Esto es un colectivo", u1);
		c4 = new Collective("Colectivo4", "poco que contar", u1);
		c5 = new Collective("Sports", "Sports lovers", u3);
		c6 = new Collective("Fooball", "Football lovers", u4, c5);
		c7 = new Collective("Tennis", "Tennis lovers", u5, c5);
		c8 = new Collective("Real Madrid", "Real Madrid lovers", u6, c6);
		Date d1 = new Date();
		p1 = new Infrastructural("Poryecto1", "Este proyecto es el proyecto 1", 1111.11, d1, u1, "nose", "Getafe");
		p2 = new Infrastructural("Poryecto2", "Este proyecto es el proyecto 2", 2222.22, d1, c1, "nose", "Madrid");		
	}
	
	@Test
	public void testPrint() {
		assertEquals(u1.toString(), "Name: Luis. Nif: 0123456H");
		assertEquals(u2.toString(), "Name: Juan. Nif: 6543210H");
	}
	
	@Test
	public void testLogin() {
		assertEquals(u1.login("asdf", "sdjfs"), false);
		assertEquals(u1.login("Luis", "sdjfs"), false);
		assertEquals(u1.login("asdf", "qwerty"), false);
		assertEquals(u1.login("0123456H", "qwerty"), true);
		assertEquals(u2.login("0123456H", "qwerty"), false);
	}
	
	@Test
	public void testBanning() {
		//we add vote p1 with u2 twice, the second time it returns error
		assertEquals(u2.getBanned(), false);
		assertEquals(u2.ban("I am angry with you", 1), true);
		assertEquals(u2.ban("I am angry with you", 1), false);
		//should be banned now and if we try to unban it returns false and we cannot login
		assertEquals(u2.getBanned(), true);
		assertEquals(u2.tryUnban(), false);
		assertEquals(u2.login("6543210H", "poiuy"), false);
		//we manually set the unban date (not recommended) and now we can unban u2 and login
		u2.setUnbanDate(Calendar.getInstance());
		assertEquals(u2.tryUnban(), true);
		assertEquals(u2.getBanned(), false);
		assertEquals(u2.login("6543210H", "poiuy"), true);
	}
	
	@Test
	public void testCount() {
		assert(u1.ban("hey", 1));
		assert(u1.count().isEmpty());
		u1.setBanned(false);
		assert(u1.count().contains(u1));
	}
	
	@Test
	public void testVotedProjects(){
		//we add vote p1 with u2 twice, the second time it returns error
		assertEquals(u2.addVotedProject(p1), true);
		assertEquals(u2.addVotedProject(p1), false);
		//as it is the creator, u1 has already the project in his voted lists, and addVotedProjects also returns false
		assertEquals(u1.getVotedProjects().contains(p1), true);
		assertEquals(u1.addVotedProject(p1), false);
	}
	
	@Test
	public void testFollowingProjects() {
		assert(u1.addFollowedProject(p1));
		assert(u1.addFollowedProject(p2));
		assert(!u1.addFollowedProject(p2));
		assert(u1.getFollowedProjects().contains(p1));
		assert(u1.getFollowedProjects().contains(p2));
		assert(u1.removeFollowedProject(p1));
		assert(!u1.removeFollowedProject(p1));
		assert(u1.removeFollowedProject(p2));
		assert(u1.getFollowedProjects().isEmpty());
	}
	
	@Test
	public void testCollectives() {
		//u1 is already in c1 because he created it
		assert(!u1.enterCollective(c1));
		assert(u1.enterCollective(c2));
		assert(c2.getMembers().contains(u1));
		assert(u1.exitCollective(c2));
		assert(!c2.getMembers().contains(u1));
		//u1 cannot leave c1 because he created it
		assert(!u1.exitCollective(c1));	
		
		// User can enter in a descendant collective of his collectives.
		assert(u3.enterCollective(c8));
		// User cannot enter in a father collective of his collectives.
		assert(!u4.enterCollective(c5));
		assert(u4.enterCollective(c8));
		// Users can join to the siblings of his collectives.
		assert(u4.enterCollective(c7));
	}
	
	@Test
	public void testCreateCollective() {
		//u1 is already c1's representative as specified when created c1
		assert(!u1.createCollective(c1));
		//the same collective can be in two representedCollectives lists. 
		//That is why this function must only be used in the Collective's constructor.
		assert(u1.createCollective(c2)); 
		assert(u1.getRepresentedCollectives().contains(c2));
		assert(u2.getRepresentedCollectives().contains(c2));		
	}
	
	@Test
	public void testCreateProject() {
		//u1 is already c1's representative as specified when created c1
		assert(!u1.addCreatedProject(p1));
		//the same project can be in two representedCollectives lists in Voters. 
		//That is why this function must only be used in the Project's constructor.
		assert(u1.addCreatedProject(p2)); 
		assert(c1.getCreatedProjects().contains(p2));
		assert(u1.getCreatedProjects().contains(p2));		
	}

	@Test
	public void testNotifications() {
		Notification n3 = new Notification("n3", "I am n3");
		Notification n4 = new Notification("n4", "I am n4");
		assert(u1.addNotification(n3));
		assert(u1.addNotification(n4));
		assert(u1.getNotifications().contains(n3));
		assert(u1.getNotifications().contains(n4));
		assert(!u1.addNotification(n3));
		assert(u1.removeNotification(n3));
		assert(!u1.getNotifications().contains(n3));
	}	
}