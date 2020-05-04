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


/**
 * The ControlInicio class implements the actions that should be performed when 
 * the different bottons in the Inicio view are clicked.
 * @author Pedro Urbina Rodriguez 
 * @author Martin Sanchez Signorini
 */

public class ControlInicio implements ActionListener {
	
	private Inicio vista;
	private Ventana frame;
	private Application modelo;
	
	public ControlInicio(Ventana frame, Application modelo) {
		this.frame = frame;
		this.vista = frame.getVistaInicio();
		this.modelo = modelo;
	}
	
	/**
	 * When the event occurs in the views which have this controller correctly set,
	 * different actions are performed depending on the origin of the action.
	 * @param e ActionEvent which caused the controller to act.
	 * @return None.
	 */
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
	 * Changes the view, making invisible the login view and making visible the administrator's
	 * login view.
	 * @return None.
	 */	
	private void cambioLoginAdmin() {
		InicioAdmin nuevaVista = frame.getVistaInicioAdmin();
		nuevaVista.update();
		nuevaVista.setVisible(true);
		vista.setVisible(false);
		frame.pack();
	}
	
	/**
	 * Changes the view, making invisible the login view and making visible the view
	 * to register a new user.
	 * @return None.
	 */	
	private void cambioRegistro() {
		Registro nuevaVista = frame.getVistaRegistro();
		nuevaVista.update();
		nuevaVista.setVisible(true);
		vista.setVisible(false);
		frame.pack();		
	}
	
	/**
	 * Tries to login with the written information on the JLabels.
	 * @return True in case login was successful, false in other case.
	 */	
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
	
	/**
	 * Tries to login with the written information on the JLabels and if login was successful,
	 * MiPagina will be shown with the updated data of the logged in user.
	 * @return None.
	 */
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

		frame.getControlPantallaPrincipal().actualizarMiPagina();
		frame.pack();
		frame.setLocationRelativeTo(null);
	}	
}
