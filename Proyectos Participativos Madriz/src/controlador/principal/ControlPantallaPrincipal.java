package controlador.principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import modelo.entities.Collective;
import modelo.entities.individuals.User;
import modelo.functionalities.Application;
import modelo.projects.Project;
import vista.Ventana;
import vista.principal.PantallaPrincipal;

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
			frame.getControlCreateProject().setCollectivos(this.modelo.getCollectives());
			frame.getCreateProjectView().update();
			frame.getCreateProjectView().setVisible(true);
			frame.pack();
			//frame.getVistaInicio().setVisible(false);
			frame.pack();
			break;
		case "Crear Colectivo":
			frame.getControlCreateCollective().setCollectivos(this.modelo.getCollectives());
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
		case "Mas informacion proyecto":
			// En el nombre hemos almacenado el indice del proyecto del que queremos mas informacion.
			int indiceProyecto = Integer.parseInt(((JButton)e.getSource()).getName());
			mostrarInformacionProyecto((pantallaPrincipal.getResultadoBusquedaProyectos()).get(indiceProyecto));
			break;
		case "Mas informacion colectivo":
			// En el nombre hemos almacenado el indice del colectivo del que queremos mas informacion.
			int indiceColectivo = Integer.parseInt(((JButton)e.getSource()).getName());
			mostrarInformacionColectivo((pantallaPrincipal.getResultadoBusquedaColectivos()).get(indiceColectivo));
			break;
		}
	}
	
	private void mostrarInformacionProyecto(Project p) {
		frame.getProjectView().update(p);
		frame.setAllInvisible();
		frame.getProjectView().setVisible(true);
		frame.pack();
	}
	
	private void mostrarInformacionColectivo(Collective c) {
		frame.getCollectiveView().update(c);
		frame.setAllInvisible();
		frame.getCollectiveView().setVisible(true);
		frame.pack();
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
		pantallaPrincipal.setVotedProjects(u.getVotedProjects());
		pantallaPrincipal.setFollowedProjects(u.getFollowedProjects());
		pantallaPrincipal.setCollectives(u.getCollectives());
		pantallaPrincipal.setRepresentedCollectives(u.getRepresentedCollectives());
		pantallaPrincipal.setNotifications(u.getNotifications());
		
		pantallaPrincipal.update();
	}	
	
}
