package vista.principal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import modelo.entities.Collective;
import modelo.entities.individuals.Notification;
import modelo.projects.Project;

public class PantallaPrincipal extends JPanel {
	

	private List<Project> createdProjects;
	private List<Project> followedProjects;
	private List<Collective> collectives;
	private List<Collective> representedCollectives;
	private ArrayList<Notification> notifications;	

	private List<Collective> resultadoBusquedaColectivos;
	
	private JPanel pestaniaMisColectivos;
	private JPanel pestaniaColectivosCreados;
	private JPanel pestaniaMisProyectos;
	private JPanel pestaniaInformePoularidad;
	private JPanel pestaniaInformeAfinidad;
	private JPanel pestaniaProyectosSeguidos;
	private JPanel pestaniaNotificaciones;
	
	private JButton botonMiPagina;
	private JButton botonBuscarProyecto;
	private JButton botonBuscarColectivo;
	private JButton botonCrearColectivo;
	private JButton botonCrearProyecto;
	private JButton botonCerrarSesion;
	
	private JTabbedPane pestanias;
	
	private JPanel searchCollectives;	
	private JTextField barraBusqueda;
	private JButton botonBuscadorColectivo;
	private JPanel resultadosBusquedaColectivo;
	
	public PantallaPrincipal() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBackground(new Color(204,255,204));
		this.setPreferredSize(new Dimension(2000, 1000));

		JPanel cont = new JPanel();
		cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
		cont.setBackground(new Color(255,255,204));
		cont.setPreferredSize(new Dimension(300, 1000));
		
		cont.add(Box.createRigidArea(new Dimension(0, 40)));
		
		JLabel etiqueta = new JLabel("Navegacion:");
		etiqueta.setFont(new Font("serif", Font.BOLD, 25));
		etiqueta.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(etiqueta);

		cont.add(Box.createRigidArea(new Dimension(0, 40)));

		this.botonMiPagina = new JButton("Actualizar Mi Pagina");
		this.botonMiPagina.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonMiPagina);

		cont.add(Box.createRigidArea(new Dimension(0, 40)));
		
		this.botonBuscarColectivo = new JButton("Buscar Colectivo");
		this.botonBuscarColectivo.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonBuscarColectivo);

		cont.add(Box.createRigidArea(new Dimension(0, 20)));
		
		this.botonBuscarProyecto = new JButton("Buscar Proyecto");
		this.botonBuscarProyecto.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonBuscarProyecto);

		cont.add(Box.createRigidArea(new Dimension(0, 40)));
		
		this.botonCrearColectivo = new JButton("Crear Colectivo");
		this.botonCrearColectivo.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonCrearColectivo);

		cont.add(Box.createRigidArea(new Dimension(0, 20)));
		
		this.botonCrearProyecto = new JButton("Crear Proyecto");
		this.botonCrearProyecto.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonCrearProyecto);

		cont.add(Box.createRigidArea(new Dimension(0, 50)));
		
		this.botonCerrarSesion = new JButton("Cerrar Sesion");
		this.botonCerrarSesion.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonCerrarSesion);
		
		cont.add(Box.createRigidArea(new Dimension(0, 40)));

		cont.add(Box.createRigidArea(new Dimension(0, 20)));
		
		this.add(cont);
		this.add(Box.createRigidArea(new Dimension(130, 70)));


		pestanias = new JTabbedPane(2);

		pestaniaMisColectivos = new JPanel();
		pestaniaColectivosCreados = new JPanel();
		pestaniaMisProyectos = new JPanel();
		pestaniaInformePoularidad = new JPanel();
		pestaniaInformeAfinidad = new JPanel();
		pestaniaProyectosSeguidos = new JPanel();
		pestaniaNotificaciones = new JPanel();
		pestaniaMisColectivos.setPreferredSize(new Dimension(500, 500));

		pestanias.addTab("Mis Proyectos", pestaniaMisProyectos);
		pestanias.addTab("Mis Colectivos", pestaniaMisColectivos);
		pestanias.addTab("Colectivos Creados", pestaniaColectivosCreados);
		pestanias.addTab("Informe de Popularidad", pestaniaInformePoularidad);
		pestanias.addTab("Informe Afinidad", pestaniaInformeAfinidad);
		pestanias.addTab("Proyectos Seguidos", pestaniaProyectosSeguidos);
		pestanias.addTab("Notificaciones", pestaniaNotificaciones);

		pestaniaMisProyectos.setLayout(new BoxLayout(pestaniaMisProyectos, BoxLayout.Y_AXIS));
		pestaniaMisColectivos.setLayout(new BoxLayout(pestaniaMisColectivos, BoxLayout.Y_AXIS));
		pestaniaColectivosCreados.setLayout(new BoxLayout(pestaniaColectivosCreados, BoxLayout.Y_AXIS));
		pestaniaProyectosSeguidos.setLayout(new BoxLayout(pestaniaProyectosSeguidos, BoxLayout.Y_AXIS));
		pestaniaNotificaciones.setLayout(new BoxLayout(pestaniaNotificaciones, BoxLayout.Y_AXIS));
		
		
		// Podemos seleccionar una pestaña del contendor con setSelectedIndex(<indice>)
		pestanias.setSelectedIndex(0);
		// Para realizar acciones al cambiar de pestañas definiremos un ChangeListener
		pestanias.addChangeListener(new ChangeListener() {
			 public void stateChanged(ChangeEvent e) { 
				 
			 }
		});
		this.add(pestanias);
		
		searchCollectives = new JPanel();
		searchCollectives.setLayout(new BoxLayout(searchCollectives, BoxLayout.Y_AXIS));
		searchCollectives.setBackground(new Color(204,204,204));
		//searchCollectives.setPreferredSize(new Dimension(100, 1000));
		
		this.add(searchCollectives);
		
		JPanel cajonBusqueda = new JPanel();
		cajonBusqueda.setLayout(new BoxLayout(cajonBusqueda, BoxLayout.X_AXIS));
		cajonBusqueda.setBackground(new Color(204,204,204));
		// cajonBusqueda.setPreferredSize(new Dimension(100, 1000));
		searchCollectives.add(cajonBusqueda);

		barraBusqueda = new JTextField();
		cajonBusqueda.add(barraBusqueda);		
		//barraBusqueda.setPreferredSize(new Dimension(50, 10));
		
		botonBuscadorColectivo = new JButton("Buscar Colectivos");
		cajonBusqueda.add(botonBuscadorColectivo);
		
		resultadosBusquedaColectivo = new JPanel();
		resultadosBusquedaColectivo.setLayout(new BoxLayout(resultadosBusquedaColectivo, BoxLayout.Y_AXIS));
		resultadosBusquedaColectivo.setBackground(new Color(204,204,204));
		//resultadosBusquedaColectivo.setPreferredSize(new Dimension(100, 100));
		
		
		searchCollectives.setVisible(false);
		
	}
	
	public String getSearchedCollectiveText() {
		return barraBusqueda.getText();
	}

	public void setControladores(ActionListener c) {  
		botonMiPagina.addActionListener(c);
		botonCrearProyecto.addActionListener(c);  
		botonBuscarColectivo.addActionListener(c);  
		botonCrearProyecto.addActionListener(c); 
		botonCrearColectivo.addActionListener(c);  
		botonCerrarSesion.addActionListener(c);
		botonBuscadorColectivo.addActionListener(c);
	}
	
	public void setCreatedProjects(List<Project> createdProjects) {
		this.createdProjects = createdProjects;
	}
	
	public void setFollowedProjects(List<Project> followedProjects) {
		this.followedProjects = followedProjects;
	}
	
	public void setCollectives(List<Collective> collectives) {
		this.collectives = collectives;
	}
	
	public void setResultadoBusquedaColectivos(List<Collective> collectives) {
		this.resultadoBusquedaColectivos = collectives;
	}
	
	public void setRepresentedCollectives(List<Collective> c) {
		this.representedCollectives = c;
	}
	
	public void setNotifications(ArrayList<Notification> notifications) {
		this.notifications = notifications;
	}
	
	public JTabbedPane getPestanias() {
		return this.pestanias;
	}
	
	public JPanel getSearchCollectives() {
		return this.searchCollectives;
	}
	
	public void update () {
		
		pestaniaMisProyectos.removeAll();
		pestaniaProyectosSeguidos.removeAll();
		pestaniaMisColectivos.removeAll();
		pestaniaColectivosCreados.removeAll();
		pestaniaNotificaciones.removeAll();
		
		for (Project p: createdProjects) {
			JPanel c = new JPanel();
			c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
			c.add(new JLabel(p.getTitle()));
			c.add(Box.createRigidArea(new Dimension(130, 070)));
			c.add(new JLabel(p.getDescription()));
			pestaniaMisProyectos.add(c);
		}
		for (Project p: followedProjects) {
			JPanel c = new JPanel();
			c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
			c.add(new JLabel(p.getTitle()));
			c.add(Box.createRigidArea(new Dimension(130, 070)));
			c.add(new JLabel(p.getDescription()));
			pestaniaProyectosSeguidos.add(c);
		}
		for (Collective p: collectives) {
			JPanel c = new JPanel();
			c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
			c.add(new JLabel(p.getName()));
			c.add(Box.createRigidArea(new Dimension(130, 070)));
			c.add(new JLabel(p.getDescription()));
			pestaniaMisColectivos.add(c);
		}
		for (Collective p: representedCollectives) {
			JPanel c = new JPanel();
			c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
			c.add(new JLabel(p.getName()));
			c.add(Box.createRigidArea(new Dimension(130, 070)));
			c.add(new JLabel(p.getDescription()));
			pestaniaColectivosCreados.add(c);
		}
		for (Notification p: notifications) {
			JPanel c = new JPanel();
			c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
			c.add(new JLabel(p.getTitle()));
			c.add(Box.createRigidArea(new Dimension(130, 070)));
			c.add(new JLabel(p.getText()));
			pestaniaNotificaciones.add(c);
		}
	}
	
	public void actualizarResultadosBusquedaColectivo () {		
		resultadosBusquedaColectivo.removeAll();
		for (Collective p: resultadoBusquedaColectivos) {
			JPanel c = new JPanel();
			c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
			c.add(new JLabel(p.getName()));
			c.add(Box.createRigidArea(new Dimension(130, 070)));
			c.add(new JLabel(p.getDescription()));
			resultadoBusquedaColectivos.add(c);
		}
	}
}
