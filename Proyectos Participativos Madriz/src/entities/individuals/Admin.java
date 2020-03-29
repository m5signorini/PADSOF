/**
 * 
 */
package entities.individuals;

/**
 * @author 
 *
 */
public class Admin extends Account {	
	public Admin(String n, String p) {
		super(n, p);
	}
	
	public boolean login(String nif, String pwd) {
		if(this.pwd.equals(pwd))
				return true;
		return false;
	}
	
	public String toString() {
		return "Name: " + name+ ". Password: " + pwd+ ".";
	}
}
