/**
 *
 */
package projects;
import entities.individuals.*;
import es.uam.eps.sadp.grants.*;

import java.io.IOException;
import java.util.*;/**
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
    private String followUpID;

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
        this.creator.addVotedProject(this);
        this.voters.add(creator);
    }
    
    public String getFollowUpID() {
    	return followUpID;
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
		return this.cost;
	}
	
	/* Method used to add a follower to the followers list of a project.
	 * @param user User that will be added to the project followers.
	 */
    public void addFollower(User user) {
    	this.followers.add(user);
    }
    
	/* Method used to send any notification to the followers of a project.
	 * @param notification Notification that will be sent to all the project followers.
	 */
    private void notifyFollowers(Notification notification ) {
    	for(User u: followers) {
			u.addNotification(notification);
		}
    }
    
    /* Method used to reject a proposal project. A notification will be sent to all the 
     * project followers reporting this decision.
	 * @return The project that will be added to the rejected projects list by the application.
	 */
    public Project reject() {
		Notification notification = new Notification("Rejection", "The project " + this.title + " has been rejected.");
		notifyFollowers(notification);
    	return this;
    }
    
    /* Method used to validate a proposal project.
	 * @return The project that will be added to the public projects list by the application.
	 */
    public Project validate() {
    	return this;
    }
    
    /* Method used to send to financiation a project that has reached the minimum number of votes established.
     * A notification reporting this decision will be sent to all the project followers.
	 * @return true if no problem was found when sending the project
	 */
    public boolean send() {
    	Notification notification = new Notification("Sent", "The project " + this.title + " has been sent to validation.");
		notifyFollowers(notification);
		CCGG gateway = CCGG.getGateway();
		try {
			followUpID = gateway.submitRequest(this);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (InvalidRequestException e) {
			e.printStackTrace();
			return false;
		}
    	return true;
    }
    
    /* Method used to add a vote to the project.
	 * @param voter Voter that will be added to the voters list of the project.
	 * @return The project itself.
	 */
    public Project support(Voter voter) {
    	if(voters.contains(voter)) return this;
    	this.voters.add(voter);
    	voter.addVotedProject(this);
    	return this;
    }
    
    /* Method used to financiate a project with a certain amount of money(euros).
     * A notification reporting this decision will be sent to all the project followers.
  	 * @param budget Double that indicates the amount of money that wiil be used to financiate the project.
  	 * @return The project that will be added to the financiated projects list by the application.
  	 */
    public Project financiate(double budget) {
    	this.budget = budget;
    	Notification notification = new Notification("Financing", "The project " + this.title + " has been financiated with " + budget + "euros.");
		notifyFollowers(notification);
		return this;
    }
    
    /* Method used to count all the votes that a project has.
  	 * @return The number of votes that a project has got.
  	 */
    public int countVotes() {
    	Set<User> s = new HashSet<User>();
    	for(int i = 0; i < this.voters.size(); i++) {
    		s.addAll(this.voters.get(i).count());
    	}
    	return s.size();
    }
    
    /* Method used to check if a project has expired since the it recieved its last vote.
     * We obtain todays day and we subtract the day of the last vote. If the result 
     * is greater than maxInactivity it will have expired. 
     * @param maxInactivity Integer that indicates the max number of days that a project can remain public without receiving any support.
  	 * @return True if the project has expired. False otherwise.
  	 */
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
    
    public String toString() {
    	String s = "PROJECT\n";
    	s += "Title: "+ title + "\n";
    	s += "Description: " + description +"\n";
    	s += "Cost: " + cost + "\n";
    	s += "Creator: " + creator + "\n";
    	s += "Number of votes: " + this.countVotes() + "\n";
    	s += "Number of followers: " + this.followers.size() + "\n";
    	s += "FollowUpID: " + this.followUpID;
    	return s;
    }
}
