package modelo.exceptions;

import modelo.entities.Collective;
import modelo.entities.individuals.User;

public class JoinException extends UserException {
	protected Collective join;
	protected Collective cause;
	public JoinException(User u, Collective tryJoin, Collective errorCause) {
		super(u);
		join = tryJoin;
		cause = errorCause;
	}
	
	public String toString() {
		return "User " + user + " could not join collective " + join + " because already in " + cause;
	}
}
