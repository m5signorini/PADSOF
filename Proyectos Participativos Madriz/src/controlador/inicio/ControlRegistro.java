package controlador.inicio;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JOptionPane;

import modelo.*;
import modelo.entities.individuals.User;
import modelo.functionalities.Application;
import vista.*;
import vista.inicio.Inicio;
import vista.inicio.Registro;
import vista.inicio.Ventana;

public class ControlRegistro implements ActionListener {
	
	private Ventana frame;
	private Registro vista;
	private Application app;
	
	public ControlRegistro(Ventana frame2, Application app) {
		this.frame = frame2;
		this.vista = this.frame.getVistaRegistro(); 
		this.app = app;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mostrarPanelRegistro();
	}
	
	private void mostrarPanelRegistro() {
		String name = vista.getName();
		String nif = vista.getNif();
		String pwd = vista.getPwd();
		
		if (name.equals("")) {
			JOptionPane.showMessageDialog(vista, "Debe introducir un nombre.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (nif.equals("")) {
			JOptionPane.showMessageDialog(vista, "Debe introducir un nif.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (pwd.equals("")) {
			JOptionPane.showMessageDialog(vista, "Debe introducir un pwd.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		User u = new User(name, pwd, nif);
		if (app.register(u)) {
			if (!app.validateUser(u)) {
				JOptionPane.showMessageDialog(null, "Error when validating user!");
				return;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Error when registering user!");
			return;
		}

		JOptionPane.showMessageDialog(null, "Now you can login!");
		

		Inicio nuevaVista = frame.getVistaInicio();
		nuevaVista.update();

		nuevaVista.setVisible(true);
		vista.setVisible(false);
		
		
	}
	
}
