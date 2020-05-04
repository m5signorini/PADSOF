package vista.inicio;

import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

/**
 * The Inicio class is an extension of JPanels which contains all the swing
 * components to create the view in order to login into the application, 
 * once all ActionListeners have been set.
 * @author Pedro Urbina Rodriguez 
 */

public class Inicio extends JPanel {

	private JTextField campoNif;
	private JTextField campoPwd;
	private JButton botonValidar;
	private JButton botonRegistro;
	private JButton botonAdmin;
	
	public Inicio() {
				
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel etiqueta = new JLabel("INICIO DE SESION:");
		etiqueta.setAlignmentX(CENTER_ALIGNMENT);
		add(etiqueta);

		add(Box.createRigidArea(new Dimension(0, 10)));
		
		JPanel cont = new JPanel();
		cont.setLayout(new GridLayout(2, 2, 5, 10));		
		
		JLabel nif = new JLabel("Nif:");	
		cont.add(nif);	
		campoNif = new JTextField();
		cont.add(campoNif);
		
		JLabel pwd = new JLabel("Password:");
		cont.add(pwd);		
		campoPwd = new JPasswordField();
		cont.add(campoPwd);
		
		add(cont);
		
		add(Box.createRigidArea(new Dimension(0, 20)));
		
		botonValidar = new JButton("Validar");
		botonValidar.setAlignmentX(CENTER_ALIGNMENT);
		add(botonValidar);
		
		add(Box.createRigidArea(new Dimension(0, 10)));

		botonRegistro = new JButton("Pulse aqui para registrarse");
		botonRegistro.setAlignmentX(CENTER_ALIGNMENT);
		add(botonRegistro);
		
		add(Box.createRigidArea(new Dimension(0, 10)));
		
		botonAdmin = new JButton("Entrar como administrador");
		botonAdmin.setAlignmentX(CENTER_ALIGNMENT);
		add(botonAdmin);
		
	}

	
	/**
	 * Sets all the ActionListeners of all the buttons in the Inicio class.
	 * @param c ActionListener for the buttons in Inicio.
	 * @return None.
	 */
	public void setControladores(ActionListener c) {  
		botonValidar.addActionListener(c); 
		botonAdmin.addActionListener(c);  
		botonRegistro.addActionListener(c);
	}
	
	/**
	 * Returns the text written as a String in the nif JLabel.
	 * @return A String containing the text written by the User in campoNif.
	 */
	public String getNif() {
		return campoNif.getText();
	}

	
	/**
	 * Returns the text written as a String in the pwd JLabel.
	 * @return A String containing the text written by the User in campoPwd.
	 */
	public String getPwd() {
		return campoPwd.getText();
	}
	
	/**
	 * Clears the all the JLabels in the view.
	 * @return None.
	 */
	public void update () {
		campoNif.setText("");
		campoPwd.setText("");
		campoNif.grabFocus();
	}
}
