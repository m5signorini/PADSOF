package tests;

import java.util.*;

import projects.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import entities.individuals.*;
import es.uam.eps.sadp.grants.GrantRequest.ProjectKind;
import entities.*;

public class InfrastructuralTest {
	
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
	public void testReject() {
		projectI.reject();
		
		Notification not = projectI.getFollowers().get(0).getNotifications().get(0);
		assertEquals("Rejection", not.getTitle());
		assertEquals("The project " + projectI.getTitle() + " has been rejected.", not.getText());
		projectI.getFollowers().get(0).getNotifications().remove(0);
	}

	@Test
	public void testSend() {
		projectI.send();
		
		Notification not = projectI.getFollowers().get(0).getNotifications().get(0);
		assertEquals("Sent", not.getTitle());
		assertEquals("The project " + projectI.getTitle() + " has been sent to validation.", not.getText());
		projectI.getFollowers().get(0).getNotifications().remove(0);

	}

	@Test
	public void testSupport() {
		User voterII = new User("Julan", "hello", "28034542C");
		projectI.support(voterII);
		assertEquals(true, projectI.getVoters().contains(voterII));
		assertEquals(true, voterII.getVotedProjects().contains(projectI));
	}

	@Test
	public void testFinanciate() {
		projectI.financiate(25000.00);
		
		Notification not = projectI.getFollowers().get(0).getNotifications().get(0);
		assertEquals("Financing", not.getTitle());
		assertEquals("The project " + projectI.getTitle() + " has been financiated with " + projectI.getBudget() + "euros.", not.getText());
		projectI.getFollowers().get(0).getNotifications().remove(0);

	}

	@Test
	public void testCountVotes() {
		User representative = new User("Antonio", "bye", "39036520H");
		Collective collective = new Collective("Go retirees", "fighting for retirees rights", representative);
		
		collective.join(voterI);
		
		/*VoterI is the creator of the ProjectI project so it is already in the
		 * voters list of the project. With this test we will check if the vote 
		 * of Julio(voterI) is counted twice.
		 */
		
		collective.addVotedProject(projectI);
		projectI.support(collective);
		
		assertEquals(2, projectI.countVotes(), 0);
		
		Notification not = collective.getMembers().get(0).getNotifications().get(0);
		assertEquals("New voted project.", not.getTitle());
		assertEquals("The colective" + collective.getName() + "now supports" + String.valueOf(projectI), not.getText());
		projectI.getFollowers().get(0).getNotifications().remove(0);
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
		
		projectI.setLastVote(date);
		
		assertEquals(true, projectI.hasExpired(maxInactivity));
		
		Notification not = projectI.getFollowers().get(0).getNotifications().get(0);
		assertEquals("Expired", not.getTitle());
		assertEquals("The project " + projectI.getTitle() + " has been expired.", not.getText());
		projectI.getFollowers().get(0).getNotifications().remove(0);
	}
	
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
	
	@Test
	public void testGetRequestedAmount() {
		double budget = 100000.00;
		
		projectI.financiate(budget);
		projectI.getFollowers().get(0).getNotifications().remove(0);
		assertEquals(projectI.getBudget(), projectI.getRequestedAmount(), 0);
	}
	
}
