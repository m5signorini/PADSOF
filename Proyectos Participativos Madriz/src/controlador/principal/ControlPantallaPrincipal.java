package controlador.principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modelo.entities.Collective;
import modelo.entities.individuals.User;
import modelo.exceptions.SendException;
import modelo.functionalities.Application;
import modelo.projects.Project;
import vista.Ventana;
import vista.principal.PantallaPrincipal;

/**
 * Class that contains the components and functionalities
 * to control the main screen.
 * @author Pedro Rodriguez Urbina
 * @author Cesar Ramirez Martinez
 */

public class ControlPantallaPrincipal implements ActionListener {

	private Ventana frame;
	private PantallaPrincipal pantallaPrincipal;
	private Application modelo;
	
	public ControlPantallaPrincipal(Ventana frame2, Application modelo) {
		this.frame = frame2;
		this.pantallaPrincipal = frame2.getVistaPantallaPrincipal();
		this.modelo = modelo;
	}
	
	/** 
	 * Here we set the action for every button in the main screen. 
	 */
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		switch(button.getActionCommand()) {
		case "Mi Pagina":
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
			frame.getCreateProjectView().setDistricts(modelo.getDistricts());
			frame.getCreateProjectView().update();
			frame.getCreateProjectView().setVisible(true);
			frame.pack();
			//frame.getVistaInicio().setVisible(false);
			//frame.pack();
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
		case "Pulsa aqui para enviar al ayuntamiento":
			// En el nombre hemos almacenado el indice del proyecto del que queremos mas informacion.
			int indiceProyecto5 = Integer.parseInt(((JButton)e.getSource()).getName());
			intentaEnviar((pantallaPrincipal.getCreatedProjects()).get(indiceProyecto5));
			break;
		case "Mas informacion proyecto":
			// En el nombre hemos almacenado el indice del proyecto del que queremos mas informacion.
			int indiceProyecto = Integer.parseInt(((JButton)e.getSource()).getName());
			mostrarInformacionProyecto((pantallaPrincipal.getResultadoBusquedaProyectos()).get(indiceProyecto));
			break;
		case "Mas informacion sobre tu proyecto":
			// En el nombre hemos almacenado el indice del proyecto del que queremos mas informacion.
			int indiceProyecto1 = Integer.parseInt(((JButton)e.getSource()).getName());
			mostrarInformacionProyecto((pantallaPrincipal.getCreatedProjects()).get(indiceProyecto1));
			break;
		case "Mas informacion sobre el proyecto":
			// En el nombre hemos almacenado el indice del proyecto del que queremos mas informacion.
			int indiceProyecto2 = Integer.parseInt(((JButton)e.getSource()).getName());
			mostrarInformacionProyecto((pantallaPrincipal.getFollowedProjects()).get(indiceProyecto2));
			break;
		case "Mas informacion colectivo":
			// En el nombre hemos almacenado el indice del colectivo del que queremos mas informacion.
			int indiceColectivo = Integer.parseInt(((JButton)e.getSource()).getName());
			mostrarInformacionColectivo((pantallaPrincipal.getResultadoBusquedaColectivos()).get(indiceColectivo));
			break;
		case "Mas informacion el colectivo al que perteneces":
			// En el nombre hemos almacenado el indice del colectivo del que queremos mas informacion.
			int indiceColectivo1 = Integer.parseInt(((JButton)e.getSource()).getName());
			mostrarInformacionColectivo((pantallaPrincipal.getCollectives()).get(indiceColectivo1));
			break;
		case "Mas informacion sobre tu colectivo":
			// En el nombre hemos almacenado el indice del colectivo del que queremos mas informacion.
			int indiceColectivo2 = Integer.parseInt(((JButton)e.getSource()).getName());
			mostrarInformacionColectivo((pantallaPrincipal.getRepresentedCollectives()).get(indiceColectivo2));
			break;
		case "Calcular":	
			realizarCalculoDeAfinidad();
			break;
		}
	}
	
	/**
	 * Function that tries to send a project to the town hall
	 * for the final validation
	 */
	private void intentaEnviar(Project p) {
		User u = modelo.getLoggedUser();
		try {
			if(modelo.sendProject(p) == false) {
				JOptionPane.showMessageDialog(pantallaPrincipal, "No puedes enviar a este proyecto aun.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			JOptionPane.showMessageDialog(pantallaPrincipal, "Enviaste este proyecto a financiacion.");
		} catch (SendException e) {
			JOptionPane.showMessageDialog(pantallaPrincipal, "Huvo un problema al enviar este proyecto.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Function that shows the information of a project
	 */
	private void mostrarInformacionProyecto(Project p) {
		User u = modelo.getLoggedUser();
		//System.out.println(p);
		frame.getProjectView().update(p, u.getRepresentedCollectives());
		frame.setAllInvisible();
		frame.getProjectView().setVisible(true);
		frame.pack();
	}
	
	/**
	 * Function that shows the information of a collective
	 */
	private void mostrarInformacionColectivo(Collective c) {
		frame.getCollectiveView().update(c);
		frame.setAllInvisible();
		frame.getCollectiveView().setVisible(true);
		frame.pack();
	}
	
	/**
	 * Function that makes the collective search
	 */
	private void realizarBusquedaColectivos() {
		String text = pantallaPrincipal.getSearchedCollectiveText();
		List<Collective> collectives = modelo.getSearcher().searchCollectives(text);
		pantallaPrincipal.setResultadoBusquedaColectivos(collectives);
		pantallaPrincipal.actualizarResultadosBusquedaColectivo();
		frame.pack();
	}
	
	/**
	 * Function that makes the project search
	 */
	private void realizarBusquedaProyectos() {
		String text = pantallaPrincipal.getSearchedProjectText();
		List<Project> collectives = modelo.getSearcher().searchPublicProjects(text);
		pantallaPrincipal.setResultadoBusquedaProyectos(collectives);
		pantallaPrincipal.actualizarResultadosBusquedaProyecto();
		frame.pack();
	}
	
	/**
	 * Function that makes the affinity operations
	 */
	private void realizarCalculoDeAfinidad() {
		String text = pantallaPrincipal.getCollectiveCalcAffinity();
		//We obtain all the collectives of the app
		List<Collective> collectives = this.modelo.getCollectives();
		//To get the affinity list we only want to compare our collective
		//with the rest of the collectives of the app that we dont belong to
		collectives.removeAll(this.modelo.getLoggedUser().getCollectives());
		List<Integer> members = new ArrayList<Integer>();
		Collective co = null;
		for(Collective c:this.modelo.getCollectives()) {
			if(c.getName() == text) {
				//This will be the collective we will use to obtain the affinity list
				co = c;
			}
		}
		for(Collective c:collectives) {
			members.add(c.getMembers().size());
		}
		
		List<Double> indices = this.modelo.affinityList(co);
		for(Double d: indices) {
			if (d == -1) {
				indices.remove(d);
			}
		}
		
		pantallaPrincipal.representacionInformeAfinidad(collectives, indices, members, text);
		frame.pack();
	}
	
	/**
	 * Function to sign out
	 */
	private void cerrarSesion() {
		modelo.writeToFile("data");			
		frame.setAllInvisible();
		frame.getVistaInicio().update();
		frame.getVistaInicio().setVisible(true);
		frame.pack();
	}
	
	/**
	 * Function that sets all the information needed in the main screen.
	 * It will be called after some actions are executed.
	 */
	public void actualizarMiPagina() {	
		PantallaPrincipal pantallaPrincipal = frame.getVistaPantallaPrincipal();
		User u = modelo.getLoggedUser();
		
		frame.setAllInvisible();
		pantallaPrincipal.setVisible(true);
		frame.pack();
		
		pantallaPrincipal.setCreatedProjects(u.getCreatedProjects());
		pantallaPrincipal.setSentProjects(modelo.getSentProjects());
		pantallaPrincipal.setPendingProjects(modelo.getPendingProjects());
		pantallaPrincipal.setExpiredProjects(modelo.getExpiredProjects());
		pantallaPrincipal.setFinanciatedProjects(modelo.getFinanciatedProjects());
		pantallaPrincipal.setDeniedProjects(modelo.getDeniedProjects());
		pantallaPrincipal.setRejectedProjects(modelo.getRejectedProjects());
		pantallaPrincipal.setVotedProjects(u.getVotedProjects());
		pantallaPrincipal.setFollowedProjects(u.getFollowedProjects());
		pantallaPrincipal.setCollectives(u.getCollectives());
		pantallaPrincipal.setRepresentedCollectives(u.getRepresentedCollectives());
		pantallaPrincipal.setNotifications(u.getNotifications());
		
		pantallaPrincipal.update();
	}	
	
}
