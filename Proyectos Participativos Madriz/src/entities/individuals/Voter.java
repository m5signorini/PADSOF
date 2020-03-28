package entities.individuals;
import java.util.*;

import projects.Project; 

public abstract class Voter {
	private ArrayList<Project> projects;
	
	public Voter() {
		this.projects = new ArrayList<Project>();
	}
	
	public ArrayList<Project> getProjects() {
		return projects;
	}
	
	public void setProjects(ArrayList<Project> projects) {
		this.projects= projects;
	}
	
	public boolean vote(Project p) {
		if(p.getVoters().contains(this) == true) {
			return false;
		}
		this.projects.add(p.support(this));
		return true;
	}
	public abstract Set<User> count();

}
