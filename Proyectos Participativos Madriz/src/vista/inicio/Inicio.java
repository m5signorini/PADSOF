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

	public void setControladorValidar(ActionListener c) {  
		botonValidar.addActionListener(c);
	}
	
	public void setControladorCambioRegistro(ActionListener c) {  
		botonRegistro.addActionListener(c);
	}
	
	public void setControladorCambioAdmin(ActionListener c) {  
		botonAdmin.addActionListener(c);
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
	}
}
