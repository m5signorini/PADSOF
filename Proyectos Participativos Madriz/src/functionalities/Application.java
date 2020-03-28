package functionalities;
/**
 * 
 */

/**
 * @author
 *
 */
import java.util.*;
import java.io.*;
import entities.individuals.*;
import entities.*;
import projects.*;

public class Application implements Serializable{
	private int minSupports;
	private int maxInactivity;
	
	private List<Project> sentProjects;
	private List<Project> financiatedProjects;
	private List<Project> rejectedProjects;
	private List<Project> publicProjects;
	private List<Project> expiredProjects;
	private List<Project> pendingProjects;
	
	private List<User> registeredUsers;
	private List<User> unregisteredUsers;
	private List<User> bannedUsers;
	
	private List<Collective> collectives;
	
	private SearchEngine searcher;
	private Admin admin;
	private User loggedUser;
	
	public Application(Admin admin) {
		this.admin = admin;
	}
	
	public int getMinSupports() {
		return minSupports;
	}
	
	public void setMinSupports(int minSupports) {
		this.minSupports = minSupports;
	}
	
	public int getMaxInactivity() {
		return maxInactivity;
	}
	
	public void setMaxInactivity(int maxInactivity) {
		this.maxInactivity = maxInactivity;
	}

	public List<Project> getSentProjects() {
		return sentProjects;
	}

	public void setSentProjects(List<Project> sentProjects) {
		this.sentProjects = sentProjects;
	}

	public List<Project> getFinanciatedProjects() {
		return financiatedProjects;
	}

	public void setFinanciatedProjects(List<Project> financiatedProjects) {
		this.financiatedProjects = financiatedProjects;
	}

	public List<Project> getRejectedProjects() {
		return rejectedProjects;
	}

	public void setRejectedProjects(List<Project> rejectedProjects) {
		this.rejectedProjects = rejectedProjects;
	}

	public List<Project> getPublicProjects() {
		return publicProjects;
	}

	public void setPublicProjects(List<Project> publicProjects) {
		this.publicProjects = publicProjects;
	}

	public List<Project> getExpiredProjects() {
		return expiredProjects;
	}

	public void setExpiredProjects(List<Project> expiredProjects) {
		this.expiredProjects = expiredProjects;
	}

	public List<Project> getPendingProjects() {
		return pendingProjects;
	}

	public void setPendingProjects(List<Project> pendingProjects) {
		this.pendingProjects = pendingProjects;
	}

	public List<User> getRegisteredUsers() {
		return registeredUsers;
	}

	public void setRegisteredUsers(List<User> registeredUsers) {
		this.registeredUsers = registeredUsers;
	}

	public List<User> getUnregisteredUsers() {
		return unregisteredUsers;
	}

	public void setUnregisteredUsers(List<User> unregisteredUsers) {
		this.unregisteredUsers = unregisteredUsers;
	}

	public List<User> getBannedUsers() {
		return bannedUsers;
	}

	public void setBannedUsers(List<User> bannedUsers) {
		this.bannedUsers = bannedUsers;
	}

	public List<Collective> getCollectives() {
		return collectives;
	}

	public void setCollectives(List<Collective> collectives) {
		this.collectives = collectives;
	}

	public SearchEngine getSearcher() {
		return searcher;
	}

	public void setSearcher(SearchEngine searcher) {
		this.searcher = searcher;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	/* Method that creates an unregistered user
	 * @param name 	Name of the user
	 * @param nif 	NIF of user
	 * @param pwd	User's password
	 * @return true if creation went well, false if not
	 */
	public boolean register(String name, String nif, String pwd) {
		if(name == null || nif == null || pwd == null) return false;
		/* Controlar nif unico */
		User u = new User(name, pwd, nif);
		unregisteredUsers.add(u);
		return true;
	}
	
	public boolean login(String nif, String pwd) {
		if(nif == null || pwd == null) return false;
		for(User u: registeredUsers) {
			if(u.login(nif, pwd) == true) {
				return true;
			}
		}
		return false;
	}
	
	/* Method for creating a project
	 * @param P Created project
	 * @return true if creation was possible, false if not
	 */
	public boolean createProject(Project p) {
		if(p == null) return false;
		pendingProjects.add(p);
		return false;
	}
	
	/* Method for validating projects as administrator
	 * @param P Validated project
	 * @return true if validation was possible, false if not
	 */
	public boolean validateProject(Project p) {
		if(p == null) return false;
		if(pendingProjects.remove(p) != true) {
			return false;
		}
		publicProjects.add(p);
		return true;
	}
	
	/* Method for sending project to the council
	 * @param P Sent project
	 * @return true if sending was possible, false if not
	 */
	public boolean sendProject(Project p) {
		if(p == null) return false; 
		if(publicProjects.remove(p) != true) {
			return false;
		}
		p.send();
		sentProjects.add(p);
		return true;
	}
	
	/* Method used by the administrator to reject pending projects
	 * @param P Project to be rejected
	 * @return true if rejection was possible, false if not
	 */
	public boolean rejectProject(Project p) {
		if(p == null) return false;
		if(pendingProjects.remove(p) != true) {
			return false;
		}
		rejectedProjects.add(p);
		return true;
	}
	
	/* Method used to manage time-related events like
	 * when a project expires or gets accepted
	 */
	public void updateProjects() {
		
	}
	
	/* Method for logging out of the current session
	 */
	public void logout() {
		
	}
	
	/* Adds user to ban list
	 * @param u User to be banned
	 * @param 
	 * @return true if could ban, false if not
	 */
	public boolean ban(User u) {
		return false;
	}
	
	/* Removes user from ban list
	 * @param u User to remove ban
	 * @return true if could remove ban, false if not
	 */
	public boolean unban(User u) {
		return false;
	}
	
	/* Validates user registration
	 * @param u Accepted user
	 * @return true if validation went well, false if not
	 */
	public boolean validateUser(User u) {
		if(u == null) return false;
		if(unregisteredUsers.remove(u) != true) {
			return false;
		}
		registeredUsers.add(u);
		return true;
	}
	
	/* Rejects an user registration as an administrator
	 * @param u User register that has been rejected
	 * @return true if rejection went well, false if not
	 */
	public boolean rejectUser(User u) {
		if(u == null) return false;
		if(unregisteredUsers.remove(u) != true) {
			return false;
		}
		return false;
	}
	
	public boolean notify(User u, Notification n) {
		return false;
	}
	
	public double calcAffinity(Collective c1, Collective c2) {
		return 2.0;
	}
}



