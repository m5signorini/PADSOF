package tests;

import java.util.*;
import projects.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import entities.individuals.*;
import es.uam.eps.sadp.grants.GrantRequest.ProjectKind;
import entities.*;

class SocialTest {
	
	private Social projectS;
	private Collective collectiveS;

	
	@Before
	public void setUp() throws Exception {
		Date date = new Date();
		User representative = new User("Antonio", "bye", "39036520H");
		collectiveS = new Collective("Go retirees", "fighting for retirees rights", representative);
		projectS = new Social("Increase pensions", "Notable improvement of retirement pensions", 500000.00 , date, collectiveS, ScopeType.national, "Retirees", "picture");
	
		projectS.addFollower(representative);
	}
	
	@Test
	void testReject() {
		projectS.reject();
		
		Notification not = projectS.getFollowers().get(0).getNotifications().get(0);
		assertEquals(not.getTitle(), "Rejection");
		assertEquals(not.getText(), "The project " + projectS.getTitle() + " has been rejected.");
		projectS.getFollowers().get(0).getNotifications().remove(0);
	}

	@Test
	void testSend() {
		projectS.send();
		
		Notification not = projectS.getFollowers().get(0).getNotifications().get(0);
		assertEquals(not.getTitle(), "Sent");
		assertEquals(not.getText(), "The project " + projectS.getTitle() + " has been sent to validation.");
		projectS.getFollowers().get(0).getNotifications().remove(0);

	}

	@Test
	void testSupport() {
		User voterII = new User("Julan", "hello", "28034542C");
		projectS.support(voterII);
		assertEquals(projectS.getVoters().contains(voterII), true);
	}

	@Test
	void testFinanciate() {
		projectS.financiate(25000.00);
		
		Notification not = projectS.getFollowers().get(0).getNotifications().get(0);
		assertEquals(not.getTitle(), "Financing");
		assertEquals(not.getText(),  "The project " + projectS.getTitle() + " has been financiated with " + projectS.getBudget() + "euros.");
		projectS.getFollowers().get(0).getNotifications().remove(0);

	}

	@Test
	void testCountVotes() {
		User voterI = new User("Julio", "hello", "28036512C");
		
		projectS.support(voterI);
		collectiveS.addMember(voterI);
		collectiveS.addVotedProject(projectS);
		projectS.support(collectiveS);
		
		/*VoterI has voted thr project projects as an individual user and 
		 * the colective he belongs to, has supported that project too.
		 * With this test we will check if the vote of Julio(voterI) is counted twice.
		 * The result must be 2. One vote from Julio, and one from 
		 * Antonio, the representative of the collectiveS.
		 */
		
		assertEquals(projectS.countVotes(), 2, 0);
		
		Notification not = collectiveS.getMembers().get(0).getNotifications().get(0);
		assertEquals(not.getTitle(), "New voted project.");
		assertEquals(not.getText(),  "The colective" + collectiveS.getName() + "now supports" + String.valueOf(projectS));
		projectS.getFollowers().get(0).getNotifications().remove(0);
	}

	@Test
	void testHasExpired() {

		int maxInactivity = 10;
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2020);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date date = cal.getTime();
		
		projectS.setLastVote(date);
		
		assertEquals(projectS.hasExpired(maxInactivity), true);
		
		Notification not = projectS.getFollowers().get(0).getNotifications().get(0);
		assertEquals(not.getTitle(), "Expired");
		assertEquals(not.getText(),  "The project " + projectS.getTitle() + " has been expired.");
		projectS.getFollowers().get(0).getNotifications().remove(0);
	}
	
	@Test
	void testGetExtraData() {
		assertEquals(projectS.getExtraData(),  String.valueOf(projectS.getCost()) + String.valueOf(projectS.getCreationDate()) + String.valueOf(projectS.getLastVote()) + String.valueOf(projectS.getCreator()) + String.valueOf(projectS.getScope()) + projectS.getGroup() +projectS.getPicture());
	}
	
	@Test
	void testGetProjectKind() {
		assertEquals(projectS.getProjectKind(), ProjectKind.Social);
	}
	
	@Test
	void testGetProjectTitle() {
		assertEquals(projectS.getProjectTitle(), projectS.getTitle());
	}
	
	@Test
	void testGetProjectDescription() {
		assertEquals(projectS.getProjectDescription(), projectS.getDescription());
	}
	
	@Test
	void testGetRequestedAmount() {
		double budget = 100000.00;
		
		projectS.financiate(budget);
		projectS.getFollowers().get(0).getNotifications().remove(0);
		assertEquals(projectS.getRequestedAmount(), projectS.getBudget(), 0);
	}
	
}
