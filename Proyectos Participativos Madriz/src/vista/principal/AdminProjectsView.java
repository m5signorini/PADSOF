package vista.principal;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeListener;

public class AdminProjectsView extends JPanel{
	
	private JButton registers;
	private JButton logout;
	private JTabbedPane tabs;
	
	private JSpinner apoyos;
	private JSpinner caduca;
	
	private JPanel projectList;
	
	public AdminProjectsView() {
		setLayout(new BorderLayout());
		//setPreferredSize(new Dimension(1200, 700));
		
		// NORTH Panel
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("Administrar Proyectos");
		title.setFont(title.getFont().deriveFont(64.0f));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		
		north.add(title);
		//WEST Panel
		JPanel west = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets = new Insets(0, 10, 0, 0);
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		JPanel box = new JPanel(new GridLayout(4, 1, 0, 100));
		registers = new JButton("Administrar Registros");
		logout = new JButton("Cerrar Sesión");
		JLabel nav = new JLabel("Navegacion");
		nav.setFont(nav.getFont().deriveFont(24f));
		
		box.add(nav);
		box.add(registers);
		box.add(logout);
		
		west.add(box, gbc);
		
		//CENTER Panel
		JPanel center = new JPanel(new GridBagLayout());
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
		
		projectList = new JPanel(new GridBagLayout());
		projectList.add(new JPanel(), gbc);
        
		tabs = new JTabbedPane();
		tabs.addTab("Pendientes", new JScrollPane(projectList));
		tabs.setPreferredSize(new Dimension(700, 500));
		
		center.add(tabs, gbc);
		
		// EAST Panel
		JPanel east = new JPanel(new GridBagLayout());
		
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		JPanel settings = new JPanel(new GridLayout(3, 1));
		JPanel caducaBox = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JPanel apoyosBox = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		caduca = new JSpinner(new SpinnerNumberModel(30,1, 365, 1));
		apoyos = new JSpinner(new SpinnerNumberModel(1000, 10, 999999, 1));
		
		caducaBox.add(new JLabel("Días hasta que un proyecto caduca: "));
		caducaBox.add(caduca);
		apoyosBox.add(new JLabel("Número de apoyos mínimos: "));
		apoyosBox.add(apoyos);
		settings.add(Box.createVerticalStrut(20));
		settings.add(caducaBox);
		settings.add(apoyosBox);
		
		east.add(settings, gbc);
		
		
		// ADD ALL
		add(north, BorderLayout.NORTH);
		add(west, BorderLayout.WEST);
		add(center, BorderLayout.CENTER);
		add(east, BorderLayout.EAST);
		
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
	
	public void setControladorGotoRegisters(ActionListener l) {
		registers.addActionListener(l);
	}
	
	public void setControladorCaduca(ChangeListener l) {
		caduca.addChangeListener(l);
	}
	
	public void setControladorApoyos(ChangeListener l) {
		apoyos.addChangeListener(l);
	}
	
	public Integer getCaduca() {
		return (Integer)caduca.getValue();
	}
	
	public Integer getApoyos() {
		return (Integer)apoyos.getValue();
	}
}
