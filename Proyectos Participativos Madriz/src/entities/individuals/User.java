/**
 * 
 */
package entities.individuals;

import java.io.Serializable;
import java.util.*;
import entities.Collective;
import projects.Project;
import entities.individuals.Notification;

/**
 * @author 
 *
 */
public class User extends Account implements Voter, Serializable {
	protected String nif;
	protected ArrayList<Collective> collectives;
	protected ArrayList<Collective> representedCollectives;
	protected ArrayList<Project> followedProjects;
	protected ArrayList<Project> votedProjects;
	protected ArrayList<Notification> notifications;
	Calendar unbanDate;
	
	public User(String name, String pwd, String nif) {
		super(name, pwd);
		this.nif = nif;
		collectives = new ArrayList<Collective>();
		representedCollectives = new ArrayList<Collective>();
		followedProjects = new ArrayList<Project>();
		votedProjects = new ArrayList<Project>();
	}
	
	public String getNif() {
		return nif;
	}
	
	public User validate() {
		return this;
	}
	
	public User reject() {
		return this;
	}
	
	/* Returns true if the login is correct, false otherwise.
	 * @param nif String containing the nif of the user trying to login.
	 * @param pwd String containing password written by the user trying to login.
	 * @return True if the login is correct, false otherwise.
	 */
	public boolean login(String nif, String pwd) {
		if (nif != this.nif || pwd != this.pwd) {
			return false;
		} else {
			return true;
		}
	}
	
	/* Tries to unban a user.
	 * @return True if the the banned period has finished, false otherwise.
	 */
	public boolean tryUnban() {
		Calendar fecha = Calendar.getInstance();
		if(fecha.compareTo(this.unbanDate) == -1)
			return false;
		return true;
	}
	
	public void unban() {
	}
	
	/* Function that sets the end of the banning period and notifies the user with the reason of the ban.
	 * @param message String containing the reason why the user has been banned.
	 * @param days int representing the number of days the User will be banned.
	 */
	public void ban(String message, int days) {
		Calendar fecha = Calendar.getInstance();
		fecha.add(Calendar.DAY_OF_YEAR, days);
		this.unbanDate = fecha;
		Notification n = new Notification("You have been banned!",message);
		this.getNotified(n);
	}
	
	public void logout() {
		
	}
	
	public void unfollow (Project p) {
		
	}
	
	public boolean readNotification(Notification n) {
		return true;
	}
	
	public void getNotified(Notification n) {
		
	}
	
	/* Returns a set containing the User, in order to be joined with other sets later.
	 * @return A HashSet containing the User.
	 */
	public Set<User> count() {
		Set<User> s = new HashSet<User>();
		if (this.tryUnban())
			s.add(this);		
		return s;
	}


	/* Adds a project to the VotedProjects list.
	 * @param p Project to be added.
	 * @return true in case the project has been added to the list, false if it was already there.
	 */
	public boolean addVotedProject(Project p) {
		if(this.votedProjects.contains(p))
			return false;
		this.votedProjects.add(p);
		return true;
	}
	
	/* Adds a project to the FollowedProjects list.
	 * @param p Project to be added.
	 * @return true in case the project has been added to the list, false if it was already there.
	 */
	public boolean addFollowedProject(Project p) {
		if(this.followedProjects.contains(p))
			return false;
		this.followedProjects.add(p);
		return true;
	}
	
	/* Removes a project to the FollowedProjects list.
	 * @param p Project to be removed.
	 * @return true in case the project has been removed from the list, false if it was not already there.
	 */
	public boolean removeFollowedProject(Project p) {
		if(this.followedProjects.contains(p)) {
			this.followedProjects.remove(followedProjects.indexOf(p));
			return true;
		}
		return false;
	}
	
	/* Adds a collective to the collectives list.
	 * @param c Collective to be added.
	 * @return true in case the collective has been added to the list, false if it was already there.
	 */
	public boolean enterCollective(Collective c) {
		if(this.collectives.contains(c))
			return false;
		this.collectives.add(c);
		return true;
	}
	
	/* Removes a collective to the collectives list.
	 * @param c Collective to be removed.
	 * @return true in case the collective has been removed from to the list, false if it was not already there.
	 */
	public boolean exitCollective(Collective c) {
		if(this.collectives.contains(c)) {
			this.collectives.remove(collectives.indexOf(c));
			c.leave(this);
			return true;
		}
		return false;
	}
	
	
	/* Adds a collective to the representedCollectives list.
	 * @param c Collective to be added.
	 * @return true in case the collective has been added to the list, false if it was already there.
	 */
	public boolean createCollective(Collective c) {
		if(this.representedCollectives.contains(c))
			return false;
		this.representedCollectives.add(c);
		return true;
	}
	
	
	/* Adds a notification to the notifications list.
	 * @param n Notification to be added.
	 * @return true in case the notification has been added to the list, false if it was already there.
	 */
	public boolean addNotification(Notification n) {
		if(this.notifications.contains(n))
			return false;
		this.notifications.add(n);
		return true;
	}
	
	
	/* Removes a notification to the notifications list.
	 * @param notification to be removed.
	 * @return true in case the notification has been removed from the list, false if it was not already there.
	 */
	public boolean removeNotification(Notification n) {
		if(this.notifications.contains(n)) {
			notifications.remove(notifications.indexOf(n));
			return true;
		}
		return false;
	}
}
