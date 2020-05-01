package main;

import java.awt.EventQueue;
import modelo.entities.individuals.Admin;
import modelo.functionalities.Application;
import vista.inicio.Ventana;
import controlador.inicio.Controlador;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {						
					Ventana frame = new Ventana();
					Application app = new Application(new Admin("admin"), 2, 2);
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
