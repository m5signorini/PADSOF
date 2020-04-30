
/**
 * 
 */
package modelo.entities;
import java.util.*;

import modelo.entities.individuals.*;
import modelo.projects.Project;

import java.io.*;

/**
 * The Collective class represents collectives through which users with similar interests 
 * can associate and support different projects all at the same time, being notified when
 * projects are voted and being able to enter and exit this collectives. Also, collectives 
 * can have children so the users can easily find collectives that are interesting to them.
 * @author Pedro Urbina Rodriguez 
 */
public class Collective implements Voter, Serializable{
	protected String name;
	protected String description;
	protected ArrayList<User> members;
	protected ArrayList<Project> supportedProjects;
	protected ArrayList<Project> createdProjects;
	protected User representative;
	protected Collective parent;
	protected Set<Collective> childCollectives;
 

    public Collective(String name, String description, User representative) {
        this.name = name;
        this.description = description;
        this.supportedProjects = new ArrayList<Project>();
        this.createdProjects= new ArrayList<Project>();
        this.members = new ArrayList<User>();
        members.add(representative);
        this.representative = representative;
    	this.childCollectives = new HashSet<Collective>();
    	representative.createCollective(this);
    	representative.enterCollective(this);
    }
    
    public Collective(String name, String description, User representative, Collective father) {
    	this(name, description, representative);
    	this.parent = father;
    	father.addChildCollective(this);
    }
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<User> getMembers() {
		return members;
	}
	public void setMembers(ArrayList<User> members) {
		this.members = members;
	}
	public ArrayList<Project> getSupportedProjects() {
		return supportedProjects;
	}
	public void setSupportedProjects(ArrayList<Project> supportedProjects) {
		this.supportedProjects = supportedProjects;
	}
	public ArrayList<Project> getCreatedProjects() {
		return createdProjects;
	}
	public void setCreatedProjects(ArrayList<Project> createdProjects) {
		this.createdProjects = createdProjects;
	}
	public User getRepresentative() {
		return representative;
	}
	public void setRepresentative(User representative) {
		this.representative = representative;
	}
	public Collective getParent() {
		return parent;
	}
	public void setParent(Collective parent) {
		this.parent = parent;
	}
	public Set<Collective> getChildCollectives() {
		return childCollectives;
	}
	public void setChildCollectives(Set<Collective> childCollectives) {
		this.childCollectives = childCollectives;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Name: " + this.name + ".\nDescription: "+ this.description);
		return sb.toString();
	}
	

	/**
	 * Extended toString.
	 * @return A String containing all the information of the User.
	 */
	public String printAllInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append("Name: " + this.name + ".\nDescription: "+ this.description);
		sb.append(".\nRepresentative's name: " + this.representative.getName());
		sb.append("Parent's collective name: " + this.parent.getName());
		if(!this.members.isEmpty()) {
			sb.append("\nUsers that belong to this collective: ");
			for(User u: this.members) {
				sb.append(u.getName() + ", ");
			}
		}
		if(!this.supportedProjects.isEmpty()) {
			sb.append("\nHas supported projects: ");
			for(Project p: this.supportedProjects) {
				sb.append(p.getTitle() + ", ");
			}
		}
		if(!this.createdProjects.isEmpty()) {
			sb.append("\nHas created this projects: ");
			for(Project p: this.createdProjects) {
				sb.append(p.getTitle() + ", ");
			}
		}
		if(!this.childCollectives.isEmpty()) {
			sb.append("\nHas this children collectives: ");
			for(Collective c: this.childCollectives) {
				sb.append(c.getName() + ", ");
			}
		}
		return sb.toString();
	}
		
		
	
	/**
	 * Notifies every member of the collective.
	 * @param notification Notification that will be sent to every member of the collective.
	 */
	private void notifyMembers(Notification notification) {
		for(User u: members) {
			u.addNotification(notification);
		}
	}
	
	/**
	 * Removes a User from the members list.
	 * @param u User that will be removed from the collective.
	 * @return True if the user was in the collective and left it, false otherwise.
	 */
	public boolean leave(User u) {
		if(u == representative) return false;
		if (this.members.contains(u)) {
			members.remove(this.members.indexOf(u));
			return true;
		}
		return false;
	}
	
	/**
	 * Adds a User to the members list.
	 * @param u User that will be added to the collective.
	 * @return True if the user was not in the collective and joined it, false otherwise.
	 */
	public boolean join(User u) {
		Set<Collective> s = new HashSet<Collective>();
		s = this.getDescendantCollectives();
		// If the user is in a child collective, it cannot join this collective.
		for(Collective c: u.getCollectives()) {
			if(s.contains(c)) return false;
		}		
		if (this.members.contains(u)) return false;
		this.members.add(u);
		u.enterCollective(this);
		return true;
	}
	
	/**
	 * Adds a Project createdProjects list.
	 * @param p Project that will be added to the createdProjects list of the collective.
	 * @return True if the project was not already created by the collective, false otherwise.
	 */	
	public boolean addCreatedProject(Project p) {
		if (this.createdProjects.contains(p)) {
			return false;
		}
		this.createdProjects.add(p);
		return true;
	}
	
	/**
	 * Returns set containing all the users that vote when the representative of the collective votes as representative.
	 * @return The set containing all the users.
	 */		
	@Override	
	public Set<User> count() {
		Set<User> s = new HashSet<User>();
		s.addAll(this.members);
		for (Collective c: this.getDescendantCollectives()) {
			for (User u: c.members) {
				s.addAll(u.count());
			}
		}
		return s;
	}
	
	/**
	 * Adds a Project supportedProjects list.
	 * @param p Project that will be added to the supportedProjects list of the collective.
	 * @return True if the project was not already supported by the collective, false otherwise.
	 */	
	@Override
	public boolean addVotedProject(Project p) {
		if(this.supportedProjects.contains(p)) return false;
		// Add as voters all its children too
		for(Collective c: this.childCollectives) {
			p.support(c);
		}
		Notification notification = new Notification("New voted project.", "The collective " + this.name + " now supports " + p.getTitle());
		notifyMembers(notification);
		return this.supportedProjects.add(p);
	}
	
	/**
	 * Adds a collective to the childCollectives list, if it is permitted.
	 * @param c Collective that will be added to the childCollectives list of the collective.
	 * @return True if the collective was not already a descendant and has been added to the childCollectives list,
	 *  false if it cannot be the child of this project (because they are both son of the same parent, if it is already a
	 *  descendant, or if the project being added has this collective as descendant).
	 */		
	public boolean addChildCollective(Collective c) {
		if(c == null) return false;
		if (c.getParent() == this.getParent() && this.getParent() != null) return false;
		if (this.getDescendantCollectives().contains(c)) return false;
		if (c.getDescendantCollectives().contains(this)) return false;
		c.setParent(this);
		this.childCollectives.add(c);		
		return true;
	}
	
	/**
	 * Returns a set containing all the descendant collectives.
	 * @return HashSet containing all the descendant collectives.
	 */		
	public Set<Collective> getDescendantCollectives(){
		Set<Collective> c = new HashSet<Collective>();
		for (Collective aux: this.childCollectives) {
			c.add(aux);
			c.addAll(aux.getDescendantCollectives());
		}
		return c;
	}

}
