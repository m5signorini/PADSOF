package main;

import java.awt.EventQueue;

import controlador.Controlador;
import modelo.entities.individuals.Admin;
import modelo.functionalities.Application;
import vista.Ventana;
import vista.proyectos.CreateProjectView;
import vista.proyectos.CreateProjectView;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {						
					//CreateProjectView projectView = new CreateProjectView();
					Ventana frame = new Ventana();
					Application app = Application.getApplication();
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
