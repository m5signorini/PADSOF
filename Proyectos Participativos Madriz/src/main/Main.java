package main;

import java.awt.EventQueue;

import controlador.Controlador;
import modelo.entities.individuals.Admin;
import modelo.functionalities.Application;
import vista.Ventana;


/**
 * The Main class executes the application we have developed, creating the necessary objects in 
 * order for the user to interact with the application and loading them from the files.
 * @author Pedro Urbina Rodriguez 
 */

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Ventana frame = new Ventana();
				Application app = Application.readFromFile("data");
				if(app == null) {
					Application.clearApplication();
					app = Application.getApplication();
					app.setAdmin(new Admin("admin"));
					app.setMinSupports(1000);
					app.setMaxInactivity(30);
					app.writeToFile("data");
					System.out.println("Guardado nuevo fichero data");
				}
				Controlador controlador = new Controlador(frame, app);
				frame.setControlador(controlador);
				frame.setVisible(true);
			}
		});
	}
}
