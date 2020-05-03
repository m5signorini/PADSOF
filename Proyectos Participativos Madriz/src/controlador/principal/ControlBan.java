package controlador.principal;

import java.awt.event.*;

import javax.swing.JOptionPane;

import modelo.functionalities.Application;
import vista.principal.BanDialog;

public class ControlBan implements ActionListener {
	private ControlAdmin top;
	private BanDialog vista;
	private Application modelo;
	
	public ControlBan(BanDialog dialog, Application model, ControlAdmin toplevel) {
		vista = dialog;
		modelo = model;
		top = toplevel;
	}
	
	public void actionPerformed(ActionEvent e) {
		String message = vista.getMessage();
		String time = vista.getDays();
		int days = 0;
		
		try {
			days = Integer.parseInt(time.trim());
			if(days <= 0) {
				throw new Exception();
			}
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(vista, "Debe introducir un numero positivo entero de dias.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(!modelo.ban(vista.getUser(), message, days)) {
			JOptionPane.showMessageDialog(vista, "No se ha podido banear al usuario.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		JOptionPane.showMessageDialog(vista, "Baneo completado correctamente.");
		vista.dispose();
		top.update();
	}
}