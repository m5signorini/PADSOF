/**
 * 
 */
package entities.individuals;

import java.io.*;
/**
 * Class used to send messages to users, it simply consists of a title and a text.
 * @author Pedro Urbina Rodriguez
 * 
 */
public class Notification implements Serializable{
	private String title;
	private String text;
	
	public Notification(String title, String text) {
		this.title = title;
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String toString() {
		return "Title: "+ title + ". Text: " + text;
	}
	
}
