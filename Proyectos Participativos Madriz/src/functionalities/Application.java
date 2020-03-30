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
import es.uam.eps.sadp.grants.*;

public class Application implements Serializable{
	private int minSupports;
	private int maxInactivity;
	
	private List<Project> sentProjects;
	private List<Project> financiatedProjects;
	/* Projects rejected by council */
	private List<Project> rejectedProjects;
	/* Projects denied by the administrator */
	private List<Project> deniedProjects;
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
	
	public Application(Admin admin, int minSupports, int maxInactivity) {
		this.admin = admin;
		this.minSupports = minSupports;
		this.maxInactivity = maxInactivity;
		
		sentProjects = new ArrayList<Project>();
		financiatedProjects = new ArrayList<Project>();
		rejectedProjects = new ArrayList<Project>();
		deniedProjects = new ArrayList<Project>();
		publicProjects = new ArrayList<Project>();
		expiredProjects = new ArrayList<Project>();
		pendingProjects = new ArrayList<Project>();
		
		registeredUsers = new ArrayList<User>();
		unregisteredUsers = new ArrayList<User>();
		bannedUsers = new ArrayList<User>();
		
		collectives = new ArrayList<Collective>();
		this.searcher = new SearchEngine(this);
	}
	
	public void writeToFile(String filepath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
	public static Application readFromFile(String filepath) {
		try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Object obj = objectIn.readObject();
            System.out.println("The Object has been read from the file");
            objectIn.close();
            return (Application)obj;
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
	}
	
	/*
	 * GETTERS AND SETTERS
	 */
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
	
	public List<Project> getDeniedProjects() {
		return deniedProjects;
	}

	public void setDeniedProjects(List<Project> deniedProjects) {
		this.deniedProjects = deniedProjects;
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

	/**
	 * Method that creates an unregistered user
	 * @param name 	Name of the user
	 * @param nif 	NIF of user
	 * @param pwd	User's password
	 * @return true if creation went well, false if not
	 */
	public boolean register(User u) {
		if(u == null) return false;
		for(User u2: registeredUsers) {
			if(u2.getNif().equals(u.getNif())) return false;
		}
		for(User u2: unregisteredUsers) {
			if(u2.getNif().equals(u.getNif())) return false;
		}
		unregisteredUsers.add(u);
		return true;
	}
	
	/**
	 * Method for logging in as an user
	 * @param nif 	NIF of account
	 * @param pwd	Account's password
	 * @return true if login was correct, false if wrong credentials
	 */
	public boolean login(String nif, String pwd) {
		if(nif == null || pwd == null) return false;
		// Check as user
		for(User u: registeredUsers) {
			if(u.login(nif, pwd) == true) {
				// Ban check
				if(bannedUsers.contains(u) && u.tryUnban() == false) {
					return false;
				}
				loggedUser = u;
				this.updateProjects();
				this.updateUsers();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method for logging in as an administrator
	 * Note that administrators only need a password
	 * @param pwd	Admin's password
	 * @return true if login was correct, false if wrong credentials
	 */
	public boolean loginAdmin(String pwd) {
		// Check as administrator
		if(admin.login("Administrator", pwd) == true) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method for creating a project
	 * @param P Created project
	 * @return true if creation was possible, false if not
	 */
	public boolean createProject(Project p) {
		if(p == null) return false;
		pendingProjects.add(p);
		return true;
	}
	
	/**
	 * Method for validating projects as administrator
	 * @param P Validated project
	 * @return true if validation was possible, false if not
	 */
	public boolean validateProject(Project p) {
		if(p == null) return false;
		if(pendingProjects.remove(p) != true) {
			return false;
		}
		publicProjects.add(p);
		p.validate();
		return true;
	}
	
	/**
	 * Method for sending project to the council
	 * @param P Sent project
	 * @return true if sending was possible, false if not
	 */
	public boolean sendProject(Project p) {
		if(p == null || p.countVotes() < minSupports) return false; 
		if(publicProjects.remove(p) != true) {
			return false;
		}
		if(p.send() == false) {
			publicProjects.add(p);
			return false;
		}
		sentProjects.add(p);
		return true;
	}
	
	/**
	 * Method used when the administrator denies a project
	 * @param P Project to be rejected
	 * @return true if rejection was possible, false if not
	 */
	public boolean denyProject(Project p) {
		if(p == null) return false;
		if(pendingProjects.remove(p) != true) {
			return false;
		}
		deniedProjects.add(p);
		return true;
	}
	
	/**
	 * Method used for manual project rejection
	 * @param P Project to be rejected
	 * @return true if rejection was possible, false if not
	 */
	public boolean rejectProject(Project p) {
		if(p == null) return false;
		if(sentProjects.remove(p) != true) {
			return false;
		}
		rejectedProjects.add(p);
		p.reject();
		return true;
	}
		
	/**
	 * Method used to manage time-related events like
	 * when a project expires or gets accepted by council
	 */
	public void updateProjects() {
		// Checking sent
		CCGG gateway = CCGG.getGateway();
		Double budget;
		Iterator<Project> it = sentProjects.iterator();
		while(it.hasNext()) {
			Project p = it.next();
			try {
				budget = gateway.getAmountGranted(p.getFollowUpID());
				if(budget == null) {
					continue;
				}
				else if(budget == 0) {
					it.remove();
					rejectedProjects.add(p);
				}
				else if(budget > 0) {
					p.setBudget(budget);
					it.remove();
					financiatedProjects.add(p);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidIDException e) {
				// Invalid ID, treat project as rejected
				it.remove();
				rejectedProjects.add(p);
				e.printStackTrace();
			}
		}
		// Checking expired projects
		it = publicProjects.iterator();
		while(it.hasNext()) {
			Project p = it.next();
			if(p.hasExpired(maxInactivity) == true) {
				it.remove();
				expiredProjects.add(p);
			}
		}
		
	}
	
	/**
	 * Method for logging out of the current session
	 */
	public void logout() {
		loggedUser = null;
	}
	
	/**
	 * Adds user to ban list
	 * @param u User to be banned
	 * @param 
	 * @return true if could ban, false if not
	 */
	public boolean ban(User u, String message, int days) {
		if(u == null) return false;
		if(registeredUsers.contains(u) == false) {
			return false;
		}
		bannedUsers.add(u);
		u.ban(message, days);
		return true;
	}
	
	/**
	 * Updates users lists unbanning those who can be
	 */
	public void updateUsers(){
		Iterator<User> it = bannedUsers.iterator();
		while(it.hasNext()) {
			User u = it.next();
			if(u.tryUnban()==true) {
				it.remove();
			}
		}
	}
	
	/**
	 * Validates user registration
	 * @param u Accepted user
	 * @return true if validation went well, false if not
	 */
	public boolean validateUser(User u) {
		if(u == null) return false;
		if(unregisteredUsers.remove(u) != true) {
			return false;
		}
		registeredUsers.add(u);
		u.validate();
		return true;
	}
	
	/**
	 * Rejects an user registration as an administrator
	 * @param u User register that has been rejected
	 * @return true if rejection went well, false if not
	 */
	public boolean rejectUser(User u) {
		if(u == null) return false;
		if(unregisteredUsers.remove(u) != true) {
			return false;
		}
		u.reject();
		return true;
	}
	
	/**
	 * Method for sending a notification to a specific user
	 * @param u User that will receive the notification
	 * @param n Sent notification
	 * @return true if notification was able to be sent
	 */
	public boolean notifyUser(User u, Notification n) {
		if(u == null || n== null) return false;
		return u.addNotification(n);
	}
	
	/**
	 * Adds a collective to the application
	 * @param c New collective
	 * @return true if it was possible to add
	 */
	public boolean createCollective(Collective c) {
		if(c == null) return false;
		collectives.add(c);
		return true;
	}
	
	/**
	 * Method for calculating collectives affinity
	 * @param c1 Collective 1
	 * @param c2 Collective 2
	 * @return Affinity level using a specific formula
	 */
	public double calcAffinity(Collective c1, Collective c2) {
		return 2.0;
	}
	
	private String projectsToString() {
		String s = "";
		s += "Pending projects:\n";
		for(Project p: this.pendingProjects) {
			/* For pretty printing the application */
			s += p.toString().replaceAll("(?m)^", "\t") + "\n";
		}
		s += "Denied projects:\n";
		for(Project p: this.deniedProjects) {
			s += p.toString().replaceAll("(?m)^", "\t") + "\n";
		}
		s += "Sent projects:\n";
		for(Project p: this.sentProjects) {
			s += p.toString().replaceAll("(?m)^", "\t") + "\n";
		}
		s += "Rejected projects:\n";
		for(Project p: this.rejectedProjects) {
			s += p.toString().replaceAll("(?m)^", "\t") + "\n";
		}
		s += "Financiated projects:\n";
		for(Project p: this.financiatedProjects) {
			s += p.toString().replaceAll("(?m)^", "\t") + "\n";
		}
		s += "Public projects:\n";
		for(Project p: this.publicProjects) {
			s += p.toString().replaceAll("(?m)^", "\t") + "\n";
		}
		s += "Expired projects:\n";
		for(Project p: this.expiredProjects) {
			s += p.toString().replaceAll("(?m)^", "\t") + "\n";
		}
		return s;
	}
	
	private String usersToString() {
		String s = "";
		s += "Unregistered users:\n";
		for(User u: this.unregisteredUsers) {
			s += u.toString().replaceAll("(?m)^", "\t") + "\n";
		}
		s += "Registered users:\n";
		for(User u: this.registeredUsers) {
			s += u.toString().replaceAll("(?m)^", "\t") + "\n";
		}
		s += "Banned Users:\n";
		for(User u: this.bannedUsers) {
			s += u.toString().replaceAll("(?m)^", "\t") + "\n";
		}
		return s;
	}
	
	private String collectivesToString() {
		String s = "";
		s += "Collectives:\n";
		for(Collective c: this.collectives) {
			s += c.toString().replaceAll("(?m)^", "\t") + "\n";
		}
		return s;
	}
	
	private String extraDataToString() {
		String s = "";
		s += "Admin: " + admin + "\n";
		s += "Logged user: " + loggedUser + "\n";
		s += "Minimum Supports: " + minSupports + "\n";
		s += "Maximum Inactivity: " + maxInactivity + "\n";
		return s;
	}
	
	public String toString() {
		String s = "APPLICATION\n";
		s += projectsToString();
		s += collectivesToString();
		s += usersToString();
		s += extraDataToString();
		return s;
	}
}



