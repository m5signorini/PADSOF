package entities.individuals;
import java.util.*;

import projects.Project; 

public interface Voter {

	boolean vote(Project p);
	Set<User> count();
	boolean addVotedProject(Project p);

}
