package controlador.principal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import modelo.*;
import modelo.entities.individuals.User;
import modelo.exceptions.BannedUserException;
import modelo.functionalities.Application;
import vista.*;
import vista.colectivos.CreateCollectiveView;
import vista.inicio.Inicio;
import vista.inicio.Registro;
import vista.principal.PantallaPrincipal;
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
		JButton button = (JButton)e.getSource();
		switch(button.getActionCommand()) {
		case "Validar":
			try {
				intentaLogin();
				break;
			} catch (BannedUserException e1) {
				e1.printStackTrace();
			}
		case "Cerrar Sesion":
			cerrarSesion();
			break;
		case "Crear Proyecto":
			CreateProjectView createProject = frame.getCreateProjectView();
			createProject.setVisible(true);
			//frame.getVistaInicio().setVisible(false);
			frame.pack();
			break;
		case "Crear Colectivo":
			CreateCollectiveView createCollective = frame.getCreateCollectiveView();
			createCollective.setVisible(true);
			//frame.getVistaInicio().setVisible(false);
			frame.pack();
			break;
		}
	}
	

	private void cerrarSesion() {
		modelo.writeToFile("data");	
	}
	
	private void intentaLogin() throws BannedUserException {
	
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
	
}