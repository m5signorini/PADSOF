package vista.principal;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import modelo.entities.individuals.User;

public class BanDialog extends JDialog {
	
	private User banned;
	
	private JButton ban;
	private JTextField message;
	private JSpinner time;
	
	public BanDialog(User u, JFrame top) {
		super(top, "Banear Usuario", true);
		banned = u;
		
		setLocationRelativeTo(null);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		JPanel box = new JPanel(new GridLayout(5, 1));
		JPanel messageBox = new JPanel();
		JPanel timeBox = new JPanel();
		
		message = new JTextField();
		message.setColumns(10);
		time = new JSpinner(new SpinnerNumberModel(30, 1, 365, 1));
		ban = new JButton("Banear");
		
		
		box.add(new JLabel("Usuario a banear: " + u.getNif()));
		messageBox.add(new JLabel("Motivo: "));
		messageBox.add(message);
		box.add(messageBox);
		timeBox.add(new JLabel("Duracion en dias: "));
		timeBox.add(time);
		box.add(timeBox);
		box.add(ban);
		add(box);
	}
	
	public String getMessage() {
		return message.getText();
	}
	
	public Integer getDays() {
		return (Integer)time.getValue();
	}
	
	public User getUser() {
		return banned;
	}
	
	public void setControlador(ActionListener l) {
		ban.addActionListener(l);
	}
}
