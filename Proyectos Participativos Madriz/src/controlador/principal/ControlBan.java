package controlador.principal;

import java.awt.event.*;

import javax.swing.JOptionPane;

import modelo.functionalities.Application;
import vista.principal.BanDialog;

/**
 * Controller class for the ban dialog
 * @author Martin Sanchez Signorini
 *
 */
public class ControlBan implements ActionListener {
	private ControlAdmin top;
	private BanDialog vista;
	private Application modelo;
	
	public ControlBan(BanDialog dialog, Application model, ControlAdmin toplevel) {
		vista = dialog;
		modelo = model;
		top = toplevel;
	}
	
	/**
	 *  Action for the ban dialog
	 */
	public void actionPerformed(ActionEvent e) {
		String message = vista.getMessage();
		Integer time = vista.getDays();
		
		if(!modelo.ban(vista.getUser(), message, time)) {
			JOptionPane.showMessageDialog(vista, "No se ha podido banear al usuario.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		JOptionPane.showMessageDialog(vista, "Baneo completado correctamente.");
		vista.dispose();
		top.update();
	}
}