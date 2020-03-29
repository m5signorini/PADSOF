/**
 * 
 */
package projects;

import java.util.*;

import entities.individuals.*;


/**
 * @author eps
 *
 */
public class Infrastructural extends Project {
	private String scheme;
	private String location;
	private ArrayList<District> affectedDistricts;
	
	public Infrastructural(String title, String description, Double cost, Date creationDate, Voter creator, String scheme, String location) {
		super(title, description, cost, creationDate, creator);
		this.scheme = scheme;
		this.location = location;
		this.affectedDistricts = new ArrayList<District>();
		creator.createProject(this);
	}

	public ArrayList<District> getAffectedDistricts() {
		return affectedDistricts;
	}

	public void setAffectedDistricts(ArrayList<District> affectedDistricts) {
		this.affectedDistricts = affectedDistricts;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getExtraData() {
		return super.getExtraData() + this.scheme + this.location;
	}
	
	public ProjectKind getProjectKind() {
		return ProjectKind.Infrastructure;
	}
	
}
