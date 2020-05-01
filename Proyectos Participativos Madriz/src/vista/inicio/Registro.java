package vista.inicio;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import controlador.*;

public class Registro extends JPanel {

	private JButton botonRegistro;
	private JButton botonInicio;
	private JLabel etiqueta;
	private JTextField campoNif;
	private JTextField campoPwd;
	private JTextField campoNombre;
	
	public Registro() { 

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		etiqueta = new JLabel("REGISTRO: ");
		etiqueta.setAlignmentX(CENTER_ALIGNMENT);
		add(etiqueta);

		add(Box.createRigidArea(new Dimension(0, 10)));
		
		etiqueta = new JLabel("Por favor, introduzca sus datos: ");
		etiqueta.setAlignmentX(CENTER_ALIGNMENT);
		add(etiqueta);
		
		add(Box.createRigidArea(new Dimension(0, 10)));
		
		JPanel cont = new JPanel();
		cont.setLayout(new GridLayout(3, 2, 5, 10));
		
		etiqueta = new JLabel("Nombre: ");
		cont.add(etiqueta);
		campoNombre = new JTextField();
		cont.add(campoNombre);
		
		etiqueta = new JLabel("Nif: ");
		cont.add(etiqueta);
		campoNif = new JTextField();
		cont.add(campoNif);
		
		etiqueta = new JLabel("Contraseña: ");
		cont.add(etiqueta);
		campoPwd = new JPasswordField();
		cont.add(campoPwd);
		
		add(cont);	
		
		add(Box.createRigidArea(new Dimension(0, 10)));

		botonRegistro = new JButton("Registrarse");
		botonRegistro.setAlignmentX(CENTER_ALIGNMENT);
		add(botonRegistro);

		add(Box.createRigidArea(new Dimension(0, 10)));
		
		botonInicio = new JButton("Pulse aqui para iniciar sesion");
		botonInicio.setAlignmentX(CENTER_ALIGNMENT);
		add(botonInicio);
	}
	
	public String getName() {
		return campoNombre.getText();
	}
	
	public String getNif() {
		return campoNif.getText();
	}
	
	public String getPwd() {
		return campoPwd.getText();
	}

	public void setControladorRegistro(ActionListener c) {
		botonRegistro.addActionListener(c);
	}

	public void setControladorCambioInicio(ActionListener c) {
		botonInicio.addActionListener(c);
	}
		
	public void update() { 
		campoNif.setText("");
		campoNombre.setText("");
		campoPwd.setText("");
		campoNif.grabFocus();
		campoNombre.grabFocus();
		campoPwd.grabFocus();
	}
	
}
