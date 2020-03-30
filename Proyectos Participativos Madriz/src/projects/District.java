/**
 * 
 */
package projects;

import java.io.*;
/**
 * @author eps
 *
 */
public class District implements Serializable{
	private String name;
	
	public District(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
