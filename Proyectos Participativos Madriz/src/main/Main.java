package main;

import java.awt.EventQueue;

import controlador.Controlador;
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
				try {
					Ventana frame = new Ventana();
					Application app = Application.readFromFile("data");					
					Controlador controlador = new Controlador(frame, app);
					frame.setControlador(controlador);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
