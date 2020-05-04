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

public class ControlInicio implements ActionListener {
	
	private Inicio vista;
	private Ventana frame;
	private Application modelo;
	
	public ControlInicio(Ventana frame, Application modelo) {
		this.frame = frame;
		this.vista = frame.getVistaInicio();
		this.modelo = modelo;
	}

	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		switch(button.getActionCommand()) {
		case "Validar":
			intentaLogin();
			break;
		case "Pulse aqui para registrarse":
			cambioRegistro();
			break;
		case "Entrar como administrador":
			cambioLoginAdmin();
			break;
		}
	}
	

	
	/**
	 * Returns true if the login is correct, false otherwise.
	 * @param nif String containing the nif of the user trying to login.
	 * @param pwd String containing password written by the user trying to login.
	 * @return True if the login is correct, false otherwise.
	 */
	
	private void cambioLoginAdmin() {
		InicioAdmin nuevaVista = frame.getVistaInicioAdmin();
		nuevaVista.update();
		nuevaVista.setVisible(true);
		vista.setVisible(false);
		frame.pack();
	}

	private void cambioRegistro() {
		Registro nuevaVista = frame.getVistaRegistro();
		nuevaVista.update();
		nuevaVista.setVisible(true);
		vista.setVisible(false);
		frame.pack();		
	}
	
	private boolean loginCheck() {
		String nif = vista.getNif();
		if (nif.equals("")) {
			JOptionPane.showMessageDialog(vista, "Debe introducir un nif.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		String pwd = vista.getPwd();
		if (pwd.equals("")) {
			JOptionPane.showMessageDialog(vista, "Debe introducir un pwd.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	private void intentaLogin() {
		if(!loginCheck()) return;
	
		String nif = vista.getNif();
		String pwd = vista.getPwd();
		
		try {
			if(!modelo.login(nif, pwd)){
				JOptionPane.showMessageDialog(null, "Incorrect login! Please, try again.");
				vista.update();
				return;
			}
		}
		catch(BannedUserException ex) {
			JOptionPane.showMessageDialog(null, ex);
			vista.update();
			return;
		}
		
		PantallaPrincipal pantallaPrincipal = frame.getVistaPantallaPrincipal();
		User u = modelo.getLoggedUser();
		
		JOptionPane.showMessageDialog(null, "Correctly logged in!");
		
		frame.setAllInvisible();
		pantallaPrincipal.setVisible(true);
		
		/*pantallaPrincipal.setCreatedProjects(u.getCreatedProjects());
		pantallaPrincipal.setVotedProjects(u.getVotedProjects());
		pantallaPrincipal.setFollowedProjects(u.getFollowedProjects());
		pantallaPrincipal.setCollectives(u.getCollectives());
		pantallaPrincipal.setRepresentedCollectives(u.getRepresentedCollectives());
		pantallaPrincipal.setNotifications(u.getNotifications());
		
		pantallaPrincipal.update();*/
		frame.getControlPantallaPrincipal().actualizarMiPagina();
		frame.pack();
		frame.setLocationRelativeTo(null);
	}	
}
