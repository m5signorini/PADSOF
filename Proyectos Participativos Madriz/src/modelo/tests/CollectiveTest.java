package modelo.tests;

import java.util.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import modelo.entities.*;
import modelo.entities.individuals.*;
import modelo.projects.*;

public class CollectiveTest {	
	private Collective collective;
	private User representative;
	private User u1;
	private User u2;
	private User u3;
	private User u4;
	private Collective c1;
	private Collective c2;
	private Collective c3;
	private Collective c4;
	
	@Before
	public void setUp() throws Exception {
		representative = new User("Julio", "hello", "28036512C");
		collective = new Collective("Sports", "Sports lovers", representative);
		u1 = new User("Antonio", "bye", "76589395S");
		u2 = new User("Luis", "bye", "1234567A");
		u3 = new User("Sara", "bye", "1234567B");
		u4 = new User("Juan", "bye", "1234567C");
		c1 = new Collective("Sports", "Sports lovers", u1);
		c2 = new Collective("Fooball", "Football lovers", u2, c1);
		c3 = new Collective("Tennis", "Tennis lovers", u3, c1);
		c4 = new Collective("Real Madrid", "Real Madrid lovers", u4, c2);
	}

	@Test
	public void testJoin() {
		User u = new User("Antonio", "bye", "76589395S");
		
		assertEquals(true, collective.join(u));
		
		/*If we try to add it again it will return false*/
		assertEquals(false, collective.join(u));
		
		/* u4 cannot join c1 because he created c4 (and therefore he is inside it) which is a descendant of c1*/
		assertEquals(c1.join(u4), false);
		/* Users inside  */
		assertEquals(c4.join(u3), true);
	}
	
	@Test
	public void testLeave() {
		User u = new User("Antonio", "bye", "76589395S");
		collective.join(u);
		
		assertEquals(true ,collective.leave(u));
		
		/*If we try to leave the collective again it will return false*/
		assertEquals(false, collective.leave(u));
		
		/*If the person who created the collective tries to leave it will return false,
		 * not letting him leave. */
		assertEquals(false, collective.leave(representative));
	}

	@Test
	public void testAddCreatedProject() {
		Date date = new Date();
		User user = new User("Julian", "hellos", "28036512C");
		Project project = new Social("Increase pensions", "Notable improvement of retirement pensions", 500000.00 , date, user, ScopeType.national, "Retirees", "picture");
		
		assertEquals(true, collective.addCreatedProject(project));
		
		/*If we try to add it again it will return false*/
		assertEquals(false, collective.addCreatedProject(project));

	}

	@Test
	public void testCount() {
		/* Repeated members do not count twice, members of descendant collectives are correctly counted. */
		assert(c4.join(u3));		
		assertEquals(c1.count().size(), 4);
	}


	@Test
	public void testAddVotedProject() {
		Date date = new Date();
		User user = new User("Julian", "hellos", "28036512C");
		Project project = new Social("Increase pensions", "Notable improvement of retirement pensions", 500000.00 , date, user, ScopeType.national, "Retirees", "picture");
		
		assertEquals(true, collective.addVotedProject(project));
		
		/*If we try to add it again it will return false*/
		assertEquals(false, collective.addVotedProject(project));
	}

	@Test
	public void testAddChildCollective() {
		User user = new User("Julian", "hellos", "28036512C");
		
		Collective aux = new Collective("Soccer", "Soccer Lovers", user);
		
		/*If I try to add as a collective child of collective aux it should return true*/
		assertEquals(true, collective.addChildCollective(aux));
		
		/*But if I try to add as a collective child of aux collective now it should return false*/
		assertEquals(false, aux.addChildCollective(collective));
		
		/*Collective already contains aux, so i cant add it as a child again*/
		assertEquals(false, collective.addChildCollective(aux));

	}

	@Test
	public void testGetDescendantCollectives() {
		User user = new User("Julian", "hellos", "28036512C");
		
		Collective aux2 = new Collective("Basket", "Basket Lovers", user);
		Collective aux1 = new Collective("Tennis", "Tennis Lovers", user);
		
		collective.addChildCollective(aux2);
		collective.addChildCollective(aux1);
		
		Set<Collective> s = collective.getDescendantCollectives();
		
		assertEquals(true, s.equals(collective.getChildCollectives()));
	}

}
