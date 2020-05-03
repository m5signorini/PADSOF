package controlador.principal;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import modelo.*;
import modelo.entities.individuals.User;
import modelo.exceptions.BannedUserException;
import modelo.functionalities.Application;
import vista.*;
import vista.colectivos.CreateCollectiveView;
import vista.inicio.Inicio;
import vista.inicio.Registro;
import vista.principal.PantallaPrincipal;
import vista.proyectos.CreateProjectView;

public class ControlPantallaPrincipal implements ActionListener {

	private Ventana frame;
	private Application modelo;
	
	public ControlPantallaPrincipal(Ventana frame2, Application modelo) {
		this.frame = frame2;
		this.modelo = modelo;
	}

	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		switch(button.getActionCommand()) {
		case "Actualizar Mi Pagina":
			actualizarMiPagina();
			break;
		case "Buscar Colectivo":
			frame.getVistaPantallaPrincipal().getPestanias().setVisible(false);
			break;
		case "Buscar Proyecto":
			frame.getVistaPantallaPrincipal().getPestanias().setVisible(false);
			break;
		case "Crear Proyecto":
			frame.getCreateProjectView().setVisible(true);
			frame.pack();
			//frame.getVistaInicio().setVisible(false);
			frame.pack();
			break;
		case "Crear Colectivo":
			frame.getCreateCollectiveView().setVisible(true);
			frame.pack();
			//frame.getVistaInicio().setVisible(false);
			frame.pack();
			break;
		case "Cerrar Sesion":
			cerrarSesion();
			break;
		}
	}
	

	private void cerrarSesion() {
		modelo.writeToFile("data");	
	}
	
	private void actualizarMiPagina() {	
		PantallaPrincipal pantallaPrincipal = frame.getVistaPantallaPrincipal();
		User u = modelo.getLoggedUser();
		
		frame.setAllInvisible();
		pantallaPrincipal.setVisible(true);
		frame.pack();

		pantallaPrincipal.setCreatedProjects(u.getCreatedProjects());
		pantallaPrincipal.setFollowedProjects(u.getFollowedProjects());
		pantallaPrincipal.setCollectives(u.getCollectives());
		pantallaPrincipal.setRepresentedCollectives(u.getRepresentedCollectives());
		pantallaPrincipal.setNotifications(u.getNotifications());
		
		pantallaPrincipal.update();
	}	
	
}