package controlador.inicio;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import modelo.*;
import modelo.entities.individuals.User;
import modelo.exceptions.BannedUserException;
import modelo.functionalities.Application;
import vista.*;
import vista.inicio.*;
import vista.principal.PantallaPrincipal;
import vista.proyectos.CreateProjectView;

public class ControlInicioAdmin implements ActionListener {
	
	private InicioAdmin vista;
	private Ventana frame;
	private Application modelo;
	
	public ControlInicioAdmin(Ventana frame, Application modelo) {
		this.frame = frame;
		this.vista = frame.getVistaInicioAdmin();
		this.modelo = modelo;
	}

	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		switch(button.getActionCommand()) {
		case "Iniciar sesion como Administrador":
			intentaAdminLogin();
			break;
		case "Pulse aqui para volver":
			cambioInicio();
			break;
		}
	}
	
	private void cambioInicio() {
		Inicio nuevaVista = frame.getVistaInicio();
		nuevaVista.update();
		nuevaVista.setVisible(true);
		vista.setVisible(false);
		frame.pack();
	}
	
	private void loginCheck() {
		String pwd = vista.getPwd();
		if (pwd.equals("")) {
			JOptionPane.showMessageDialog(vista, "Debe introducir un pwd.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	private void intentaAdminLogin() {
		loginCheck();
		
		String pwd = vista.getPwd();
		
		if(!modelo.loginAdmin(pwd)){
			JOptionPane.showMessageDialog(null, "Incorrect login! Please, try again.");
			vista.update();
			return;
		}	
		
		PantallaPrincipal pantallaPrincipal = frame.getVistaPantallaPrincipal();
		User u = modelo.getLoggedUser();
			
		JOptionPane.showMessageDialog(null, "Correctly logged in!");
		
		frame.setAllInvisible();
		pantallaPrincipal.setVisible(true);
		frame.pack();
		
		pantallaPrincipal.setCreatedProjects(u.getCreatedProjects());
		pantallaPrincipal.setFollowedProjects(u.getFollowedProjects());
		pantallaPrincipal.setCollectives(u.getCollectives());
		pantallaPrincipal.setRepresentedCollectives(u.getRepresentedCollectives());
		pantallaPrincipal.setNotifications(u.getNotifications());
		
		pantallaPrincipal.update();
	}	
}
