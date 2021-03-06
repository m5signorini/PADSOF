/**
 * 
 */
package modelo.projects;

import java.util.*;

import modelo.entities.individuals.*;


/**
 * This class represents the projects of social type.
 * This kind of projects will affect one specific group
 * of the society.
 * 
 * @author Cesar Ramirez
 *
 */

public class Social extends Project {
	private ScopeType scope;
	private String group;
	private String picture;
	
	public Social(String title, String description, Double cost, Date creationDate, Voter creator, ScopeType scope, String group) {
		super(title, description, cost, creationDate, creator);
		this.scope = scope;
		this.group = group;
		creator.addCreatedProject(this);
	}
	
	public Social(String title, String description, Double cost, Date creationDate, Voter creator, ScopeType scope, String group, String picture) {
		this(title, description, cost, creationDate, creator, scope, group);
		this.picture = picture;
		creator.addCreatedProject(this);
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

	public String getImagePath() {
		return picture;
	}

	public void setImagePath(String picture) {
		this.picture = picture;
	}
	
	public String typeToString() {
		return "Social";
	}
	
	public String getExtraData() {
		return super.getExtraData() + String.valueOf(this.scope) + this.group + this.picture;
	}
	
	public ProjectKind getProjectKind() {
		return ProjectKind.Social;
	}
}
