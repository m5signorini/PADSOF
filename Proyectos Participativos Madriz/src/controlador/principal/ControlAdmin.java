package controlador.principal;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modelo.entities.individuals.User;
import modelo.functionalities.Application;
import vista.*;
import vista.inicio.*;
import vista.principal.AdminProjectsView;
import vista.principal.AdminRegistersView;
import vista.principal.BanDialog;

public class ControlAdmin {
	private Ventana frame;
	private AdminRegistersView viewRegisters;
	private AdminProjectsView viewProjects;
	private Application modelo;
	
	public ControlAdmin(Ventana mainFrame, Application modelo) {
		this.frame = mainFrame;
		this.viewRegisters = mainFrame.getVistaAdminRegisters();
		this.modelo = modelo;
	}
	
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
	
	public void update() {
		setPendingList();
		setRegisteredList();
	}
	
	public ActionListener controlLogout() {
		return (e) -> {
			modelo.logout();
			Inicio nuevaVista = frame.getVistaInicio();
			nuevaVista.update();
			nuevaVista.setVisible(true);
			viewRegisters.setVisible(false);
			frame.pack();
		};
	}
	
	public ActionListener controlGotoProjects() {
		return (e) -> {
			
		};
	}
	
	public ActionListener controlBanUser(User u) {
		return (e) -> {
			BanDialog bd = new BanDialog(u, frame);
			ControlBan cb = new ControlBan(bd, modelo, this);
			bd.setControlador(cb);
			bd.pack();
			bd.setVisible(true);
		};
	}
}
