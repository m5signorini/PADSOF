package projects;

import java.io.*;
/**
 * This class represents the different places where
 * an infrastructural project will be done.
 * 
 * @author Cesar Ramirez
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
