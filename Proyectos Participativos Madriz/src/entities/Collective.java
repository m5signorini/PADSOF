/**
 * 
 */
package entities;
import entities.individuals.*;
import java.util.*;

import entities.individuals.User;
import projects.Project;

/**
 * @author eps
 *
 */
public class Collective implements Voter{
	private String name;
    private String description;
    private ArrayList<User> members;
    private ArrayList<Project> supportedProjects;
    private ArrayList<Project> createdProjects;
    private User representative;
    private Collective parent;
 

    public Collective(String name, String description, User representative) {
        this.name = name;
        this.description = description;
        this.supportedProjects = new ArrayList<Project>();
        this.createdProjects= new ArrayList<Project>();
        this.members = new ArrayList<User>();
        members.add(representative);
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

	public Collective(String name, String description, User representative, Collective parent) {
        this(name, description, representative);
        this.parent = parent;
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
	
	/*
	 *
	 */
	
	private void notifyMembers() {
	}
	
	public Collective leave(User u) {
		members.remove(u);
		return this;
	}
	
	public Collective join(User u) {
		members.add(u);
		return this;
	}
	
	@Override
	
	public boolean addProject(Project p) {
		this.createdProjects.add(p);
		return true;
	}
	
	@Override
	
	public boolean vote(Project p) {
		if(this.supportedProjects.contains(p)) return false;
		else {
			return this.supportedProjects.add(p);
		}
	}
	
	@Override
	
	public Set<User> count() {
		Set<User> s = new HashSet<User>();
		s.addAll(this.members);
		return s;
	}
}
