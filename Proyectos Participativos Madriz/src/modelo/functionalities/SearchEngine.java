package functionalities;

import java.util.*;
import entities.individuals.*;
import entities.*;
import projects.*;
import java.io.*;
/**
 * Search engine of an application. Used for searching methods
 * @author Martin Sanchez Signorini
 *
 */
public class SearchEngine implements Serializable{
	
	private Application data;
	
	public SearchEngine(Application data) {
		this.data = data;
	}
	
	/**
	 * Method for searching collectives
	 * @param s String to look for
	 * @return Matched collectives
	 */
	public List<Collective> searchCollectives(String s) {
		if(s == null) return null;
		List<Collective> pool = data.getCollectives();
		List<Collective> result = new ArrayList<Collective>();
		for(Collective c: pool) {
			if(c.getName().contains(s)) {
				result.add(c);
			}
		}
		return result;
	}
	
	/**
	 * Method for searching a user by NIF
	 * @param nif String to look for
	 * @return Matched user
	 */
	public User searchUser(String nif) {
		if(nif == null) return null;
		List<User> pool = data.getRegisteredUsers();
		pool.addAll(data.getBannedUsers());
		pool.addAll(data.getUnregisteredUsers());
		for(User u: pool) {
			if(u.getNif().equals(nif)) {
				return u;
			}
		}
		return null;
	}
	
	/** 
	 * Private method to search for a project by title given a pool of projects
	 * @param title String that the project title must contain
	 * @param pool List of projects in which to look for
	 * @return List of found projects with partially matching titles
	 */
	private List<Project> searchProjects(String title, List<Project> pool) {
		if(title == null || pool == null) return null;
		List<Project> result = new ArrayList<Project>();
		for(Project p: pool) {
			if(p.getTitle().contains(title)) {
				result.add(p);
			}
		}
		return result;
	}
	
	/**
	 * Method for searching projects sent to council
	 * @param title String to look for
	 * @return Matched projects
	 */
	public List<Project> searchSentProjects(String title) {
		return searchProjects(title, data.getSentProjects());
	}
	
	/**
	 * Method for searching projects that expired
	 * @param title String to look for
	 * @return Matched projects
	 */
	public List<Project> searchExpiredProjects(String title) {
		return searchProjects(title, data.getExpiredProjects());
	}
	
	/**
	 * Method for searching projects available to vote
	 * @param title String to look for
	 * @return Matched projects
	 */
	public List<Project> searchPublicProjects(String title) {
		return searchProjects(title, data.getPublicProjects());
	}
	
	/**
	 * Method for searching projects pending administrator approval
	 * @param title String to look for
	 * @return Matched projects
	 */
	public List<Project> searchPendingProjects(String title) {
		return searchProjects(title, data.getPendingProjects());
	}
	
	/**
	 * Method for searching projects accepted by council
	 * @param title String to look for
	 * @return Matched projects
	 */
	public List<Project> searchFinanciatedProjects(String title) {
		return searchProjects(title, data.getFinanciatedProjects());
	}
	
	/**
	 * Method for searching projects denied by the administrator
	 * @param title String to look for
	 * @return Matched projects
	 */
	public List<Project> searchDeniedProjects(String title) {
		return searchProjects(title, data.getDeniedProjects());
	}
	
	/**
	 * Method for searching projects rejected by council
	 * @param title String to look for
	 * @return Matched projects
	 */
	public List<Project> searchRejectedProjects(String title) {
		return searchProjects(title, data.getRejectedProjects());
	}
	
	/**
	 * Method for getting social projects in a pool
	 * @param pool Pool of projects
	 * @result List of social projects in the pool
	 */
	public List<Project> getSocials(List<Project> pool) {
		if(pool == null) return null;
		List<Project> result = new ArrayList<Project>();
		for(Project p: pool) {
			if(p.getClass() == Social.class) {
				result.add(p);
			}
		}
		return result;
	}
	
	/**
	 * Method for getting infrastructure projects in a pool
	 * @param pool Pool of projects
	 * @result List of infrastructure projects in the pool
	 */
	public List<Project> getInfrastructurals(List<Project> pool) {
		if(pool == null) return null;
		List<Project> result = new ArrayList<Project>();
		for(Project p: pool) {
			if(p.getClass() == Infrastructural.class) {
				result.add(p);
			}
		}
		return result;
	}
}
