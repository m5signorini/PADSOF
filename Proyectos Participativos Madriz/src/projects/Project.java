/**
 *
 */
package projects;
import entities.*;
import entities.individuals.*;

import java.util.*;
import java.time.*;
/**
 * @author Cesar Ramirez Martinez
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


    public Project(String title, String description, Double cost, Date creationDate, Voter creator) {
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.budget = 0.0;
        this.creationDate = creationDate;
        this.lastVote = null;
        this.creator = creator;
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

    private void notifyFollowers(String type) {
    	/*Podemos sustituir todos los bucles por
    	 * uno al final ya que estamos seguros de que notifyFollowers()
    	 * solo se va a llamar cuando el proyecto se
    	 * encuentre en los estados indicados al ser
    	 * llamado directamente desde la clase Application.
    	 */
    	if (type == "Financiated") {
    		Notification notification = new Notification(type, "The project " + this.title + " has been financiated with " + this.budget + "euros.");
    	}
    	else if (type == "Sent") {
    		Notification notification = new Notification(type, "The project " + this.title + " has been sent to verification.");
    	}
    	else if (type == "Rejected") {
    		Notification notification = new Notification(type, "The project " + this.title + " has been rejected.");
    	}
    	else if (type == "Expired") {
    		Notification notification = new Notification(type, "The project " + this.title + " has expired.");
    		}
    	for(int i = 0; i < this.followers.size(); i++) {
			this.followers.get(i).notifications.add(notification);
		}
    }

    public Project reject() {
    	return this;
    }

    public Project validate() {
    	return this;
    }

    public Project send() {
    	return this;
    }

    public Project support(Voter v) {
    	this.voters.add(v);
    	return this;
    }

    public void financiate(double budget) {
    	this.budget = budget;
    }

    public int countVotes() {
    	return this.voters.size();
    }

    public boolean hasExpired(int maxInactivity) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(new Date());
    	int today = calendar.get(Calendar.DAY_OF_MONTH);

    	Calendar calendarAux = Calendar.getInstance();
    	calendarAux.setTime(this.creationDate);
    	int creationDay = calendarAux.get(Calendar.DAY_OF_MONTH);


    	if ((today - creationDay) > maxInactivity) {
    		return true;
    	}
    	return false;
    }
}
