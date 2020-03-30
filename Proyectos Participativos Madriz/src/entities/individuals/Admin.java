/**
 * 
 */
package entities.individuals;

/**
 * Class representing the Administrator's account. With a already defined name, he can 
 * mainly login as an administrator of the application.
 * @author Pedro Urbina Rodriguez
 * 
 */
public class Admin extends Account {	
	public Admin(String pwd) {
		super("Administrator", pwd);
	}
	
	public boolean login(String name, String pwd) {
		if(this.pwd.equals(pwd) && name.equals("Administrator")) return true;
		return false;
	}
	
	public String toString() {
		return "Name: " + name+ ". Password: " + pwd+ ".";
	}
}
