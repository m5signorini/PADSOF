/**
 * 
 */
package modelo.entities.individuals;

import java.io.*;

/**
 * Abstract class from which Admin and User inherit. This forces both classes to have a login function
 * having both of them a name and a password in order to login.
 * @author Pedro Urbina Rodriguez
 * 
 */
public abstract class Account implements Serializable {
	
	protected String name;
	protected String pwd;
	
	public Account(String n, String p) {
		name = n;
		pwd = p;
	}
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getPwd() { return pwd; }
	public void setPwd(String pwd) { this.pwd = pwd; }
	
	public abstract boolean login(String nif, String pwd);

}
	
