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

public class Social extends Project {
	private ScopeType scope;
	private String group;
	private String picture;
	
	public Social(String title, String description, Double cost, Date creationDate, Voter creator, ScopeType scope, String group, String picture) {
		super(title, description, cost, creationDate, creator);
		this.scope = scope;
		this.group = group;
		this.picture = picture;
	}

	public ScopeType getScope() {
		return scope;
	}

	public void setScope(ScopeType scope) {
		this.scope = scope;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public String getExtraData() {
		return super.getExtraData() + String.valueOf(this.scope) + this.group + this.picture;
	}
	
	public ProjectKind getProjectKind() {
		return ProjectKind.Social;
	}
}
