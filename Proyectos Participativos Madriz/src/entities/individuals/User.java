/**
 * 
 */
package entities.individuals;

import java.util.*;
import entities.Collective;
import projects.Project;
import entities.individuals.Notification;

/**
 * @author Pedro Urbina Rodriguez
 *
 */
public class User extends Account {
	protected String nif;
	protected ArrayList<Collective> collectives;
	protected ArrayList<Collective> representedCollectives;
	protected ArrayList<Project> followedProjects;
	protected ArrayList<Project> votedProjects;
	boolean banned = false;
	Calendar unbanDate;
	
	public User(String name, String pwd, String nif) {
		super(name, pwd);
		nif = this.nif;
		collectives = new ArrayList<Collective>();
		representedCollectives = new ArrayList<Collective>();
		followedProjects = new ArrayList<Project>();
		votedProjects = new ArrayList<Project>();
	}
	
	
	public String getNif() { return nif; }
	public void setNif(String nif) { this.nif = nif; }
	
	public User validate() {
		return this;
	}
	
	public User follow(Project p) {
		followedProjects.add(p);
		return this;
	}
	
	public User reject() {
		return this;
	}
	
	public boolean login(String nif, String pwd) {
		if (nif != this.nif || pwd != this.pwd) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean tryUnban() {
		if (this.banned == false) 
			return true;
		Calendar fecha = Calendar.getInstance();
		if(fecha.compareTo(this.unbanDate) == -1)
			return false;
		this.unban();
		return true;
	}
	
	public void unban() {
		this.banned = false;
	}
	
	public void ban(String message, int days) {
		this.banned = true;
		Calendar fecha = Calendar.getInstance();
		fecha.add(Calendar.DAY_OF_YEAR, days);
		this.unbanDate = fecha;
		Notification n = new Notification(message);
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


	public void setVotedCollectives(ArrayList<Project> votedProjects) {
		this.votedProjects = votedProjects;
	}
}
