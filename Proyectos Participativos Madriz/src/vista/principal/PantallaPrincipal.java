package vista.principal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	
	private ActionListener listener;
	
	private List<Project> createdProjects;
	private List<Project> votedProjects;
	private List<Project> followedProjects;
	private List<Collective> collectives;
	private List<Collective> representedCollectives;
	private ArrayList<Notification> notifications;	

	private List<Collective> resultadoBusquedaColectivos;
	private List<Project> resultadoBusquedaProyectos;
	
	private JPanel pestaniaMisColectivos;
	private JPanel pestaniaColectivosCreados;
	private JPanel pestaniaMisProyectos;
	private JPanel pestaniaInformePopularidad;
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
	
	private JPanel leftMenu;
	
	private JPanel searchCollectives;	
	private JTextField barraBusquedaColectivo;
	private JButton botonBuscadorColectivo;
	private JPanel resultadosBusquedaColectivo;
	
	private JPanel searchProjects;	
	private JTextField barraBusquedaProyecto;
	private JButton botonBuscadorProyecto;
	private JPanel resultadosBusquedaProyecto;
	
	private JComboBox<String> afinidadColectivo;
	
	public PantallaPrincipal() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBackground(new Color(204,255,204));
		this.setPreferredSize(new Dimension(2300, 1200));

		this.add(Box.createRigidArea(new Dimension(80, 70)));
		
		leftMenu = createLeftMenu();		
		this.add(leftMenu);
		
		this.add(Box.createRigidArea(new Dimension(80, 70)));

		pestanias = createMiPagina();
		this.add(pestanias);
		
		searchCollectives = createZonaBusquedaColectivos();		
		this.add(searchCollectives);
		
		searchProjects = createZonaBusquedaProyectos();		
		this.add(searchProjects);	

		searchCollectives.setVisible(false);
		searchProjects.setVisible(false);
		
	}
	
	private JPanel createLeftMenu() {
		JPanel cont = new JPanel();
		cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
		cont.setBackground(new Color(255,255,204));
		Dimension d = new Dimension(300, 700);
        cont.setMinimumSize(d);
        cont.setMaximumSize(d);
        cont.setPreferredSize(d);
		
		cont.add(Box.createRigidArea(new Dimension(0, 50)));
		
		JLabel etiqueta = new JLabel("Navegacion:");
		etiqueta.setFont(new Font("serif", Font.BOLD, 25));
		etiqueta.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(etiqueta);

		cont.add(Box.createRigidArea(new Dimension(0, 55)));

		this.botonMiPagina = new JButton("Actualizar Mi Pagina");
		this.botonMiPagina.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonMiPagina);

		cont.add(Box.createRigidArea(new Dimension(0, 70)));
		
		this.botonBuscarColectivo = new JButton("Buscar Colectivo");
		this.botonBuscarColectivo.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonBuscarColectivo);

		cont.add(Box.createRigidArea(new Dimension(0, 40)));
		
		this.botonBuscarProyecto = new JButton("Buscar Proyecto");
		this.botonBuscarProyecto.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonBuscarProyecto);

		cont.add(Box.createRigidArea(new Dimension(0, 70)));
		
		this.botonCrearColectivo = new JButton("Crear Colectivo");
		this.botonCrearColectivo.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonCrearColectivo);

		cont.add(Box.createRigidArea(new Dimension(0, 40)));
		
		this.botonCrearProyecto = new JButton("Crear Proyecto");
		this.botonCrearProyecto.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonCrearProyecto);

		cont.add(Box.createRigidArea(new Dimension(0, 70)));
		
		this.botonCerrarSesion = new JButton("Cerrar Sesion");
		this.botonCerrarSesion.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonCerrarSesion);
		
		cont.add(Box.createRigidArea(new Dimension(0, 40)));
		return cont;
	}
	
	private JTabbedPane createMiPagina() {
		JTabbedPane pestanias1 = new JTabbedPane(1);
		pestanias1.setFont(new Font("serif", Font.BOLD, 20));
		pestanias1.setBackground(new Color(190,255,255));
		Dimension d = new Dimension(1700, 1000);
		pestanias1.setMinimumSize(d);
		pestanias1.setMaximumSize(d);
		pestanias1.setPreferredSize(d);

		pestaniaMisColectivos = new JPanel();
		pestaniaColectivosCreados = new JPanel();
		pestaniaMisProyectos = new JPanel();
		pestaniaInformePopularidad = new JPanel();
		pestaniaInformeAfinidad = new JPanel();
		pestaniaProyectosSeguidos = new JPanel();
		pestaniaNotificaciones = new JPanel();
		//pestaniaMisColectivos.setPreferredSize(new Dimension(500, 500));

		pestaniaMisColectivos.setBackground(new Color(255,255,255));
		pestaniaColectivosCreados.setBackground(new Color(255,255,255));
		pestaniaMisProyectos.setBackground(new Color(255,255,255));
		pestaniaInformePopularidad.setBackground(new Color(255,255,255));
		pestaniaInformeAfinidad.setBackground(new Color(255,255,255));
		pestaniaProyectosSeguidos.setBackground(new Color(255,255,255));
		pestaniaNotificaciones.setBackground(new Color(255,255,255));
		
		pestanias1.addTab("Mis Proyectos", pestaniaMisProyectos);
		pestanias1.addTab("Mis Colectivos", pestaniaMisColectivos);
		pestanias1.addTab("Colectivos Creados", pestaniaColectivosCreados);
		pestanias1.addTab("Informe de Popularidad", pestaniaInformePopularidad);
		pestanias1.addTab("Informe Afinidad", pestaniaInformeAfinidad);
		pestanias1.addTab("Proyectos Seguidos", pestaniaProyectosSeguidos);
		pestanias1.addTab("Notificaciones", pestaniaNotificaciones);

		pestaniaMisProyectos.setLayout(new BoxLayout(pestaniaMisProyectos, BoxLayout.Y_AXIS));
		pestaniaMisColectivos.setLayout(new BoxLayout(pestaniaMisColectivos, BoxLayout.Y_AXIS));
		pestaniaColectivosCreados.setLayout(new BoxLayout(pestaniaColectivosCreados, BoxLayout.Y_AXIS));
		pestaniaProyectosSeguidos.setLayout(new BoxLayout(pestaniaProyectosSeguidos, BoxLayout.Y_AXIS));
		pestaniaNotificaciones.setLayout(new BoxLayout(pestaniaNotificaciones, BoxLayout.Y_AXIS));
		pestaniaInformePopularidad.setLayout(new BoxLayout(pestaniaInformePopularidad, BoxLayout.Y_AXIS));
		pestaniaInformeAfinidad.setLayout(new BoxLayout(pestaniaInformeAfinidad, BoxLayout.Y_AXIS));


		return pestanias1;
	}
	
	private JPanel createZonaBusquedaColectivos() {
		JPanel searchCollectives1 = new JPanel();
		searchCollectives1.setLayout(new BoxLayout(searchCollectives1, BoxLayout.Y_AXIS));
		searchCollectives1.setBackground(new Color(204,204,204));
		//searchCollectives.setPreferredSize(new Dimension(100, 1000));
		
		JPanel cajonBusqueda = new JPanel();
		cajonBusqueda.setLayout(new BoxLayout(cajonBusqueda, BoxLayout.X_AXIS));
		cajonBusqueda.setBackground(new Color(204,204,204));
		// cajonBusqueda.setPreferredSize(new Dimension(100, 1000));
		searchCollectives1.add(cajonBusqueda);

		barraBusquedaColectivo = new JTextField();
		cajonBusqueda.add(barraBusquedaColectivo);		
		barraBusquedaColectivo.setMaximumSize(new Dimension(300, 50));
		
		botonBuscadorColectivo = new JButton("Buscar Colectivos");
		cajonBusqueda.add(botonBuscadorColectivo);
		
		resultadosBusquedaColectivo = new JPanel();
		resultadosBusquedaColectivo.setLayout(new BoxLayout(resultadosBusquedaColectivo, BoxLayout.Y_AXIS));
		resultadosBusquedaColectivo.setBackground(new Color(204,204,204));
		//resultadosBusquedaColectivo.setPreferredSize(new Dimension(100, 100));
		searchCollectives1.add(resultadosBusquedaColectivo);
		
		return searchCollectives1;
	}
	
	private JPanel createZonaBusquedaProyectos() {
		JPanel searchProyectos1 = new JPanel();
		searchProyectos1.setLayout(new BoxLayout(searchProyectos1, BoxLayout.Y_AXIS));
		searchProyectos1.setBackground(new Color(204,204,204));
		//searchCollectives.setPreferredSize(new Dimension(100, 1000));
		
		JPanel cajonBusqueda = new JPanel();
		cajonBusqueda.setLayout(new BoxLayout(cajonBusqueda, BoxLayout.X_AXIS));
		cajonBusqueda.setBackground(new Color(204,204,204));
		// cajonBusqueda.setPreferredSize(new Dimension(100, 1000));
		searchProyectos1.add(cajonBusqueda);

		barraBusquedaProyecto = new JTextField();
		cajonBusqueda.add(barraBusquedaProyecto);		
		barraBusquedaProyecto.setMaximumSize(new Dimension(300, 50));
		
		botonBuscadorProyecto = new JButton("Buscar Proyectos");
		cajonBusqueda.add(botonBuscadorProyecto);
		
		resultadosBusquedaProyecto = new JPanel();
		resultadosBusquedaProyecto.setLayout(new BoxLayout(resultadosBusquedaProyecto, BoxLayout.Y_AXIS));
		resultadosBusquedaProyecto.setBackground(new Color(204,204,204));
		searchProyectos1.add(resultadosBusquedaProyecto);
		
		return searchProyectos1;
	}
	
	
	public String getSearchedCollectiveText() {
		return barraBusquedaColectivo.getText();
	}
	
	public String getSearchedProjectText() {
		return barraBusquedaProyecto.getText();
	}

	public void setControladores(ActionListener c) {  
		botonMiPagina.addActionListener(c);
		botonCrearProyecto.addActionListener(c);  
		botonBuscarColectivo.addActionListener(c);  
		botonBuscarProyecto.addActionListener(c); 
		botonCrearColectivo.addActionListener(c);  
		botonCerrarSesion.addActionListener(c);
		botonBuscadorColectivo.addActionListener(c);
		botonBuscadorProyecto.addActionListener(c);
		listener = c;
	}
	
	public void setCreatedProjects(List<Project> createdProjects) {
		this.createdProjects = createdProjects;
	}
	
	public void setVotedProjects(List<Project> votedProjects) {
		this.votedProjects = votedProjects;
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
	
	public void setResultadoBusquedaProyectos(List<Project> collectives) {
		this.resultadoBusquedaProyectos = collectives;
	}
	
	public List<Project> getResultadoBusquedaProyectos() {
		return this.resultadoBusquedaProyectos;
	}
	
	public List<Collective> getResultadoBusquedaColectivos() {
		return this.resultadoBusquedaColectivos;
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
	
	public JPanel getSearchProjects() {
		return this.searchProjects;
	}
	
	
	private JPanel representacionProyecto(Project p, int index) {
		JPanel c = new JPanel();
		c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
		Dimension d = new Dimension(1700, 100);
        c.setMinimumSize(d);
        c.setMaximumSize(d);
        c.setPreferredSize(d);

		c.add(Box.createRigidArea(new Dimension(200, 0)));
		JLabel title = new JLabel(p.getTitle());
		title.setFont(new Font("serif", Font.BOLD, 25));
		c.add(title);
		
		int votes = p.getVoters().size();
		c.add(Box.createRigidArea(new Dimension(200, 0)));
		c.add(new JLabel(p.getDescription()));
		
		c.add(Box.createRigidArea(new Dimension(200, 0)));
		
		JButton b = new JButton("Mas informacion");
		b.addActionListener(listener);
		b.setName(Integer.toString(index));
		c.add(b);
		
		return c;
	}
	
	private JPanel representacionColectivo(Collective p) {
		JPanel c = new JPanel();
		c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
		c.add(new JLabel(p.getName()));
		c.add(Box.createRigidArea(new Dimension(130, 070)));
		c.add(new JLabel(p.getDescription()));
		return c;
	}
	
	private JPanel representacionNotificacion(Notification p) {
		JPanel c = new JPanel();
		c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
		c.add(new JLabel(p.getTitle()));
		c.add(Box.createRigidArea(new Dimension(130, 070)));
		c.add(new JLabel(p.getText()));
		return c;
	}
	
	private JPanel primeraFilaInformePopularidad() {
		JPanel c = new JPanel();
		c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
		c.add(new JLabel("Título"));
		c.add(Box.createRigidArea(new Dimension(130, 070)));
		c.add(new JLabel("Votos"));
		c.add(Box.createRigidArea(new Dimension(130, 070)));
		c.add(new JLabel("Tipo"));
		c.add(Box.createRigidArea(new Dimension(130, 070)));
		c.add(new JLabel("Presupuesto"));
		return c;
	}
	
	private JPanel representacionProyectoEnInformePopularidad(Project p) {
		JPanel c = new JPanel();
		c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
		c.add(new JLabel(p.getTitle()));
		c.add(Box.createRigidArea(new Dimension(130, 070)));
		c.add(new JLabel(String.valueOf(p.countVotes())));
		c.add(Box.createRigidArea(new Dimension(130, 070)));
		c.add(new JLabel(String.valueOf(p.getProjectKind())));
		c.add(Box.createRigidArea(new Dimension(130, 070)));
		c.add(new JLabel(String.valueOf(p.getRequestedAmount())));
		return c;
	}
	
	private JPanel primeraFilaInformeAfinidad() {
		JPanel c = new JPanel();
		JPanel aux = new JPanel(); 
		c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
		aux.setLayout(new BoxLayout(aux, BoxLayout.X_AXIS));

		aux.add(new JLabel("Encontrar colectivos afines a:"));
		aux.add(Box.createRigidArea(new Dimension(130, 070)));
		aux.add(afinidadColectivo);
		c.add(aux);
		c.add(Box.createRigidArea(new Dimension(130, 070)));
		c.add(new JLabel("Colectivos"));
		c.add(Box.createRigidArea(new Dimension(130, 070)));
		c.add(new JLabel("Índice de afinidad"));
		c.add(Box.createRigidArea(new Dimension(130, 070)));
		c.add(new JLabel("Tipo"));
		c.add(Box.createRigidArea(new Dimension(130, 070)));
		c.add(new JLabel("Cantidad de miembros"));
		
		return c;
	}
	
	/*private JPanel representacionInformeAfinidad(Collective co) {
		JPanel c = new JPanel();
		c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
		c.add(new JLabel(p.getTitle()));
		c.add(Box.createRigidArea(new Dimension(130, 070)));
		c.add(new JLabel(String.valueOf(p.countVotes())));
		c.add(Box.createRigidArea(new Dimension(130, 070)));
		c.add(new JLabel(String.valueOf(p.getProjectKind())));
		c.add(Box.createRigidArea(new Dimension(130, 070)));
		c.add(new JLabel(String.valueOf(p.getRequestedAmount())));
		return c;
	}*/
	
	public void update () {
		
		pestaniaMisProyectos.removeAll();
		pestaniaProyectosSeguidos.removeAll();
		pestaniaMisColectivos.removeAll();
		pestaniaColectivosCreados.removeAll();
		pestaniaNotificaciones.removeAll();
		
		int i = 0;
		for (Project p: createdProjects) {
			pestaniaMisProyectos.add(representacionProyecto(p, i));
			i++;
		}
		i = 0;
		for (Project p: followedProjects) {
			pestaniaProyectosSeguidos.add(representacionProyecto(p, i));
			i++;
		}
		for (Collective p: collectives) {
			pestaniaMisColectivos.add(representacionColectivo(p));
		}
		for (Collective p: representedCollectives) {
			pestaniaColectivosCreados.add(representacionColectivo(p));
		}
		for (Notification p: notifications) {
			pestaniaNotificaciones.add(representacionNotificacion(p));
		}
		
		pestaniaInformePopularidad.add(primeraFilaInformePopularidad());
		createdProjects.sort(Comparator.comparing(Project::countVotes));
		for(Project p:votedProjects) {
			pestaniaInformePopularidad.add(representacionProyectoEnInformePopularidad(p));
		}
		String[] opciones = {"Ninguno"};
		afinidadColectivo = new JComboBox<String>(opciones);
		afinidadColectivo.setSize(new Dimension(20, 20));
		afinidadColectivo.setSelectedIndex(0);
		
		pestaniaInformeAfinidad.add(primeraFilaInformeAfinidad());
		if(	afinidadColectivo.getSelectedIndex() == 0) {
			return;
		}

		for(Collective c:collectives) {
			//pestaniaInformeAfinidad.add(representacionInformeAfinidad(c));
		}
	}
	
	public void actualizarResultadosBusquedaColectivo () {		
		resultadosBusquedaColectivo.removeAll();
		if(resultadoBusquedaColectivos.isEmpty()) {
			resultadosBusquedaColectivo.add(new JLabel("No reults found!"));
			return;
		}
		for (Collective p: resultadoBusquedaColectivos) {
			JPanel c = new JPanel();
			c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
			c.add(new JLabel(p.getName()));
			c.add(Box.createRigidArea(new Dimension(130, 070)));
			c.add(new JLabel(p.getDescription()));
			resultadosBusquedaColectivo.add(c);
		}
	}
	
	public void actualizarResultadosBusquedaProyecto () {		
		resultadosBusquedaProyecto.removeAll();
		if(resultadoBusquedaProyectos.isEmpty()) {
			resultadosBusquedaProyecto.add(new JLabel("No reults found!"));
			return;
		}
		for (Project p: resultadoBusquedaProyectos) {
			JPanel c = new JPanel();
			c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
			c.add(new JLabel(p.getTitle()));
			c.add(Box.createRigidArea(new Dimension(130, 070)));
			c.add(new JLabel(p.getDescription()));
			resultadosBusquedaProyecto.add(c);
		}
	}
}
