package controlador.principal;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeListener;

import modelo.entities.individuals.User;
import modelo.functionalities.Application;
import modelo.projects.Project;
import vista.*;
import vista.inicio.*;
import vista.principal.AdminProjectsView;
import vista.principal.AdminRegistersView;
import vista.principal.BanDialog;

/**
 * Controller for admin
 * @author Martin Sanchez Signorini
 *
 */
public class ControlAdmin {
	private Ventana frame;
	private AdminRegistersView viewRegisters;
	private AdminProjectsView viewProjects;
	private Application modelo;
	
	public ControlAdmin(Ventana mainFrame, Application modelo) {
		this.frame = mainFrame;
		this.viewRegisters = mainFrame.getVistaAdminRegisters();
		this.viewProjects = mainFrame.getVistaAdminProjects();
		this.modelo = modelo;
	}
	
	/**
	 * Setter for pending users
	 */
	private void setPendingList() {
		viewRegisters.prepareList(viewRegisters.getPendingList());
		// Set the list of pending registers
		for(User u: modelo.getUnregisteredUsers()) {
			JPanel reg = new JPanel();
            reg.add(new JLabel(u.getNif()));
            reg.add(new JLabel(u.getName()));
            
            // Approve and deny register buttons
            JButton approve = new JButton("Aprobar");
            JButton deny = new JButton("Rechazar");
            approve.addActionListener((e) ->{modelo.validateUser(u); update();});
            deny.addActionListener((e) -> {modelo.rejectUser(u); update();});
            
            reg.add(approve);
            reg.add(deny);
            
            viewRegisters.getPendingList().add(reg, viewRegisters.getGbc(), 0);
		}
		viewRegisters.getPendingList().validate();
		viewRegisters.getPendingList().repaint();
	}
	
	/**
	 * Setter for registered users
	 */
	private void setRegisteredList() {
		viewRegisters.prepareList(viewRegisters.getRegisteredList());
		// Set the list of registered registers
		for(User u: modelo.getRegisteredUsers()) {
			if(modelo.getBannedUsers().contains(u)) {
				continue;
			}
			JPanel reg = new JPanel();
            reg.add(new JLabel(u.getNif()));
            reg.add(new JLabel(u.getName()));
            
            JButton ban = new JButton("Banear");
            ban.addActionListener(controlBanUser(u));
            
            reg.add(ban);
            
            viewRegisters.getRegisteredList().add(reg, viewRegisters.getGbc(), 0);
		}
		viewRegisters.getRegisteredList().validate();
		viewRegisters.getRegisteredList().repaint();
	}
	
	/**
	 * Setter for pending projects
	 */
	private void setProjects() {
		viewProjects.prepareList(viewProjects.getProjects());
		// Set the list of registered registers
		for(Project p: modelo.getPendingProjects()) {
			JPanel proj = new JPanel();
			JPanel info = new JPanel();
			info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
			info.add(new JLabel(p.getTitle()));
			info.add(new JLabel(p.getDescription()));
			info.add(new JLabel("Creator:"));
			for(String s: p.getCreator().toString().split("\n")) {
				info.add(new JLabel(s));
			}
			
			proj.add(info);
            
			JButton approve = new JButton("Aprobar");
            JButton deny = new JButton("Rechazar");
            approve.addActionListener((e) ->{modelo.validateProject(p); update();});
            deny.addActionListener((e) -> {modelo.denyProject(p); update();});
            
            proj.add(approve);
            proj.add(deny);
            
            viewProjects.getProjects().add(proj, viewProjects.getGbc(), 0);
		}
		viewProjects.getProjects().validate();
		viewProjects.getProjects().repaint();
	}
	
	/**
	 * Update all lists
	 */
	public void update() {
		setPendingList();
		setRegisteredList();
		setProjects();
	}
	
	/**
	 * Getter of a logout controller
	 * @return ActionListener controller for the login out
	 */
	public ActionListener controlLogout() {
		return (e) -> {
			modelo.logout();
			Inicio nuevaVista = frame.getVistaInicio();
			nuevaVista.update();
			nuevaVista.setVisible(true);
			viewRegisters.setVisible(false);
			viewProjects.setVisible(false);
			frame.pack();
			frame.setLocationRelativeTo(null);
		};
	}
	
	/**
	 * Getter of a go-to-projects controller
	 * @return ActionListener controller for changing view to projects
	 */
	public ActionListener controlGotoProjects() {
		return (e) -> {
			viewRegisters.setVisible(false);
			viewProjects.setVisible(true);
			frame.pack();
		};
	}
	
	/**
	 * Getter of a go-to-registers controller
	 * @return ActionListener controller for changing view to registers
	 */
	public ActionListener controlGotoRegisters() {
		return (e) -> {
			viewProjects.setVisible(false);
			viewRegisters.setVisible(true);
			frame.pack();
		};
	}
	
	/**
	 * Getter of a ban action
	 * @param u User to be banned
	 * @return ActionListener controller for banning
	 */
	public ActionListener controlBanUser(User u) {
		return (e) -> {
			BanDialog bd = new BanDialog(u, frame);
			ControlBan cb = new ControlBan(bd, modelo, this);
			bd.setControlador(cb);
			bd.pack();
			bd.setVisible(true);
		};
	}
	
	/**
	 * Getter of an expiring input
	 * @return ActionListener controller for the expiring time input
	 */
	public ChangeListener controlCaduca() {
		return (e) -> {
			modelo.setMaxInactivity(viewProjects.getCaduca());
			System.out.println(modelo.getMaxInactivity());
			System.out.println(modelo.getMinSupports());
		};
	}
	
	/**
	 * Getter of the minimum supports input
	 * @return ActionListener controller for the minimum supports input
	 */
	public ChangeListener controlApoyos() {
		return (e) -> {
			modelo.setMinSupports(viewProjects.getApoyos());
		};
	}
}
