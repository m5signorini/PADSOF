package modelo.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import modelo.entities.individuals.*;

public class NotificationTest {
	
	private Notification notification;
	@Test
	public void test() {
		notification = new Notification("Test", "This is a test to check if the notification class works properly.");
		assertEquals( "Title: Test. Text: This is a test to check if the notification class works properly.", notification.toString());
		assertEquals("Title: Test. Text: This is a test to check if the notification class works properly.", "Title: " + notification.getTitle() + ". Text: " + notification.getText());
	}

}
