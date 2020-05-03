package vista.principal;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AdminRegistersView extends JPanel{
	
	private JButton projects;
	private JButton logout;
	private JTabbedPane tabs;
	
	private JPanel pendingList;
	private JPanel registeredList;
	
	public AdminRegistersView() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1200, 700));
		
		GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridwidth = GridBagConstraints.REMAINDER;
        gbc1.weightx = 1;
        gbc1.weighty = 1;
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridwidth = GridBagConstraints.REMAINDER;
        gbc2.weightx = 1;
        gbc2.weighty = 1;
		
		// NORTH Panel
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("Administrar Registros");
		north.add(title);
		
		//WEST Panel
		JPanel west = new JPanel();
		west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
		
		projects = new JButton("Administrar Proyectos");
		logout = new JButton("Cerrar Sesión");
		west.add(new JLabel("Navegacion"));
		west.add(Box.createRigidArea(new Dimension(0, 10)));
		west.add(projects);
		west.add(logout);
		
		//CENTER Panel
		JPanel center = new JPanel();
		
		pendingList = new JPanel(new GridBagLayout());
		registeredList = new JPanel(new GridBagLayout());
		
		pendingList.add(new JPanel(), gbc1);
		registeredList.add(new JPanel(), gbc2);
        
		tabs = new JTabbedPane();
		tabs.addTab("Pendientes", new JScrollPane(pendingList));
		tabs.addTab("Registrados", new JScrollPane(registeredList));
		tabs.setPreferredSize(new Dimension(700, 500));
		
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
	
	public JPanel getPendingList() {
		return pendingList;
	}
	public JPanel getRegisteredList() {
		return registeredList;
	}
	public GridBagConstraints getGbc() {
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        /*gbc.anchor = GridBagConstraints.SOUTHEAST;*/
        return gbc;
	}
	
	public void setControladorLogout(ActionListener l) {
		logout.addActionListener(l);
	}
	
	public void setControladorGotoProjects(ActionListener l) {
		projects.addActionListener(l);
	}
}
