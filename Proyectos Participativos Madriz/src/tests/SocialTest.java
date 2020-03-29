package tests;

import java.util.*;
import projects.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import entities.individuals.*;
import es.uam.eps.sadp.grants.GrantRequest.ProjectKind;
import entities.*;

public class SocialTest {
	
	private Social projectS;
	private User voterS;
	
	@Before
	public void setUp() throws Exception {
		Date date = new Date();
		voterS = new User("Julio", "hello", "28036512C");		
		projectS = new Social("Increase pensions", "Notable improvement of retirement pensions", 500000.00 , date, voterS, ScopeType.national, "Retirees", "picture");
		projectS.addFollower(voterS);
	}
	
	@Test
	public void testReject() {

		projectS.reject();
		
		Notification not = projectS.getFollowers().get(0).getNotifications().get(0);
		assertEquals("Rejection", not.getTitle());
		assertEquals("The project " + projectS.getTitle() + " has been rejected.", not.getText());
		
		/* We eliminate any message sent before by any other @Test*/
		projectS.getFollowers().get(0).getNotifications().remove(0);
	}

	@Test
	public void testSend() {

		projectS.send();
		
		Notification not = projectS.getFollowers().get(0).getNotifications().get(0);
		assertEquals("Sent", not.getTitle());
		assertEquals("The project " + projectS.getTitle() + " has been sent to validation.", not.getText());
		
		/* We eliminate any message sent before by any other @Test*/
		projectS.getFollowers().get(0).getNotifications().remove(0);
	}

	@Test
	public void testSupport() {
		
		User voterII = new User("Julan", "hello", "28034542C");
		projectS.support(voterII);
		assertEquals(true, projectS.getVoters().contains(voterII));
		assertEquals(true, voterII.getVotedProjects().contains(projectS));
	}

	@Test
	public void testFinanciate() {


		projectS.financiate(25000.00);
		
		Notification not = projectS.getFollowers().get(0).getNotifications().get(0);
		assertEquals("Financing", not.getTitle());
		assertEquals("The project " + projectS.getTitle() + " has been financiated with " + projectS.getBudget() + "euros.", not.getText());
		
		/* We eliminate any message sent before by any other @Test*/
		projectS.getFollowers().get(0).getNotifications().remove(0);
	}

	@Test
	public void testCountVotes() {
		User representative = new User("Antonio", "bye", "39036520H");
		Collective collective = new Collective("Go retirees", "fighting for retirees rights", representative);
		
		collective.join(voterS);
		
		/*VoterS is the creator of the ProjectS project so it is already in the
		 * voters list of the project. With this test we will check if the vote 
		 * of Julio(voterS) is counted twice.
		 */
		
		collective.addVotedProject(projectS);
		projectS.support(collective);
		
		assertEquals(2, projectS.countVotes());
		
		Notification not = collective.getMembers().get(0).getNotifications().get(0);
		assertEquals("New voted project.", not.getTitle());
		assertEquals("The collective " + collective.getName() + " now supports " + projectS.getTitle(), not.getText());
		projectS.getFollowers().get(0).getNotifications().remove(0);
		collective.getMembers().get(0).getNotifications().remove(0);
	}

	@Test
	public void testHasExpired() {

		int maxInactivity = 10;
		
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
		/* We eliminate any message sent before by any other @Test*/
		projectS.getFollowers().get(0).getNotifications().remove(0);
		
	}
	
	@Test
	public void testGetExtraData() {
		assertEquals(String.valueOf(projectS.getCost()) + String.valueOf(projectS.getCreationDate()) + String.valueOf(projectS.getLastVote()) + String.valueOf(projectS.getCreator()) + String.valueOf(projectS.getScope()) + projectS.getGroup() +projectS.getPicture(), projectS.getExtraData());
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
