/**
 * 
 */
package modelo.exceptions;

import java.util.Calendar;
import java.util.Locale;

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
		return user + " has been banned.";
	}
}
