package modelo.tests;

import java.util.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import es.uam.eps.sadp.grants.GrantRequest.ProjectKind;
import modelo.entities.*;
import modelo.entities.individuals.*;
import modelo.projects.*;

public class InfrastructuralTest {
	
	private Infrastructural projectI;
	private User userI;
	private Collective collectiveI;
	
	@Before
	public void setUp() throws Exception {
		Date date = new Date();
		userI = new User("Julio", "hello", "28036512C");
		collectiveI = new Collective("New Collective", "Interesting Collective", userI);
		District district = new District("Tres Cantos");
		District district1 = new District("Alcobendas");
		ArrayList<District> affectedDistricts = new ArrayList<District>();
		affectedDistricts.add(district);
		affectedDistricts.add(district1);
		projectI = new Infrastructural("Refugee center", "New center to embrace refugees", 1000000.00 , date, userI, affectedDistricts, "scheme", "Madrid");
		projectI.addFollower(userI);
	}
	
	@Test
	public void testReject() {
		projectI.reject();
		
		Notification not = projectI.getFollowers().get(0).getNotifications().get(0);
		assertEquals("Rejection", not.getTitle());
		assertEquals("The project " + projectI.getTitle() + " has been rejected.", not.getText());
		/* We eliminate the notification sent in this test to avoid any +
		 * error in the next ones*/
		for(int i = 0; i < projectI.getFollowers().size(); i++) {
			projectI.getFollowers().get(i).removeNotification(not);
		}	}

	@Test
	public void testSend() {
		try {
			projectI.send();
		}
		catch(Exception ex) {
			System.out.println(ex);
			testSend();
		}
		
		Notification not = projectI.getFollowers().get(0).getNotifications().get(0);
		assertEquals("Sent", not.getTitle());
		assertEquals("The project " + projectI.getTitle() + " has been sent to validation.", not.getText());
		/* We eliminate the notification sent in this test to avoid any +
		 * error in the next ones*/
		for(int i = 0; i < projectI.getFollowers().size(); i++) {
			projectI.getFollowers().get(i).removeNotification(not);
		}
	}

	@Test
	public void testSupport() {
		User voterII = new User("Julan", "hello", "28034542C");
		User voterI = new User("Julian", "hellos", "28056542C");

		projectI.support(voterII);
		projectI.support(collectiveI);
		assertEquals(true, projectI.getVoters().contains(voterII));
		assertEquals(true, voterII.getVotedProjects().contains(projectI));
		assertEquals(true, collectiveI.getSupportedProjects().contains(projectI));
		/*But if we dont support the project with voter2...*/
		assertEquals(false, voterI.getVotedProjects().contains(projectI));
	}

	@Test
	public void testFinanciate() {
		projectI.financiate(25000.00);
		
		Notification not = projectI.getFollowers().get(0).getNotifications().get(0);
		assertEquals("Financing", not.getTitle());
		assertEquals("The project " + projectI.getTitle() + " has been financiated with " + projectI.getBudget() + "euros.", not.getText());
		/* We eliminate the notification sent in this test to avoid any +
		 * error in the next ones*/
		for(int i = 0; i < projectI.getFollowers().size(); i++) {
			projectI.getFollowers().get(i).removeNotification(not);
		}
	}

	@Test
	public void testCountVotes() throws Exception{
		User representative = new User("Antonio", "bye", "39036520H");
		Collective collective = new Collective("Go retirees", "fighting for retirees rights", representative);
		
		collective.join(userI);
		
		/*userI is the creator of the ProjectI project so it is already in the
		 * voters list of the project. With this test we will check if the vote 
		 * of Julio(userI) is counted twice.
		 */
		
		collective.addVotedProject(projectI);
		projectI.support(collective);
		
		assertEquals(2, projectI.countVotes(), 0);
		
		Notification not = collective.getMembers().get(0).getNotifications().get(0);
		assertEquals("New voted project.", not.getTitle());
		assertEquals("The collective " + collective.getName() + " now supports " +projectI.getTitle(), not.getText());
		/* We eliminate the notification sent in this test to avoid any +
		 * error in the next ones*/
		for(int i = 0; i < projectI.getFollowers().size(); i++) {
			projectI.getFollowers().get(i).removeNotification(not);
		}
	}

	@Test
	public void testHasExpired() {

		int maxInactivity = 10;
		int maxInactivityAux = 100000;

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2020);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date date = cal.getTime();
		
		projectI.setLastVote(date);
		
		assertEquals(true, projectI.hasExpired(maxInactivity));
		
		Notification not = projectI.getFollowers().get(0).getNotifications().get(0);
		assertEquals("Expired", not.getTitle());
		assertEquals("The project " + projectI.getTitle() + " has been expired.", not.getText());
		/*If we set maxInactivityAux as the last vote date, it wont
		 * expire until 100000 days from today;
		 */
		projectI.setLastVote(date);
		
		assertEquals(projectI.hasExpired(maxInactivityAux), false);
		/* We eliminate the notification sent in this test to avoid any +
		 * error in the next ones*/
		for(int i = 0; i < projectI.getFollowers().size(); i++) {
			projectI.getFollowers().get(i).removeNotification(not);
		}	}
	
	@Test
	public void testGetExtraData() {
		assertEquals(String.valueOf(projectI.getCost()) + String.valueOf(projectI.getCreationDate()) + String.valueOf(projectI.getLastVote()) + String.valueOf(projectI.getCreator()) + projectI.getScheme() + projectI.getLocation(), projectI.getExtraData());
	}
	
	@Test
	public void testGetProjectKind() {
		assertEquals(ProjectKind.Infrastructure, projectI.getProjectKind());
	}
	
	@Test
	public void testGetProjectTitle() {
		assertEquals(projectI.getTitle(), projectI.getProjectTitle());
	}
	
	@Test
	public void testGetProjectDescription() {
		assertEquals(projectI.getDescription(), projectI.getProjectDescription());
	}
	
}
