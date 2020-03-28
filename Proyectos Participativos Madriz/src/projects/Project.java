/**
 *
 */
package projects;
import entities.*;
import entities.individuals.*;
import es.uam.eps.sadp.*;
import es.uam.eps.sadp.grants.GrantRequest;

import java.util.*;
import java.time.*;
/**
 * @author Cesar Ramirez Martinez
 *
 */
public abstract class Project implements GrantRequest{
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
        this.creator.addProject(this);
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


	public  Voter getCreator() {
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
	
	public String getExtraData() {
		return String.valueOf(this.cost) + String.valueOf(this.creationDate) + String.valueOf(this.lastVote) + String.valueOf(this.creator);
	}
	
	public String getProjectTitle(){
		String s = getTitle();
		return s.substring(0, Math.min(25, s.length()));
	}
	
	public String getProjectDescription(){
		String s = getDescription();
		return s.substring(0, Math.min(500, s.length()));
	}
	
	public abstract ProjectKind getProjectKind();
	
	public double getRequestedAmount(){
		return this.budget;
	}

	
	
	
	
	
    private void notifyFollowers(Notification notification ) {
    	for(int i = 0; i < this.followers.size(); i++) {
			this.followers.get(i).notifications.add(notification);
		}
    }

    public Project reject() {
		Notification notification = new Notification("Rejection", "The project " + this.title + " has been rejected.");
		notifyFollowers(notification);
    	return this;
    }

    public Project validate() {
    	return this;
    }

    public Project send() {
    	Notification notification = new Notification("Sent", "The project " + this.title + " has been sent to validation.");
		notifyFollowers(notification);
    	return this;
    }

    public Project support(Voter v) {
    	this.voters.add(v);
    	return this;
    }

    public void financiate(double budget) {
    	this.budget = budget;
    	Notification notification = new Notification("Financing", "The project " + this.title + " has been financiated with " + budget + "euros.");
		notifyFollowers(notification);
    }

    public int countVotes() {
    	Set<User> s = new HashSet<User>();
    	for(int i = 0; i < this.voters.size(); i++) {
    		s.addAll(this.voters.get(i).count());
    	}
    	return s.size();
    }

    public boolean hasExpired(int maxInactivity) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(new Date());
    	int today = calendar.get(Calendar.DAY_OF_YEAR);

    	Calendar calendarAux = Calendar.getInstance();
    	calendarAux.setTime(this.lastVote);
    	int lastVoteDay = calendarAux.get(Calendar.DAY_OF_YEAR);


    	if ((today - lastVoteDay) > maxInactivity) {
    		Notification notification = new Notification("Expired", "The project " + this.title + " has been expired.");
    		notifyFollowers(notification);
    		return true;
    	}
    	return false;
    }
}
