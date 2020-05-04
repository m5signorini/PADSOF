package controlador.inicio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modelo.entities.individuals.User;
import modelo.functionalities.Application;
import vista.Ventana;
import vista.inicio.Inicio;
import vista.inicio.Registro;

/**
 * The ControlRegistro class implements the actions that should be performed when 
 * the different buttons in the Registro view are clicked.
 * @author Pedro Urbina Rodriguez 
 */

public class ControlRegistro implements ActionListener {
	
	private Ventana frame;
	private Registro vista;
	private Application app;
	
	public ControlRegistro(Ventana frame2, Application app) {
		this.frame = frame2;
		this.vista = this.frame.getVistaRegistro(); 
		this.app = app;
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
		case "Registrarse":
			intentarRegistro();
			break;
		case "Pulse aqui para iniciar sesion":
			cambioInicio();
			break;
		}
	}

	/**
	 * Changes the view, making invisible the register view and making visible the login view.
	 * @return None.
	 */	
	private void cambioInicio() {
		Inicio nuevaVista = frame.getVistaInicio();
		nuevaVista.update();
		nuevaVista.setVisible(true);
		vista.setVisible(false);
		frame.pack();	
	}
	
	/**
	 * Tries to register with the written information on the JLabels and if register was successful,
	 * the login view will be displayed, having the user for the administrator's verification to login.
	 * @return None.
	 */
	private void intentarRegistro() {
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
		if (!app.register(u)) {
			JOptionPane.showMessageDialog(null, "Error when registering user!");
			return;
		}

		JOptionPane.showMessageDialog(null, "You will be able to login when the admin decides!");
		

		Inicio nuevaVista = frame.getVistaInicio();
		nuevaVista.update();

		nuevaVista.setVisible(true);
		vista.setVisible(false);		
	}
	
}
