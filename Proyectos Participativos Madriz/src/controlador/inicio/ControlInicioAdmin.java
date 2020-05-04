package controlador.inicio;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controlador.principal.ControlAdmin;
import modelo.*;
import modelo.entities.individuals.User;
import modelo.exceptions.BannedUserException;
import modelo.functionalities.Application;
import vista.*;
import vista.inicio.*;
import vista.principal.AdminRegistersView;
import vista.principal.PantallaPrincipal;
import vista.proyectos.CreateProjectView;

/**
 * Controller class for the login as admin view
 * @author Martin Sanchez Signorini
 *
 */
public class ControlInicioAdmin implements ActionListener {
	
	private InicioAdmin vista;
	private Ventana frame;
	private Application modelo;
	
	public ControlInicioAdmin(Ventana frame, Application modelo) {
		this.frame = frame;
		this.vista = frame.getVistaInicioAdmin();
		this.modelo = modelo;
	}
	
	/**
	 * Generic action performed fo the view
	 */
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
	
	/**
	 * Method for changing view to login as usual
	 */
	private void cambioInicio() {
		Inicio nuevaVista = frame.getVistaInicio();
		nuevaVista.update();
		nuevaVista.setVisible(true);
		vista.setVisible(false);
		frame.pack();
	}
	
	/**
	 * Method for checking input
	 * @return boolean true if input was correct syntactically
	 */
	private boolean loginCheck() {
		String pwd = vista.getPwd();
		if (pwd.equals("")) {
			JOptionPane.showMessageDialog(vista, "Debe introducir un pwd.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	/**
	 * Method for trying to login as administrator
	 */
	private void intentaAdminLogin() {
		if(!loginCheck()) return;
		
		String pwd = vista.getPwd();
		
		if(!modelo.loginAdmin(pwd)){
			JOptionPane.showMessageDialog(null, "Incorrect login! Please, try again.");
			vista.update();
			return;
		}
		
		AdminRegistersView adminRegs = frame.getVistaAdminRegisters();
		ControlAdmin contAdmin = frame.getControlAdmin();
			
		JOptionPane.showMessageDialog(null, "Correctly logged in!");
		
		frame.setAllInvisible();
		adminRegs.setVisible(true);
		contAdmin.update();
		frame.pack();
		frame.setLocationRelativeTo(null);
	}	
}
