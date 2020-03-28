package entities.individuals;
import java.util.*;

import projects.Project; 

public abstract class Voter {
	protected ArrayList<Project> projects;
	
	public Voter() {
		this.projects = new ArrayList<Project>();
	}
	
	public ArrayList<Project> getProjects() {
		return projects;
	}
	
	public void setProjects(ArrayList<Project> projects) {
		this.projects= projects;
	}
	
	
	public abstract boolean vote(Project p);
	public abstract Set<User> count();

}
