package controlador.principal;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import modelo.*;
import modelo.entities.Collective;
import modelo.entities.individuals.User;
import modelo.exceptions.BannedUserException;
import modelo.functionalities.Application;
import modelo.projects.Project;
import vista.*;
import vista.colectivos.CreateCollectiveView;
import vista.inicio.Inicio;
import vista.inicio.Registro;
import vista.principal.PantallaPrincipal;
import vista.proyectos.CreateProjectView;

public class ControlPantallaPrincipal implements ActionListener {

	private Ventana frame;
	private PantallaPrincipal pantallaPrincipal;
	private Application modelo;
	
	public ControlPantallaPrincipal(Ventana frame2, Application modelo) {
		this.frame = frame2;
		this.pantallaPrincipal = frame2.getVistaPantallaPrincipal();
		this.modelo = modelo;
	}

	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		switch(button.getActionCommand()) {
		case "Actualizar Mi Pagina":
			frame.getVistaPantallaPrincipal().getPestanias().setVisible(true);
			frame.getVistaPantallaPrincipal().getSearchProjects().setVisible(false);
			frame.getVistaPantallaPrincipal().getSearchCollectives().setVisible(false);
			actualizarMiPagina();
			break;
		case "Buscar Colectivo":
			frame.getVistaPantallaPrincipal().getPestanias().setVisible(false);
			frame.getVistaPantallaPrincipal().getSearchProjects().setVisible(false);
			frame.getVistaPantallaPrincipal().getSearchCollectives().setVisible(true);
			break;
		case "Buscar Proyecto":
			frame.getVistaPantallaPrincipal().getPestanias().setVisible(false);
			frame.getVistaPantallaPrincipal().getSearchCollectives().setVisible(false);
			frame.getVistaPantallaPrincipal().getSearchProjects().setVisible(true);
			break;
		case "Crear Proyecto":
			frame.getCreateProjectView().setRepresentedCollectives(this.modelo.getCollectives());
			frame.getCreateProjectView().update();
			frame.getCreateProjectView().setVisible(true);
			frame.pack();
			//frame.getVistaInicio().setVisible(false);
			frame.pack();
			break;
		case "Crear Colectivo":
			frame.getCreateCollectiveView().setCollectives(this.modelo.getCollectives());
			frame.getCreateCollectiveView().update();
			frame.getCreateCollectiveView().setVisible(true);
			frame.pack();
			//frame.getVistaInicio().setVisible(false);
			frame.pack();
			break;
		case "Cerrar Sesion":
			cerrarSesion();
			break;
		case "Buscar Colectivos":
			realizarBusquedaColectivos();
			break;
		case "Buscar Proyectos":
			realizarBusquedaProyectos();
			break;
		}
	}


	private void realizarBusquedaColectivos() {
		String text = pantallaPrincipal.getSearchedCollectiveText();
		List<Collective> collectives = modelo.getSearcher().searchCollectives(text);
		pantallaPrincipal.setResultadoBusquedaColectivos(collectives);
		pantallaPrincipal.actualizarResultadosBusquedaColectivo();
		frame.pack();
	}

	private void realizarBusquedaProyectos() {
		String text = pantallaPrincipal.getSearchedProjectText();
		List<Project> collectives = modelo.getSearcher().searchPublicProjects(text);
		pantallaPrincipal.setResultadoBusquedaProyectos(collectives);
		pantallaPrincipal.actualizarResultadosBusquedaProyecto();
		frame.pack();
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