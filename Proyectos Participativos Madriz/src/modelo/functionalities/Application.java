package modelo.functionalities;

import java.util.*;
import java.io.*;

import es.uam.eps.sadp.grants.*;
import modelo.entities.*;
import modelo.entities.individuals.*;
import modelo.projects.*;

/**
 * Application class containing all the necessary information
 * for its correct use.
 * @author Martin Sanchez Signorini
 */
public class Application implements Serializable{
	private static Application APPLICATION;
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
	
	private Application(/*Admin admin, int minSupports, int maxInactivity*/) {
		/*this.admin = admin;
		this.minSupports = minSupports;
		this.maxInactivity = maxInactivity;*/
		
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
	
	public static Application getApplication() {
		if(APPLICATION == null) {
			APPLICATION = new Application();
		}
		return APPLICATION;
	}
	
	public boolean writeToFile(String filepath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
	
	public static Application readFromFile(String filepath) {
		try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Object obj = objectIn.readObject();
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

	public List<Project> getFinanciatedProjects() {
		return financiatedProjects;
	}

	public List<Project> getRejectedProjects() {
		return rejectedProjects;
	}
	
	public List<Project> getDeniedProjects() {
		return deniedProjects;
	}

	public List<Project> getPublicProjects() {
		return publicProjects;
	}

	public List<Project> getExpiredProjects() {
		return expiredProjects;
	}

	public List<Project> getPendingProjects() {
		return pendingProjects;
	}

	public List<User> getRegisteredUsers() {
		return registeredUsers;
	}

	public List<User> getUnregisteredUsers() {
		return unregisteredUsers;
	}

	public List<User> getBannedUsers() {
		return bannedUsers;
	}

	public List<Collective> getCollectives() {
		return collectives;
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
	 * @param u User registered
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
	 * @param p Created project
	 * @return true if creation was possible, false if not
	 */
	public boolean createProject(Project p) {
		if(p == null) return false;
		pendingProjects.add(p);
		return true;
	}
	
	/**
	 * Method for validating projects as administrator
	 * @param p Validated project
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
	 * @param p Sent project
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
	 * @param p Project to be rejected
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
	 * @param p Project to be rejected
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
	 * @param message Message containing the ban reason
	 * @param days days that the ban will last
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
	 * @return A number more than or equals to 0 representing the affinity between
	 * these two collectives.
	 */
	public double calcAffinity(Collective c1, Collective c2) {
		if(c1 == null || c2 == null) return 0;
		//(projC1suppC2 + projC2suppC2)/totProySuppC1C2
		double nProjOfC1SuppByC2 = 0;
		double nProjOfC2SuppByC1 = 0;
		double totalCreatedProjs = 0;
		// Projects created by c1 supported by c2
		for(Project p: c1.getCreatedProjects()) {
			if(c2.getSupportedProjects().contains(p)) {
				nProjOfC1SuppByC2 += 1;
			}
			totalCreatedProjs += 1;
		}
		// Projects created by c2 supported by c1
		for(Project p: c2.getCreatedProjects()) {
			if(c1.getSupportedProjects().contains(p)) {
				nProjOfC2SuppByC1 += 1;
			}
			totalCreatedProjs += 1;
		}
		if(totalCreatedProjs == 0) {
			return 0;
		}
		return (nProjOfC1SuppByC2+nProjOfC2SuppByC1)/(totalCreatedProjs);
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



