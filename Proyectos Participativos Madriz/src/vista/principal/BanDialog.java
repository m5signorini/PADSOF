package vista.principal;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import modelo.entities.individuals.User;

public class BanDialog extends JDialog {
	
	private User banned;
	
	private JButton ban;
	private JTextField message;
	private JTextField time;
	
	public BanDialog(User u, JFrame top) {
		super(top, "Banear Usuario", true);
		banned = u;
		
		setLayout(new FlowLayout());
		
		message = new JTextField();
		message.setColumns(20);
		time = new JTextField();
		time.setColumns(20);
		ban = new JButton("Banear");
		
		setPreferredSize(new Dimension(1200, 200));
		
		add(new JLabel("Usuario a banear: " + u.getNif()));
		add(Box.createHorizontalStrut(20));
		add(new JLabel("Motivo: "));
		add(message);
		add(Box.createHorizontalStrut(20));
		add(new JLabel("Duracion en dias: "));
		add(time);
		add(Box.createHorizontalStrut(20));
		add(ban);
	}
	
	public String getMessage() {
		return message.getText();
	}
	
	public String getDays() {
		return time.getText();
	}
	
	public User getUser() {
		return banned;
	}
	
	public void setControlador(ActionListener l) {
		ban.addActionListener(l);
	}
}
