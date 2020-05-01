package modelo.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import modelo.entities.*;
import modelo.entities.individuals.*;
import modelo.functionalities.*;
import modelo.projects.*;

import java.util.*;

public class SearchEngineTest {
	
	private Application app;
	
	private Project projToFind;
	private Collective collToFind;
	private User userToFind;

	@Before
	public void setUp() throws Exception {
		Application.clearApplication();
		app = Application.getApplication();
		app.setAdmin(new Admin("admin"));
		app.setMaxInactivity(10);
		app.setMinSupports(10);
		User user = new User("Tester", "123456", "123456");
		
		List<Project> projectPool = new ArrayList<Project>();
		List<Collective> collectivePool = new ArrayList<Collective>();
		List<User> userPool = new ArrayList<User>();
		
		// Collectives Pool
		for(int i = 0; i < 20; i++) {
			collectivePool.add(new Collective(""+i, "Desc", user));
		}
		collToFind = new Collective("20", "Desc", user);
		collectivePool.add(collToFind);
		
		// Users pool
		for(int i = 0; i < 20; i++) {
			userPool.add(new User("Name "+i, "Pwd "+i, "Nif "+i));
		}
		userToFind = new User("Name 20", "Pwd 20", "Nif 20");
		userPool.add(userToFind);
		
		// Projects pool
		ArrayList<District> aff = new ArrayList<District>();
		aff.add(new District("Barajas"));
		for(int i = 0; i < 20; i++) {
			projectPool.add(new Infrastructural(""+i, "Desc", 10.0, new Date(), user, aff, "", ""));
			projectPool.add(new Social(""+i, "Desc", 10.0, new Date(), user, ScopeType.national, "", ""));
		}
		projToFind = new Infrastructural("20", "Desc", 10.0, new Date(), user, aff, "", "");
		projectPool.add(projToFind);
		
		// Set pools for all application
		app.getUnregisteredUsers().addAll(userPool);
		app.getRegisteredUsers().addAll(userPool);
		app.getBannedUsers().addAll(userPool);
		    
		app.getPendingProjects().addAll(projectPool);
		app.getDeniedProjects().addAll(projectPool);
		app.getPublicProjects().addAll(projectPool);
		app.getExpiredProjects().addAll(projectPool);
		app.getFinanciatedProjects().addAll(projectPool);
		app.getRejectedProjects().addAll(projectPool);
		app.getSentProjects().addAll(projectPool);
		    
		app.getCollectives().addAll(collectivePool);
	}

	@Test
	public void testSearchCollectives() {
		List<Collective> found;
		found = app.getSearcher().searchCollectives("2");
		assertTrue(found.contains(collToFind));
	}

	@Test
	public void testSearchUser() {
		User found;
		found = app.getSearcher().searchUser("Nif 20");
		assertSame(found, userToFind);
	}

	@Test
	public void testSearchSentProjects() {
		List<Project> found;
		found = app.getSearcher().searchSentProjects("2");
		assertTrue(found.contains(projToFind));
	}

	@Test
	public void testSearchExpiredProjects() {
		List<Project> found;
		found = app.getSearcher().searchExpiredProjects("2");
		assertTrue(found.contains(projToFind));
	}

	@Test
	public void testSearchPublicProjects() {
		List<Project> found;
		found = app.getSearcher().searchPublicProjects("2");
		assertTrue(found.contains(projToFind));
	}

	@Test
	public void testSearchPendingProjects() {
		List<Project> found;
		found = app.getSearcher().searchPendingProjects("2");
		assertTrue(found.contains(projToFind));
	}

	@Test
	public void testSearchFinanciatedProjects() {
		List<Project> found;
		found = app.getSearcher().searchFinanciatedProjects("2");
		assertTrue(found.contains(projToFind));
	}

	@Test
	public void testSearchDeniedProjects() {
		List<Project> found;
		found = app.getSearcher().searchDeniedProjects("2");
		assertTrue(found.contains(projToFind));
	}

	@Test
	public void testSearchRejectedProjects() {
		List<Project> found;
		found = app.getSearcher().searchRejectedProjects("2");
		assertTrue(found.contains(projToFind));
	}

	@Test
	public void testGetSocials() {
		List<Project> found;
		found = app.getSearcher().searchPublicProjects("2");
		found = app.getSearcher().getSocials(found);
		assertFalse(found.contains(projToFind));
	}

	@Test
	public void testGetInfrastructurals() {
		List<Project> found;
		found = app.getSearcher().searchPublicProjects("2");
		found = app.getSearcher().getInfrastructurals(found);
		assertTrue(found.contains(projToFind));
	}

}
