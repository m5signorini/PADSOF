/**
 * 
 */
package modelo.exceptions;

import modelo.entities.individuals.User;
/**
 * @author mssig
 *
 */
public abstract class UserException extends Exception {
	protected User user;
	
	public UserException(User u) {
		user = u;
	}
	
	public String toString() {
		return "Exception occured, related to the use of user " + user;
	}
}
