package vista.principal;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AdminProjectsView extends JPanel{
	
	private JButton registers;
	private JButton logout;
	private JTabbedPane tabs;
	
	private JPanel projectList;
	
	private GridBagConstraints gbc;
	
	public AdminProjectsView() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1200, 700));
		
		gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
		
		// NORTH Panel
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("Administrar Registros");
		north.add(title);
		
		//WEST Panel
		JPanel west = new JPanel();
		west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
		
		registers = new JButton("Administrar Registros");
		logout = new JButton("Cerrar Sesión");
		west.add(new JLabel("Navegacion"));
		west.add(Box.createRigidArea(new Dimension(0, 10)));
		west.add(registers);
		west.add(logout);
		
		//CENTER Panel
		JPanel center = new JPanel();
		
		projectList = new JPanel(new GridBagLayout());
		projectList.add(new JPanel(), gbc);
        
		tabs = new JTabbedPane();
		tabs.addTab("Pendientes", new JScrollPane(projectList));
		
		center.add(tabs);
		
		// ADD ALL
		add(north, BorderLayout.NORTH);
		add(west, BorderLayout.WEST);
		add(center, BorderLayout.CENTER);
		
	}
	
	public void prepareList(JPanel list) {
		list.removeAll();
		list.revalidate();
		list.repaint();
	}
	
	public JPanel getProjects() {
		return projectList;
	}
	public GridBagConstraints getGbc() {
		return gbc;
	}
	
	public void setControladorLogout(ActionListener l) {
		logout.addActionListener(l);
	}
	
	public void setControladorGotoRegisters(ActionListener l) {
		registers.addActionListener(l);
	}
}
