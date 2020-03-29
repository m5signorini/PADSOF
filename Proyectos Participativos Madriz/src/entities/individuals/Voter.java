package entities.individuals;
import java.util.*;

import projects.Project; 

public interface Voter {

	Set<User> count();
	boolean addVotedProject(Project p);
	boolean addCreatedProject(Project p);

}
