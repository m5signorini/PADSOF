package modelo.tests;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import modelo.entities.*;
import modelo.entities.individuals.*;
import modelo.functionalities.Application;
import modelo.projects.*;

public class ApplicationTest {
	
	private Application app;
	private User creator;
	
	private List<User> usersU;
	private List<User> usersB;
	private List<User> usersR;
	
	private List<Project> projectsPending;
	private List<Project> projectsDenied;
	private List<Project> projectsPublic;
	private List<Project> projectsExpired;
	private List<Project> projectsRejected;
	private List<Project> projectsFinanciated;
	
	private List<Collective> collectives;

	@Before
	public void setUp() throws Exception {
		Admin admin = new Admin("admin");
		creator = new User("Creator", "0", "0");
		usersU = new ArrayList<User>();
		usersB = new ArrayList<User>();
		usersR = new ArrayList<User>();
		projectsPending = new ArrayList<Project>();
		projectsDenied = new ArrayList<Project>();
		projectsPublic = new ArrayList<Project>();
		projectsExpired = new ArrayList<Project>();
		projectsRejected = new ArrayList<Project>();
		projectsFinanciated = new ArrayList<Project>();
		collectives = new ArrayList<Collective>();
		
		usersU.add(new User("TesterU","1","1"));
		usersB.add(new User("TesterB","2","2"));
		usersR.add(new User("TesterR","3","3"));
		
		projectsPending.add(new Social("Pending", "desc", 10000.0, new Date(), creator, ScopeType.national, "g", "pic"));
		projectsDenied.add(new Social("Denied", "desc", 10000.0, new Date(), creator, ScopeType.national, "g", "pic"));
		projectsPublic.add(new Social("Public", "desc", 10000.0, new Date(), creator, ScopeType.national, "g", "pic"));
		projectsRejected.add(new Social("Rejected", "desc", 10000.0, new Date(), creator, ScopeType.national, "g", "pic"));
		projectsExpired.add(new Social("Expired", "desc", 10000.0, new Date(), creator, ScopeType.national, "g", "pic"));
		projectsFinanciated.add(new Social("Financiated", "desc", 10000.0, new Date(), creator, ScopeType.national, "g", "pic"));
		
		collectives.add(new Collective("C", "desc", creator));
		
		app = Application.getApplication();
		
		app.setAdmin(admin);
		app.setMaxInactivity(2);
		app.setMinSupports(2);
		
		app.getUnregisteredUsers().addAll(usersU);
		app.getBannedUsers().addAll(usersB);
		app.getRegisteredUsers().addAll(usersR);
		    
		app.getDeniedProjects().addAll(projectsDenied);
		app.getRejectedProjects().addAll(projectsRejected);
		app.getFinanciatedProjects().addAll(projectsFinanciated);
		app.getPublicProjects().addAll(projectsPublic);
		app.getExpiredProjects().addAll(projectsExpired);
		app.getPendingProjects().addAll(projectsPending);
		    
		app.getCollectives().addAll(collectives);
	}

	@Test
	public void testWriteToFile() {
		assertTrue(app.writeToFile("data_test"));
	}

	@Test
	public void testReadFromFile() {
		app.writeToFile("data_test");
		app = Application.readFromFile("data_test");
		assertTrue(app != null);
	}

	@Test
	public void testRegister() {
		User u = new User("John", "pwd", "nif");
		assertTrue(app.register(u));
		assertTrue(app.getUnregisteredUsers().contains(u));
	}

	@Test
	public void testLogin() {
		User u = new User("John", "pwd", "nif");
		app.register(u);
		assertFalse(app.login("nif", "pwd"));
		assertTrue(app.login(usersR.get(0).getNif(), usersR.get(0).getPwd()));
	}

	@Test
	public void testLoginAdmin() {
		assertTrue(app.loginAdmin("admin"));
		assertFalse(app.loginAdmin("NotAdmin"));
	}

	@Test
	public void testCreateProject() {
		Project p = new Social("Test", "Desc", 10.0, new Date(), creator, ScopeType.international, "","");
		assertTrue(app.createProject(p));
		assertTrue(app.getPendingProjects().contains(p));
		assertTrue(app.getPendingProjects().containsAll(projectsPending));
	}

	@Test
	public void testValidateProject() {
		Project p = new Social("Test", "Desc", 10.0, new Date(), creator, ScopeType.international, "","");
		app.createProject(p);
		assertTrue(app.validateProject(p));
		assertTrue(app.getPublicProjects().contains(p));
		assertTrue(app.getPublicProjects().containsAll(projectsPublic));
	}

	@Test
	public void testDenyProject() {
		Project p = new Social("Test", "Desc", 10.0, new Date(), creator, ScopeType.international, "","");
		app.createProject(p);
		assertTrue(app.denyProject(p));
		assertTrue(app.getDeniedProjects().contains(p));
		assertTrue(app.getDeniedProjects().containsAll(projectsDenied));
	}

	@Test
	public void testRejectProject() {
		Project p = new Social("Test", "Desc", 10.0, new Date(), creator, ScopeType.international, "","");
		app.getSentProjects().add(p);
		assertTrue(app.rejectProject(p));
		assertTrue(app.getRejectedProjects().contains(p));
		assertTrue(app.getRejectedProjects().containsAll(projectsRejected));
	}
	
	@Test
	public void testLogout() {
		assertTrue(app.login(usersR.get(0).getNif(), usersR.get(0).getNif()));
		assertTrue(app.getLoggedUser() != null);
		app.logout();
		assertTrue(app.getLoggedUser() == null);
	}

	@Test
	public void testBan() {
		User u = usersR.get(0);
		assertTrue(app.ban(u, "Banned for testing", 7));
		assertTrue(app.getBannedUsers().contains(u));
		assertTrue(app.getRegisteredUsers().contains(u));
	}

	@Test
	public void testValidateUser() {
		User u = usersU.get(0);
		assertTrue(app.validateUser(u));
		assertFalse(app.getUnregisteredUsers().contains(u));
		assertTrue(app.getRegisteredUsers().contains(u));
	}

	@Test
	public void testRejectUser() {
		User u = usersU.get(0);
		assertTrue(app.rejectUser(u));
		assertFalse(app.getUnregisteredUsers().contains(u));
		assertFalse(app.getRegisteredUsers().contains(u));
		assertFalse(app.getBannedUsers().contains(u));
	}

	@Test
	public void testNotifyUserNotification() {
		Notification n = new Notification("Test", "Text");
		assertTrue(app.notifyUser(usersR.get(0), n));
	}

	@Test
	public void testCreateCollective() {
		Collective c = new Collective("Test", "desc", creator);
		assertTrue(app.createCollective(c));
		assertTrue(app.getCollectives().contains(c));
		assertTrue(app.getCollectives().containsAll(collectives));
	}

	@Test
	public void testCalcAffinity() {
		Collective c1 = new Collective("C1", "desc", creator);
		Collective c2 = new Collective("C2", "desc", creator);
		Project p1 = new Social("Test", "Desc", 10.0, new Date(), c1, ScopeType.international, "","");
		Project p2 = new Social("Test", "Desc", 10.0, new Date(), c2, ScopeType.international, "","");
		p1.support(c2);
		p2.support(c1);
		assertTrue(app.calcAffinity(c1, c2) == 1.0);
	}

}
