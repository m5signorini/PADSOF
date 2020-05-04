package controlador.proyectos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;

import javax.swing.*;
import java.util.*;

import modelo.entities.Collective;
import modelo.entities.individuals.*;
import modelo.exceptions.JoinException;
import modelo.functionalities.Application;
import modelo.projects.*;
import vista.Ventana;
import vista.colectivos.CollectiveView;
import vista.proyectos.CreateProjectView;
import vista.proyectos.ProjectView;

public class ControlProjectView implements ActionListener {
	private Application modelo;
	private Ventana frame;
	private ProjectView projectView;
	
	public ControlProjectView(Application modelo, Ventana frame2) {
		this.modelo = modelo;
		this.frame = frame2;
		this.projectView = frame.getProjectView();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		JButton button = (JButton)arg0.getSource();
		
		switch(button.getActionCommand()) {
		case "Seguir al proyecto para mantenerte al dia":
			seguir();
			break;
		case "Volver":
			vuelveAtras();
			break;
		case "Votar por el proyecto":
			intentaVotar();
			break;
		case "Dejar de seguir el proyecto":
			dejarDeSeguir();
			break;
		default:
			break;
		}
	}
	
	private void intentaVotar() {
		
		User u = modelo.getLoggedUser();
		Project proj = projectView.getProject();
		
		if(proj.support(u) == null) {
			JOptionPane.showMessageDialog(projectView, "No puedes votar a este proyecto.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(projectView, "Has apoyado al proyecto");
		}
	}
	
	private void vuelveAtras() {	
		frame.setAllInvisible();
		frame.getVistaPantallaPrincipal().update();
		frame.getVistaPantallaPrincipal().setVisible(true);
		frame.pack();		
	}
	
	private void seguir() {
		
		User u = modelo.getLoggedUser();
		Project proj = projectView.getProject();
		
		if(u.addFollowedProject(proj)) {
			JOptionPane.showMessageDialog(projectView, "Has seguido el proyecto");
		} else {
			JOptionPane.showMessageDialog(projectView, "No puedes seguir a este proyecto.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void dejarDeSeguir() {
		
		User u = modelo.getLoggedUser();
		Project proj = projectView.getProject();
		
		if(u.removeFollowedProject(proj)) {
			JOptionPane.showMessageDialog(projectView, "Has dejado de seguir el proyecto");
		} else {
			JOptionPane.showMessageDialog(projectView, "No puedes dejar de seguir a este proyecto.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
