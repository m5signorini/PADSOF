package modelo.tests;

import java.util.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import es.uam.eps.sadp.grants.GrantRequest.ProjectKind;
import modelo.entities.*;
import modelo.entities.individuals.*;
import modelo.projects.*;

public class SocialTest {
	
	private Social projectS;
	private User userS;
	private Collective collectiveS;

	@Before
	public void setUp() throws Exception {
		Date date = new Date();
		userS = new User("Julio", "hello", "28036512C");	
		collectiveS = new Collective("New Collective", "Interesting Collective", userS);
		projectS = new Social("Increase pensions", "Notable improvement of retirement pensions", 500000.00 , date, userS, ScopeType.national, "Retirees", "picture");
		projectS.addFollower(userS);
	}
	
	@Test
	public void testReject() {

		projectS.reject();
		
		Notification not = projectS.getFollowers().get(0).getNotifications().get(0);
		assertEquals("Rejection", not.getTitle());
		assertEquals("The project " + projectS.getTitle() + " has been rejected.", not.getText());
		
		/* We eliminate the notification sent in this test to avoid any +
		 * error in the next ones*/
		for(int i = 0; i < projectS.getFollowers().size(); i++) {
			projectS.getFollowers().get(i).removeNotification(not);
		}
	}

	@Test
	public void testSend() {

		try {
			projectS.send();
		}
		catch(Exception ex) {
			System.out.println(ex + "\nNow trying again");
			testSend();
			return;
		}
		
		Notification not = projectS.getFollowers().get(0).getNotifications().get(0);
		assertEquals("Sent", not.getTitle());
		assertEquals("The project " + projectS.getTitle() + " has been sent to validation.", not.getText());
		
		/* We eliminate the notification sent in this test to avoid any +
		 * error in the next ones*/
		for(int i = 0; i < projectS.getFollowers().size(); i++) {
			projectS.getFollowers().get(i).removeNotification(not);
		}
	}

	@Test
	public void testSupport() {
		
		User voter1 = new User("Julan", "hello", "28034542C");
		User voter2 = new User("Julian", "hellos", "28056542C");
		
		projectS.support(voter1);
		projectS.support(collectiveS);
		assertEquals(true, projectS.getVoters().contains(voter1));
		assertEquals(true, voter1.getVotedProjects().contains(projectS));
		assertEquals(true, collectiveS.getSupportedProjects().contains(projectS));
		/*But if we dont support the project with voter2...*/
		assertEquals(false, voter2.getVotedProjects().contains(projectS));
	}

	@Test
	public void testFinanciate() {


		projectS.financiate(25000.00);
		
		Notification not = projectS.getFollowers().get(0).getNotifications().get(0);
		assertEquals("Financing", not.getTitle());
		assertEquals("The project " + projectS.getTitle() + " has been financiated with " + projectS.getBudget() + "euros.", not.getText());
		
		/* We eliminate the notification sent in this test to avoid any +
		 * error in the next ones*/
		for(int i = 0; i < projectS.getFollowers().size(); i++) {
			projectS.getFollowers().get(i).removeNotification(not);
		}
	}

	@Test
	public void testCountVotes() throws Exception{
		User representative = new User("Antonio", "bye", "39036520H");
		Collective collective = new Collective("Go retirees", "fighting for retirees rights", representative);
		
		collective.join(userS);
		
		/*userS is the creator of the ProjectS project so it is already in the
		 * voters list of the project. With this test we will check if the vote 
		 * of Julio(userS) is counted twice.
		 */
		
		collective.addVotedProject(projectS);
		projectS.support(collective);
		
		assertEquals(2, projectS.countVotes());
		
		Notification not = collective.getMembers().get(0).getNotifications().get(0);
		assertEquals("New voted project.", not.getTitle());
		assertEquals("The collective " + collective.getName() + " now supports " + projectS.getTitle(), not.getText());
		/* We eliminate the notification sent in this test to avoid any +
		 * error in the next ones*/
		for(int i = 0; i < projectS.getFollowers().size(); i++) {
			projectS.getFollowers().get(i).removeNotification(not);
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
		
		projectS.setLastVote(date);
		
		assertEquals(projectS.hasExpired(maxInactivity), true);
		
		Notification not = projectS.getFollowers().get(0).getNotifications().get(0);
		assertEquals("Expired", not.getTitle());
		assertEquals("The project " + projectS.getTitle() + " has been expired.", not.getText());
		
		/*If we set maxInactivityAux as the last vote date, it wont
		 * expire until 100000 days from today;
		 */
		projectS.setLastVote(date);
		
		assertEquals(projectS.hasExpired(maxInactivityAux), false);
		
		/* We eliminate the notification sent in this test to avoid any +
		 * error in the next ones*/
		for(int i = 0; i < projectS.getFollowers().size(); i++) {
			projectS.getFollowers().get(i).removeNotification(not);
		}

		
	}
	
	@Test
	public void testGetExtraData() {
		assertEquals(String.valueOf(projectS.getCost()) + String.valueOf(projectS.getCreationDate()) + String.valueOf(projectS.getLastVote()) + String.valueOf(projectS.getCreator()) + String.valueOf(projectS.getScope()) + projectS.getGroup() +projectS.getImagePath(), projectS.getExtraData());
	}
	
	@Test
	public void testGetProjectKind() {
		assertEquals(ProjectKind.Social, projectS.getProjectKind());
	}
	
	@Test
	public void testGetProjectTitle() {
		assertEquals(projectS.getTitle(), projectS.getProjectTitle());
	}
	
	@Test
	public void testGetProjectDescription() {
		assertEquals(projectS.getDescription(), projectS.getProjectDescription());
	}

	
}
