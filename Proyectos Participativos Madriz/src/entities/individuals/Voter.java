package entities.individuals;
import java.util.*;

import projects.Project; 

public abstract class Voter {
	
	public Voter() {
		
	}

	public abstract boolean vote(Project p);
	public abstract Set<User> count();

}
