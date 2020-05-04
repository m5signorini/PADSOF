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
	/**
	 * View of admin registers
	 * @author Martin Sanchez Signorini
	 *
	 */
	public AdminRegistersView() {
		setLayout(new BorderLayout());
		//setPreferredSize(new Dimension(1200, 700));
		
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
		
		// NORTH Panel
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("Administrar Registros");
		title.setFont(title.getFont().deriveFont(64.0f));
		title.setAlignmentX(CENTER_ALIGNMENT);
		north.add(title);
		
		//WEST Panel
		JPanel west = new JPanel(new GridBagLayout());
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.anchor = GridBagConstraints.NORTH;
		gbc2.insets = new Insets(0, 10, 0, 0);
		gbc2.weightx = 1;
		gbc2.weighty = 1;
		
		JPanel box = new JPanel(new GridLayout(3, 1, 0, 100));
		projects = new JButton("Administrar Proyectos");
		logout = new JButton("Cerrar Sesión");
		JLabel nav = new JLabel("Navegacion");
		
		nav.setFont(nav.getFont().deriveFont(24f));
		
		box.add(nav);
		box.add(projects);
		box.add(logout);
		
		west.add(box, gbc2);
		
		//CENTER Panel
		JPanel center = new JPanel();
		
		pendingList = new JPanel(new GridBagLayout());
		registeredList = new JPanel(new GridBagLayout());
		
		pendingList.add(new JPanel(), gbc);
		registeredList.add(new JPanel(), gbc);
        
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
