package controlador.principal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import modelo.*;
import modelo.entities.individuals.User;
import modelo.functionalities.Application;
import vista.*;
import vista.inicio.Inicio;
import vista.inicio.Registro;
import vista.proyectos.CreateProjectView;

public class ControlPantallaPrincipal implements ActionListener {
	
	private Inicio vista;
	private Ventana frame;
	private Application modelo;
	
	public ControlPantallaPrincipal(Ventana frame2, Application modelo) {
		this.frame = frame2;
		this.vista = frame2.getVistaInicio();
		this.modelo = modelo;
	}

	public void actionPerformed(ActionEvent e) {
		
		String nif = vista.getNif();
		if (nif.equals("")) {
			JOptionPane.showMessageDialog(vista, "Debe introducir un nif.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String pwd = vista.getPwd();
		if (pwd.equals("")) {
			JOptionPane.showMessageDialog(vista, "Debe introducir un pwd.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(modelo.login(nif, pwd)){
			JOptionPane.showMessageDialog(null, "Correctly logged in!");
			frame.setVisible(false);
			CreateProjectView projectView = new CreateProjectView();
		} else {
			JOptionPane.showMessageDialog(null, "Incorrect login! Please, try again.");
		}	
		
		vista.update();
	
	}
	
	public Inicio getPanelVistaIn() {
		return frame.getVistaInicio();
	}
	
}
