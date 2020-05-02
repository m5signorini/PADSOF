/**
 * 
 */
package modelo.exceptions;

import modelo.projects.Project;

/**
 * @author mssig
 *
 */
public class SendException extends Exception {
	
	private Project project;
	private Exception reason;
	
	public SendException(Project p, Exception r) {
		project = p;
		reason = r;
	}
	
	
	public String toString() {
		return "Project " + project.getTitle() + " could not be sent because: " + reason;
	}
}
