/**
 * 
 */
package modelo.entities.individuals;

import java.io.*;
import java.util.*;

import modelo.entities.Collective;
import modelo.entities.individuals.Notification;
import modelo.projects.Project;

/**
 * The User class represents the account of a user and all the objects associated with it, 
 * including projects, collectives and several attributes such as name, password...
 * This class is allowed to create, vote and follow projects, create, enter and exit
 * collectives, can be temporarily banned and can receive messages through notifications.
 * @author Pedro Urbina Rodriguez
 * 
 */
public class User extends Account implements Voter, Serializable{
	protected String nif;
	protected ArrayList<Collective> collectives;
	protected ArrayList<Collective> representedCollectives;
	protected ArrayList<Project> followedProjects;
	protected ArrayList<Project> votedProjects;
	protected ArrayList<Project> createdProjects;
	protected ArrayList<Notification> notifications;
	private Calendar unbanDate;
	boolean banned = false;
	
	public User(String name, String pwd, String nif) {
		super(name, pwd);
		this.nif = nif;
		collectives = new ArrayList<Collective>();
		representedCollectives = new ArrayList<Collective>();
		followedProjects = new ArrayList<Project>();
		votedProjects = new ArrayList<Project>();
		createdProjects = new ArrayList<Project>();
		this.notifications= new ArrayList<Notification>();
	}
		
	public ArrayList<Collective> getCollectives() {
		return collectives;
	}
	public void setCollectives(ArrayList<Collective> collectives) {
		this.collectives = collectives;
	}
	public ArrayList<Collective> getRepresentedCollectives() {
		return representedCollectives;
	}
	public void setRepresentedCollectives(ArrayList<Collective> representedCollectives) {
		this.representedCollectives = representedCollectives;
	}
	public ArrayList<Project> getFollowedProjects() {
		return followedProjects;
	}
	public void setFollowedProjects(ArrayList<Project> followedProjects) {
		this.followedProjects = followedProjects;
	}
	public ArrayList<Project> getVotedProjects() {
		return votedProjects;
	}
	public void setVotedProjects(ArrayList<Project> votedProjects) {
		this.votedProjects = votedProjects;
	}
	public ArrayList<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(ArrayList<Notification> notifications) {
		this.notifications = notifications;
	}
	public Calendar getUnbanDate() {
		return unbanDate;
	}
	public void setUnbanDate(Calendar unbanDate) {
		this.unbanDate = unbanDate;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getNif() {
		return nif;
	}
	public boolean getBanned() {
		return banned;
	}
	public void setBanned(boolean b) {
		this.banned = b;
	}
	public ArrayList<Project> getCreatedProjects() {
		return this.createdProjects;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Name: " + this.name + ". Nif: "+ this.nif);
		return sb.toString();
	}
	
	/**
	 * Extended toString.
	 * @return A String containing all the information of the User.
	 */
	public String printAllInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append("Name: " + this.name + ". Nif: "+ this.nif + ". Password: " + this.pwd);
		if(!this.collectives.isEmpty()) {
			sb.append("\nBelongs to collectives: ");
			for(Collective c: this.collectives) {
				sb.append(c.getName() + ", ");
			}
		}
		if(!this.representedCollectives.isEmpty()) {
			sb.append("\nHas created collectives: ");
			for(Collective c: this.representedCollectives) {
				sb.append(c.getName() + ", ");
			}
		}
		if(!this.followedProjects.isEmpty()) {
			sb.append("\nHas followed this projects: ");
			for(Project p: this.followedProjects) {
				sb.append(p.getTitle() + ", ");
			}
		}
		if(!this.votedProjects.isEmpty()) {
			sb.append("\nHas voted for this projects: ");
			for(Project p: this.votedProjects) {
				sb.append(p.getTitle() + ", ");
			}
		}
		if(!this.notifications.isEmpty()) {
			sb.append("\nHas this notifications pending: ");
			for(Notification n: this.notifications) {
				sb.append(n.getTitle() + ", ");
			}
		}
		if(this.banned == true)
			sb.append("\nThis user has been banned till: " + this.unbanDate);		
		
		return sb.toString();
	}
	
	/**
	 * Returns true if the login is correct, false otherwise.
	 * @param nif String containing the nif of the user trying to login.
	 * @param pwd String containing password written by the user trying to login.
	 * @return True if the login is correct, false otherwise.
	 */
	public boolean login(String nif, String pwd) {
		if (this.tryUnban() == false) return false;
		if (!nif.equals(this.nif) || !pwd.equals(this.pwd)) {
			System.out.println(this + " " + nif + " 2");
			return false;
		}			
		return true;
	
	}
	
	/**
	 * Tries to unban a user.
	 * @return True if the the banned period has finished or the user was already unbanned, false otherwise.
	 */
	public boolean tryUnban() {
		Calendar fecha = Calendar.getInstance();
		if(banned == false)
			return true;
		if(fecha.compareTo(this.unbanDate) == -1)
			return false;
		banned = false;
		return true;
	}
	
	/**
	 * Function that sets the end of the banning period and notifies the user with the reason of the ban.
	 * @param message String containing the reason why the user has been banned.
	 * @param days int representing the number of days the User will be banned.
	 * @return false if the user was already banned, true otherwise
	 */
	public boolean ban(String message, int days) {
		if (banned == true)
			return false;
		banned = true;
		Calendar fecha = Calendar.getInstance();
		fecha.add(Calendar.DAY_OF_YEAR, days);
		this.unbanDate = fecha;
		Notification n = new Notification("You have been banned!",message);
		this.addNotification(n);
		return true;
	}
	
	/**
	 * Returns a set containing the User, in order to be joined with other sets later.
	 * @return A HashSet containing the User.
	 */
	public Set<User> count() {
		Set<User> s = new HashSet<User>();
		if (this.tryUnban())
			s.add(this);		
		return s;
	}


	/**
	 * Adds a project to the VotedProjects list.
	 * @param p Project to be added.
	 * @return true in case the project has been added to the list, false if it was already there.
	 */
	public boolean addVotedProject(Project p) {
		if(this.votedProjects.contains(p))
			return false;
		this.votedProjects.add(p);
		return true;
	}
	
	/**
	 * Adds a project to the FollowedProjects list.
	 * @param p Project to be added.
	 * @return true in case the project has been added to the list, false if it was already there.
	 */
	public boolean addFollowedProject(Project p) {
		if(this.followedProjects.contains(p))
			return false;
		this.followedProjects.add(p);
		return true;
	}
	
	/**
	 * Removes a project to the FollowedProjects list.
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
	
	/**
	 * Adds a collective to the collectives list.
	 * @param c Collective to be added.
	 * @return true in case the collective has been added to the list, false if it was already there.
	 */
	public boolean enterCollective(Collective c) {
		Set<Collective> s = new HashSet<Collective>();
		s = c.getDescendantCollectives();
		// If the user is in a child collective, it cannot join this collective.
		for(Collective aux: this.getCollectives()) {
			if(s.contains(aux)) return false;
		}
		
		if(this.collectives.contains(c)) return false;
		this.collectives.add(c);
		c.join(this);
		return true;
	}
	
	/**
	 * Removes a collective to the collectives list.
	 * @param c Collective to be removed.
	 * @return true in case the collective has been removed from to the list, false if it was not already there.
	 */
	public boolean exitCollective(Collective c) {
		if (c.getRepresentative() == this) return false;
		if(this.collectives.contains(c)) {
			this.collectives.remove(c);
			c.leave(this);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Adds a collective to the representedCollectives list.
	 * @param c Collective to be added.
	 * @return true in case the collective has been added to the list, false if it was already there.
	 */
	public boolean createCollective(Collective c) {
		if(this.representedCollectives.contains(c))
			return false;
		this.representedCollectives.add(c);
		return true;
	}
	
	/**
	 * Adds a Project createdProjects list.
	 * @param p Project that will be added to the createdProjects list of the collective.
	 * @return True if the project was not already created by the user, false otherwise.
	 */
	public boolean addCreatedProject(Project p) {
		if (this.createdProjects.contains(p))
			return false;
		this.createdProjects.add(p);
		return true;
	}
	
	
	/**
	 * Adds a notification to the notifications list.
	 * @param n Notification to be added.
	 * @return true in case the notification has been added to the list, false if it was already there.
	 */
	public boolean addNotification(Notification n) {
		if(this.notifications.contains(n))
			return false;
		this.notifications.add(n);
		return true;
	}
	
	
	/**
	 * Removes a notification to the notifications list.
	 * @param n to be removed.
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
