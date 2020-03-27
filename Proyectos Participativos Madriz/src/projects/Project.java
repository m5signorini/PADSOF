/**
 * 
 */
package projects;
import entities.*;
import entities.individuals.*;

import java.util.*;
import java.time.*;
/**
 * @author César Ramírez
 *
 */
public abstract class Project {
	private String title;
    private String description;
    private double cost;
    private double budget;
    private Date creationDate;
    private Date lastVote;
    private Voter creator;
    private ArrayList<Voter> voters;
    private ArrayList<User> followers;
    private Application app;
 

    public Project(String title, String description, Double cost, Date creationDate, Voter creator, Application app) {
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.budget = 0.0;
        this.creationDate = creationDate;
        this.lastVote = null;
        this.creator = creator;
        this.app = app; 
        this.voters = new ArrayList<Voter>();
        this.followers = new ArrayList<User>();
    }


	public double getBudget() {
		return budget;
	}


	public void setBudget(double budget) {
		this.budget = budget;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getCost() {
		return cost;
	}


	public void setCost(double cost) {
		this.cost = cost;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public Date getLastVote() {
		return lastVote;
	}


	public void setLastVote(Date lastVote) {
		this.lastVote = lastVote;
	}


	public Voter getCreator() {
		return creator;
	}


	public void setCreator(Voter creator) {
		this.creator = creator;
	}


	public ArrayList<Voter> getVoters() {
		return voters;
	}


	public void setVoters(ArrayList<Voter> voters) {
		this.voters = voters;
	}


	public ArrayList<User> getFollowers() {
		return followers;
	}


	public void setFollowers(ArrayList<User> followers) {
		this.followers = followers;
	}


	public Application getApp() {
		return app;
	}


	public void setApp(Application app) {
		this.app = app;
	}
    
	
    void notifyFollowers() {
    	/*Podemos sustituir todos los bucles por
    	 * uno al final ya que estamos seguros de que notifyFollowers()
    	 * solo se va a llamar cuando el proyecto se 
    	 * encuentre en los estados indicados al ser
    	 * llamado directamente desde la clase Application.
    	 */
    	if (this.app.financiatedProjects.contains(this) == true) {
    		Notification notification = new Notification("Financiated", "The project " + this.title + " is now financiated.");
    	}
    	else if (this.app.sentProjects.contains(this) == true) {
    		Notification notification = new Notification("Sent", "The project " + this.title + " has been sent.");
    	}
    	else if (this.app.rejectedProjects.contains(this) == true) {
    		Notification notification = new Notification("Rejected", "The project " + this.title + " has been rejected.");
    	}
    	else if (this.app.expiredProjects.contains(this) == true) {
    		Notification notification = new Notification("Expired", "The project " + this.title + " has been expired.");
    		}
    	for(int i = 0; i < this.followers.size(); i++) {
			this.followers.get(i).notifications.add(notification);
		}
    }
    
    Project reject() {
    	this.app.rejectedProjects.add(this);
    	return this;
    }
    
    Project validate() {
    	this.app.publicProjects.add(this);
    	return this;
    }
    
    void send() {
    	this.app.sentProjects.add(this);
    }
    
    Project support(Voter v) {
    	this.voters.add(v);
    	return this;
    }
    
    void financiate(double budget) {
    	this.budget = budget;
    }
    
    int countVotes() {
    	return this.voters.size();
    }
    
    Boolean hasExpired() {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(new Date());
    	int today = calendar.get(Calendar.DAY_OF_MONTH);
    	
    	Calendar calendarAux = Calendar.getInstance();
    	calendarAux.setTime(this.creationDate);
    	int creationDay = calendarAux.get(Calendar.DAY_OF_MONTH);

    	
    	if ((today - creationDay) > this.app.maxInactivity) {
    		this.app.expiredProjects.add(this);
    		return true;
    	}
    	return false;
    }
}
