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

public class InicioAdmin extends JPanel {

	private JTextField campoPwd;
	private JButton botonLoginAdmin;
	private JButton botonVolver;
	
	public InicioAdmin() {
				
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel etiqueta = new JLabel("INICIAR COMO ADMIN:");
		etiqueta.setAlignmentX(CENTER_ALIGNMENT);
		add(etiqueta);

		add(Box.createRigidArea(new Dimension(0, 10)));
		
		JPanel cont = new JPanel();
		cont.setLayout(new GridLayout(2, 2, 5, 10));
		
		JLabel pwd = new JLabel("Password:");
		cont.add(pwd);		
		campoPwd = new JPasswordField();
		cont.add(campoPwd);
		add(cont);
		
		add(Box.createRigidArea(new Dimension(0, 20)));
		
		botonLoginAdmin = new JButton("Iniciar sesion como Administrador");
		botonLoginAdmin.setAlignmentX(CENTER_ALIGNMENT);
		add(botonLoginAdmin);
		
		add(Box.createRigidArea(new Dimension(0, 10)));
		
		botonVolver = new JButton("Pulse aqui para volver");
		botonVolver.setAlignmentX(CENTER_ALIGNMENT);
		add(botonVolver);
	}

	public void setControladorLoginAdmin(ActionListener c) {  
		botonLoginAdmin.addActionListener(c);
	}
	
	public void setControladorVolver(ActionListener c) {  
		botonVolver.addActionListener(c);
	}

	public String getPwd() {
		return campoPwd.getText();
	}
	
	public void update () {
		campoPwd.setText("");
		campoPwd.grabFocus();
	}
}
