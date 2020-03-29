package tests;

import java.util.*;

import projects.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import entities.individuals.*;
import entities.*;

public class CollectiveTest {
	
	private Collective collective;
	
	@Before
	public void setUp() throws Exception {
		User representative = new User("Julio", "hello", "28036512C");
		collective = new Collective("Sports", "Sports lovers", representative);
	}

	@Test
	public void testJoin() {
		User u = new User("Antonio", "bye", "76589395S");
		collective.join(u);
		
		assertEquals(true, collective.getMembers().contains(u));
	}
	
	@Test
	public void testLeave() {
		User u = new User("Antonio", "bye", "76589395S");
		collective.join(u);
		collective.leave(u);
		
		assertEquals(false, collective.getMembers().contains(u));
	}

	@Test
	public void testAddCreatedProject() {
		Date date = new Date();
		User user = new User("Julian", "hellos", "28036512C");
		Project project = new Social("Increase pensions", "Notable improvement of retirement pensions", 500000.00 , date, user, ScopeType.national, "Retirees", "picture");
		collective.addCreatedProject(project);
		
		assertEquals(true, collective.getCreatedProjects().contains(project));
		
		/*If we try to add it again it will return false*/
		assertEquals(false, collective.getCreatedProjects().contains(project));

	}

	@Test
	public void testCount() {
		User u = new User("Antonio", "bye", "76589395S");
		collective.join(u);
		Set<User> s = collective.count();
		
		assertEquals(collective.getMembers().size(), s.size());
	}


	@Test
	public void testAddVotedProject() {
		Date date = new Date();
		User user = new User("Julian", "hellos", "28036512C");
		Project project = new Social("Increase pensions", "Notable improvement of retirement pensions", 500000.00 , date, user, ScopeType.national, "Retirees", "picture");
		collective.addVotedProject(project);
		
		assertEquals(true, collective.getSupportedProjects().contains(project));
		
		/*If we try to add it again it will return false*/
		assertEquals(false, collective.getSupportedProjects().contains(project));
	}

	@Test
	public void testAddChildCollective() {
		User user = new User("Julian", "hellos", "28036512C");
		
		Collective aux = new Collective("Soccer", "Soccer Lovers", user);
		
		/*If I try to add as a child collective a collective itself, it should return false*/
		assertEquals(true, collective.addChildCollective(collective));

		/*If I try to add as a collective child of collective aux it should return true*/
		assertEquals(true, collective.addChildCollective(aux));
		
		/*But if I try to add as a collective child of aux collective now it should return false*/
		assertEquals(true, aux.addChildCollective(collective));
		
		/*Collective already contains aux, so i cant add it as a child again*/
		assertEquals(true, collective.addChildCollective(aux));

	}

	@Test
	public void testGetDescendantCollectives() {
		User user = new User("Julian", "hellos", "28036512C");
		
		Collective aux = new Collective("Soccer", "Soccer Lovers", user);
		Collective aux1 = new Collective("Tennis", "Tennis Lovers", user);
		
		collective.addChildCollective(aux);
		collective.addChildCollective(aux1);
		
		Set<Collective> s = collective.getDescendantCollectives();
		
		assertEquals(true, s.equals(collective.getChildCollectives()));
	}

}
