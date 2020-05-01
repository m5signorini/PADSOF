package vista.inicio;

import javax.swing.*;
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
	

		botonRegistro = new JButton("Registrarse");
		add(botonRegistro);

		botonInicio = new JButton("Iniciar sesion");
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
