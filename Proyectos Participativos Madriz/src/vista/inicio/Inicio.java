package vista.inicio;

import javax.swing.*;
import java.awt.event.*;

public class Inicio extends JPanel {

	private JTextField campoNif;
	private JTextField campoPwd;
	private JButton botonValidar;
	private JButton botonRegistro;
	
	public Inicio() {
		
		JLabel etiqueta = new JLabel("Inicio de sesion:");
		add(etiqueta);

		campoNif = new JTextField();
		add(campoNif);
		campoNif.setColumns(10);
		campoNif.requestFocus();
		
		campoPwd = new JTextField();
		add(campoPwd);
		campoPwd.setColumns(10);
		campoPwd.requestFocus();
		
		botonValidar = new JButton("Validar");
		add(botonValidar);
		
		botonRegistro = new JButton("Pulse aqui para registrarse");
		add(botonRegistro);
		
	}

	public void setControladorValidar(ActionListener c) {  
		botonValidar.addActionListener(c);
	}
	
	public void setControladorRegistro(ActionListener c) {  
		botonRegistro.addActionListener(c);
	}

	public String getNif() {
		return campoNif.getText();
	}

	public String getPwd() {
		return campoPwd.getText();
	}
	
	public void update () {
		campoNif.setText("");
		campoPwd.setText("");
		campoNif.grabFocus();
		campoPwd.grabFocus();
	}

}
