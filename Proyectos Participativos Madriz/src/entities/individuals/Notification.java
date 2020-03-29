/**
 * 
 */
package entities.individuals;

/**
 * @author eps
 *
 */
public class Notification {
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
