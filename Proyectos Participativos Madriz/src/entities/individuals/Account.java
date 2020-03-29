/**
 * 
 */
package entities.individuals;

/**
 * @author Pedro Urbina Rodriguez
 *
 */
public abstract class Account {
	
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
	
