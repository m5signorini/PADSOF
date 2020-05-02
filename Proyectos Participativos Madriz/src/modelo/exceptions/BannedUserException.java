/**
 * 
 */
package modelo.exceptions;

import modelo.entities.individuals.*;

/**
 * @author mssig
 *
 */
public class BannedUserException extends UserException {
	
	public BannedUserException(User u) {
		super(u);
	}
	
	public String toString() {
		return user + " has been banned until " + user.getUnbanDate();
	}
}
