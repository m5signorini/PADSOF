package modelo.entities.individuals;
import java.util.*;

import modelo.projects.Project; 


/**
 * Interface we used join the things all voters have in common, including functions to count votes, 
 * voting and creating projects. User and Collective both implement the functions in this Interface.
 * @author Pedro Urbina Rodriguez
 * 
 */
public interface Voter {

	Set<User> count();
	boolean addVotedProject(Project p);
	boolean addCreatedProject(Project p);

}
