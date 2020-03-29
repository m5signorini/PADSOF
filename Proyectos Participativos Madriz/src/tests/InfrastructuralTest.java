package tests;

import java.util.*;
import projects.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import entities.individuals.*;
import es.uam.eps.sadp.grants.GrantRequest.ProjectKind;
import entities.*;

class InfrastructuralTest {
	
	private Infrastructural projectI;
	private User voterI;

	
	@Before
	public void setUp() throws Exception {
		Date date = new Date();
		voterI = new User("Julio", "hello", "28036512C");
		projectI = new Infrastructural("Refugee center", "New center to embrace refugees", 1000000.00 , date, voterI, "scheme", "Madrid");
		projectI.addFollower(voterI);
	}
	
	@Test
	void testReject() {
		projectI.reject();
		
		Notification not = projectI.getFollowers().get(0).getNotifications().get(0);
		assertEquals(not.getTitle(), "Rejection");
		assertEquals(not.getText(), "The project " + projectI.getTitle() + " has been rejected.");
		projectI.getFollowers().get(0).getNotifications().remove(0);
	}

	@Test
	void testSend() {
		projectI.send();
		
		Notification not = projectI.getFollowers().get(0).getNotifications().get(0);
		assertEquals(not.getTitle(), "Sent");
		assertEquals(not.getText(), "The project " + projectI.getTitle() + " has been sent to validation.");
		projectI.getFollowers().get(0).getNotifications().remove(0);

	}

	@Test
	void testSupport() {
		User voterII = new User("Julan", "hello", "28034542C");
		projectI.support(voterII);
		assertEquals(projectI.getVoters().contains(voterII), true);
	}

	@Test
	void testFinanciate() {
		projectI.financiate(25000.00);
		
		Notification not = projectI.getFollowers().get(0).getNotifications().get(0);
		assertEquals(not.getTitle(), "Financing");
		assertEquals(not.getText(),  "The project " + projectI.getTitle() + " has been financiated with " + projectI.getBudget() + "euros.");
		projectI.getFollowers().get(0).getNotifications().remove(0);

	}

	@Test
	void testCountVotes() {
		User representative = new User("Antonio", "bye", "39036520H");
		Collective collective = new Collective("Go retirees", "fighting for retirees rights", representative);
		
		collective.addMember(voterI);
		
		/*VoterI is the creator of the ProjectI project so it is already in the
		 * voters list of the project. With this test we will check if the vote 
		 * of Julio(voterI) is counted twice.
		 */
		
		collective.addVotedProject(projectI);
		projectI.support(collective);
		
		assertEquals(projectI.countVotes(), 2, 0);
		
		Notification not = collective.getMembers().get(0).getNotifications().get(0);
		assertEquals(not.getTitle(), "New voted project.");
		assertEquals(not.getText(),  "The colective" + collective.getName() + "now supports" + String.valueOf(projectI));
		projectI.getFollowers().get(0).getNotifications().remove(0);
		collective.getMembers().get(0).getNotifications().remove(0);
	}

	@Test
	void testHasExpired() {

		int maxInactivity = 10;
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2020);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date date = cal.getTime();
		
		projectI.setLastVote(date);
		
		assertEquals(projectI.hasExpired(maxInactivity), true);
		
		Notification not = projectI.getFollowers().get(0).getNotifications().get(0);
		assertEquals(not.getTitle(), "Expired");
		assertEquals(not.getText(),  "The project " + projectI.getTitle() + " has been expired.");
		projectI.getFollowers().get(0).getNotifications().remove(0);
	}
	
	@Test
	void testGetExtraData() {
		assertEquals(projectI.getExtraData(),  String.valueOf(projectI.getCost()) + String.valueOf(projectI.getCreationDate()) + String.valueOf(projectI.getLastVote()) + String.valueOf(projectI.getCreator()) + projectI.getScheme() + projectI.getLocation());
	}
	
	@Test
	void testGetProjectKind() {
		assertEquals(projectI.getProjectKind(), ProjectKind.Infrastructure);
	}
	
	@Test
	void testGetProjectTitle() {
		assertEquals(projectI.getProjectTitle(), projectI.getTitle());
	}
	
	@Test
	void testGetProjectDescription() {
		assertEquals(projectI.getProjectDescription(), projectI.getDescription());
	}
	
	@Test
	void testGetRequestedAmount() {
		double budget = 100000.00;
		
		projectI.financiate(budget);
		projectI.getFollowers().get(0).getNotifications().remove(0);
		assertEquals(projectI.getRequestedAmount(), projectI.getBudget(), 0);
	}
	
}
