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

/**
 * The ControlProjectView class implements the actions that should be performed when 
 * the different buttons in the ProjectView view are clicked.
 * @author Pedro Urbina Rodriguez 
 */

public class ControlProjectView implements ActionListener {
	private Application modelo;
	private Ventana frame;
	private ProjectView projectView;
	
	public ControlProjectView(Application modelo, Ventana frame2) {
		this.modelo = modelo;
		this.frame = frame2;
		this.projectView = frame.getProjectView();
	}

	/**
	 * When the event occurs in the views which have this controller correctly set,
	 * different actions are performed depending on the origin of the action.
	 * @param e ActionEvent which caused the controller to act.
	 * @return None.
	 */
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

	/**
	 * The current logged user tries to vote for the project in the view from which he clicked the button.
	 * @return None.
	 */
	private void intentaVotar() {
		
		User u = modelo.getLoggedUser();
		Project proj = projectView.getProject();
		
		if(proj.support(u) == null) {
			JOptionPane.showMessageDialog(projectView, "No puedes votar a este proyecto.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(projectView, "Has apoyado al proyecto");
		}
	}
	
	/**
	 * Changes the view, making invisible all the other views and making visible the PantallaPrincipal view.
	 * @return None.
	 */	
	private void vuelveAtras() {	
		frame.setAllInvisible();
		frame.getVistaPantallaPrincipal().update();
		frame.getVistaPantallaPrincipal().setVisible(true);
		frame.pack();		
	}

	/**
	 * The current logged user tries to follow the project in the view from which he clicked the button.
	 * @return None.
	 */
	private void seguir() {
		
		User u = modelo.getLoggedUser();
		Project proj = projectView.getProject();
		
		if(u.addFollowedProject(proj)) {
			JOptionPane.showMessageDialog(projectView, "Has seguido el proyecto");
		} else {
			JOptionPane.showMessageDialog(projectView, "No puedes seguir a este proyecto.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * The current logged user tries to unfollow the project in the view from which he clicked the button.
	 * @return None.
	 */
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
