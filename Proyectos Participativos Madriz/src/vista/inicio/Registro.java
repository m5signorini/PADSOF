package vista.inicio;

import javax.swing.*;
import java.awt.event.*;
import controlador.*;

public class Registro extends JPanel {

	private JButton boton;
	private JLabel etiqueta;
	private JTextField campoNif;
	private JTextField campoPwd;
	private JTextField campoNombre;
	
	public Registro() { 

		etiqueta = new JLabel("Introduzca sus datos: ");
		add(etiqueta);
		
		campoNombre = new JTextField();
		add(campoNombre);
		campoNombre.setColumns(10);
		campoNombre.requestFocus();
		
		campoNif = new JTextField();
		add(campoNif);
		campoNif.setColumns(10);
		campoNif.requestFocus();
		
		campoPwd = new JTextField();
		add(campoPwd);
		campoPwd.setColumns(10);
		campoPwd.requestFocus();
	
		
		boton = new JButton("Registrarse");
		add(boton);
	}

	public void setControlador(ActionListener c) {
		boton.addActionListener(c);
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
