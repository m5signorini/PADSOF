package controlador.inicio;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import modelo.*;
import modelo.entities.individuals.User;
import modelo.functionalities.Application;
import vista.*;
import vista.inicio.Inicio;
import vista.inicio.Registro;
import vista.principal.PantallaPrincipal;
import vista.proyectos.CreateProjectView;

public class ControlInicio implements ActionListener {
	
	private Inicio vista;
	private Ventana frame;
	private Application modelo;
	
	public ControlInicio(Ventana frame2, Application modelo) {
		this.frame = frame2;
		this.vista = frame2.getVistaInicio();
		this.modelo = modelo;
	}

	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		switch(button.getActionCommand()) {
		case "Validar":
			intentaLogin();
		case "Pulse aqui para registrarse":
			cambioRegistro();
		}
	}
	

	private void cambioRegistro() {
		Registro nuevaVista = frame.getVistaRegistro();
		nuevaVista.update();
		nuevaVista.setVisible(true);
		vista.setVisible(false);
		frame.pack();		
	}
	
	private void intentaLogin() {
	
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
		
		if(!modelo.login(nif, pwd)){
			JOptionPane.showMessageDialog(null, "Incorrect login! Please, try again.");
			vista.update();
			return;
		}	
		
		PantallaPrincipal pantallaPrincipal = frame.getVistaPantallaPrincipal();
		User u = modelo.getLoggedUser();
			
		JOptionPane.showMessageDialog(null, "Correctly logged in!");
		pantallaPrincipal.setVisible(true);
		frame.getVistaInicio().setVisible(false);
		frame.pack();

		pantallaPrincipal.setCreatedProjects(u.getCreatedProjects());
		pantallaPrincipal.setFollowedProjects(u.getFollowedProjects());
		pantallaPrincipal.setCollectives(u.getCollectives());
		pantallaPrincipal.setRepresentedCollectives(u.getRepresentedCollectives());
		pantallaPrincipal.setNotifications(u.getNotifications());
		
		pantallaPrincipal.update();
	}
	
	public Inicio getPanelVistaIn() {
		return frame.getVistaInicio();
	}
	
}
