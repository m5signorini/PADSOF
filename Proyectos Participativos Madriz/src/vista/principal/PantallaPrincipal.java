package vista.principal;

import java.awt.*;
import java.awt.event.*;
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
import modelo.functionalities.Application;
import modelo.projects.Project;

/**
 * Class that contains the components and functionalities
 * to create a view for the main screen.
 * From this view the user will be able
 * to go through all the tabs of the application.
 * @author Pedro Rodriguez Urbina
 * @author Cesar Ramirez Martinez
 */

public class PantallaPrincipal extends JPanel {
	
	private ActionListener listener;
	
	private List<Project> sentProjects;
	private List<Project> financiatedProjects;
	private List<Project> expiredProjects;
	private List<Project> pendingProjects;
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
	private JButton calcularAfinidad;
	
	private JPanel currentView;
	
	/**
	 * Constructor of PantallaPrincipal:   Initializes all the attributes and puts them in the right 
	 * places in the container of the window. It is mainly formed by seven tabs and six buttons
	 * that will lead the user try all the functionalities of the application. 
	 */
	public PantallaPrincipal() {
		
		//this.setLayout(new BorderLayout());
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBackground(new Color(204,255,204));
		//this.setPreferredSize(new Dimension(1200, 700));
		
		leftMenu = createLeftMenu();
		pestanias = createMiPagina();
		searchCollectives = createZonaBusquedaColectivos();
		searchProjects = createZonaBusquedaProyectos();
		
		//this.add(leftMenu, BorderLayout.WEST);
		//this.add(pestanias, BorderLayout.CENTER);
		this.add(leftMenu);
		this.add(pestanias);
		this.add(searchCollectives);
		this.add(searchProjects);

		searchCollectives.setVisible(false);
		searchProjects.setVisible(false);
	}
	
	/**
	 * Constructor of the left side menu of the main screen.
	 * This menu will contain the six button previously mentioned.
	 * @return the panel with the left side menu created
	 */
	private JPanel createLeftMenu() {
		JPanel cont = new JPanel();
		cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
		cont.setBackground(new Color(255,255,204));
		//Dimension d = new Dimension(300, 700);
        //cont.setMinimumSize(d);
        //cont.setMaximumSize(d);
        //cont.setPreferredSize(d);
		
		//cont.add(Box.createRigidArea(new Dimension(0, 50)));
		
		JLabel etiqueta = new JLabel("Navegacion:");
		etiqueta.setFont(new Font("serif", Font.BOLD, 25));
		etiqueta.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(etiqueta);

		//cont.add(Box.createRigidArea(new Dimension(0, 55)));

		this.botonMiPagina = new JButton("Mi Pagina");
		this.botonMiPagina.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonMiPagina);

		//cont.add(Box.createRigidArea(new Dimension(0, 70)));
		
		this.botonBuscarColectivo = new JButton("Buscar Colectivo");
		this.botonBuscarColectivo.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonBuscarColectivo);

		//cont.add(Box.createRigidArea(new Dimension(0, 40)));
		
		this.botonBuscarProyecto = new JButton("Buscar Proyecto");
		this.botonBuscarProyecto.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonBuscarProyecto);

		//cont.add(Box.createRigidArea(new Dimension(0, 70)));
		
		this.botonCrearColectivo = new JButton("Crear Colectivo");
		this.botonCrearColectivo.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonCrearColectivo);

		//cont.add(Box.createRigidArea(new Dimension(0, 40)));
		
		this.botonCrearProyecto = new JButton("Crear Proyecto");
		this.botonCrearProyecto.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonCrearProyecto);

		//cont.add(Box.createRigidArea(new Dimension(0, 70)));
		
		this.botonCerrarSesion = new JButton("Cerrar Sesion");
		this.botonCerrarSesion.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonCerrarSesion);
		
		this.calcularAfinidad = new JButton("Calcular");
		String[] opciones = {"Ninguno"};
		afinidadColectivo = new JComboBox<String>(opciones);
		afinidadColectivo.setSize(new Dimension(20, 20));
		afinidadColectivo.setSelectedIndex(0);
		
		//cont.add(Box.createRigidArea(new Dimension(0, 40)));
		return cont;
	}
	
	/**
	 * Constructor of the seven tabs previously mentioned.
	 * Each tab will be a section of the user. In each the user will
	 * be able to take advice of all the information about his 
	 * projects and collectives among other.
	 * @return the JTabbedPanel that contains the different tabs
	 */
	private JTabbedPane createMiPagina() {
		JTabbedPane pestanias1 = new JTabbedPane(JTabbedPane.TOP);
		pestanias1.setFont(new Font("serif", Font.BOLD, 20));
		pestanias1.setBackground(new Color(190,255,255));
		//Dimension d = new Dimension(1700, 1000);
		//pestanias1.setMinimumSize(d);
		//pestanias1.setMaximumSize(d);
		//pestanias1.setPreferredSize(d);

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
	
	/**
	 * Constructor of the zone reserved to search the collectives
	 *  previously created.
	 * @return the JPanel that will lead the user search the collectives
	 */
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
		barraBusquedaColectivo.setColumns(30);
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
	
	/**
	 * Constructor of the zone reserved to search the projects
	 * previously created.
	 * @return the JPanel that will lead the user search the projects
	 */
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
		barraBusquedaProyecto.setColumns(30);
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
	
	public String getCollectiveCalcAffinity() {
		return afinidadColectivo.getItemAt(afinidadColectivo.getSelectedIndex());
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
		calcularAfinidad.addActionListener(c);
		listener = c;
	}
	
	public void setCreatedProjects(List<Project> createdProjects) {
		this.createdProjects = createdProjects;
	}
	
	public List<Project> getCreatedProjects() {
		return this.createdProjects;
	}
	
	public void setVotedProjects(List<Project> votedProjects) {
		this.votedProjects = votedProjects;
	}
	
	public void setPendingProjects(List<Project> p) {
		this.pendingProjects = p;
	}
	public List<Project> getPendingProjects(List<Project> p) {
		return pendingProjects;
	}
	
	public void setFinanciatedProjects(List<Project> p) {
		this.financiatedProjects = p;
	}
	public List<Project> getFinanciatedProjects(List<Project> p) {
		return financiatedProjects;
	}
	
	public void setExpiredProjects(List<Project> p) {
		this.expiredProjects = p;
	}
	public List<Project> getExpiredProjects(List<Project> p) {
		return expiredProjects;
	}
	
	public void setSentProjects(List<Project> p) {
		this.sentProjects = p;
	}
	public List<Project> getSentProjects(List<Project> p) {
		return sentProjects;
	}
	
	public void setFollowedProjects(List<Project> followedProjects) {
		this.followedProjects = followedProjects;
	}
	
	public List<Project> getFollowedProjects() {
		return this.followedProjects;
	}
	
	public void setCollectives(List<Collective> collectives) {
		this.collectives = collectives;
	}
	
	public List<Collective> getCollectives() {
		return this.collectives;
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
	
	public List<Collective> getRepresentedCollectives() {
		return this.representedCollectives;
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
	
	/**
	 * Each project obtained in the search, will appear 
	 * this way on the screen.
	 * @return the panel with a project result of the search
	 */
	private JPanel representacionProyecto(Project p) {
		JPanel c = new JPanel();
		c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
		//Dimension d = new Dimension(1700, 100);
        //c.setMinimumSize(d);
        //c.setMaximumSize(d);
        //c.setPreferredSize(d);

		c.add(Box.createRigidArea(new Dimension(200, 0)));
		JLabel title = new JLabel(p.getTitle());
		title.setFont(new Font("serif", Font.BOLD, 25));
		c.add(title);
		
		int votes = p.getVoters().size();
		c.add(Box.createRigidArea(new Dimension(200, 0)));
		c.add(new JLabel(p.getDescription()));
		
		c.add(Box.createRigidArea(new Dimension(200, 0)));
		
		return c;
	}
	
	/**
	 * Each collective obtained in the search, will appear 
	 * this way on the screen.
	 * @return the panel with a collective result of the search
	 */
	private JPanel representacionColectivo(Collective p) {
		JPanel c = new JPanel();
		c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
		//Dimension d = new Dimension(1700, 100);
        //c.setMinimumSize(d);
        //c.setMaximumSize(d);
        //c.setPreferredSize(d);

		c.add(Box.createRigidArea(new Dimension(200, 0)));
		JLabel title = new JLabel(p.getName());
		title.setFont(new Font("serif", Font.BOLD, 25));
		c.add(title);

		c.add(Box.createRigidArea(new Dimension(200, 0)));
		c.add(new JLabel(p.getDescription()));
		
		c.add(Box.createRigidArea(new Dimension(200, 0)));
		
		return c;
	}
	
	private JPanel representacionNotificacion(Notification p) {
		JPanel c = new JPanel();
		c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
		c.add(new JLabel(p.getTitle()));
		c.add(Box.createRigidArea(new Dimension(130, 70)));
		c.add(new JLabel(p.getText()));
		return c;
	}
	
	/**
	 * The first row of the "Informe de popularidad" tab
	 * will be represented this way.
	 * @return the panel with the first row on this tab
	 */
	private JPanel primeraFilaInformePopularidad() {
		JPanel c = new JPanel();
		c.setLayout(new GridLayout(1,3,0,10));
		
		c.add(Box.createRigidArea(new Dimension(200, 0)));
		//Dimension d = new Dimension(1700, 100);
		//Dimension d1 = new Dimension(200,120);
        //c.setMinimumSize(d);
        //c.setMaximumSize(d);
        //c.setPreferredSize(d);
        
		JLabel title =new JLabel("Titulo");
		//title.setMaximumSize(d1);
		//title.setMaximumSize(d1);
		//title.setPreferredSize(d1);
		title.setFont(new Font("serif", Font.ITALIC, 22));
		c.add(title);
		
		
		JLabel votes =new JLabel("Votos");
		//votes.setMaximumSize(d1);
		//votes.setMaximumSize(d1);
		//votes.setPreferredSize(d1);
		votes.setFont(new Font("serif", Font.ITALIC, 22));
		c.add(votes);
		
		
		JLabel type =new JLabel("Tipo");
		//type.setMaximumSize(d1);
		//type.setMaximumSize(d1);
		//type.setPreferredSize(d1);
		type.setFont(new Font("serif", Font.ITALIC, 22));
		c.add(type);
		
		
		JLabel budget =new JLabel("Presupuesto");
		budget.setFont(new Font("serif", Font.ITALIC, 22));
		c.add(budget);

		return c;
	}
	
	/**
	 * Each project in the "Informe de popularidad" tab
	 * will be represented this way.
	 * @return the panel with a project representation
	 */
	private JPanel representacionProyectoEnInformePopularidad(Project p) {
		JPanel c = new JPanel();
		c.setLayout(new GridLayout(1,3,0,10));
		
		c.add(Box.createRigidArea(new Dimension(200, 0)));

		//Dimension d = new Dimension(1700, 100);
		//Dimension d1 = new Dimension(200,120);
        //c.setMinimumSize(d);
        //c.setMaximumSize(d);
        //c.setPreferredSize(d);
        

		JLabel title = new JLabel(p.getTitle());
		//title.setMaximumSize(d1);
		//title.setMaximumSize(d1);
		//title.setPreferredSize(d1);
		title.setFont(new Font("serif", Font.PLAIN, 20));
		c.add(title);

		JLabel votes =new JLabel(String.valueOf(p.countVotes()));
		//votes.setMaximumSize(d1);
		//votes.setMaximumSize(d1);
		//votes.setPreferredSize(d1);
		votes.setFont(new Font("serif", Font.PLAIN, 20));
		c.add(votes);
		
		JLabel type =new JLabel(String.valueOf(p.getProjectKind()));
		//type.setMaximumSize(d1);
		//type.setMaximumSize(d1);
		//type.setPreferredSize(d1);
		type.setFont(new Font("serif", Font.PLAIN, 20));
		c.add(type);
		
		JLabel budget =new JLabel(String.valueOf(p.getRequestedAmount()));
		//budget.setMaximumSize(d1);
		//budget.setMaximumSize(d1);
		//budget.setPreferredSize(d1);
		budget.setFont(new Font("serif", Font.PLAIN, 20));
		c.add(budget);

		return c;
	}
	
	/**
	 * The first row of the "Informe de afinidad" tab
	 * will be represented this way.
	 * @return the panel with the first row on this tab
	 */
	private JPanel primeraFilaInformeAfinidad() {
		JPanel c = new JPanel();
		c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
		
		//Dimension d = new Dimension(1700, 100);
        //c.setMinimumSize(d);
        //c.setMaximumSize(d);
        //c.setPreferredSize(d);
        
		c.add(Box.createRigidArea(new Dimension(200, 0)));

		JLabel search = new JLabel("Encontrar colectivos afines a:");
		search.setFont(new Font("serif", Font.PLAIN, 22));
		c.add(search);
		c.add(Box.createRigidArea(new Dimension(200, 0)));
		
		c.add(afinidadColectivo);
		c.add(Box.createRigidArea(new Dimension(200, 0)));
		c.add(calcularAfinidad);
		c.add(Box.createRigidArea(new Dimension(200, 0)));

		return c;
	}
	
	/**
	 * This private function will receive three lists with all the needed
	 * info to represent all the collectives and its info in the tab.
	 * @return the panel with all the collectives and the info required by the user
	 */
	public void representacionInformeAfinidad(List<Collective> list1, List<Double> list2, List<Integer> list3, String text) {
		if(	list1 == null || list2 == null || list3 == null) {
			return;
		}
		
		pestaniaInformeAfinidad.removeAll();
		
		pestaniaInformeAfinidad.add(primeraFilaInformeAfinidad());
		afinidadColectivo.removeAllItems();
		afinidadColectivo.addItem("Ninguno");
		if(collectives == null) {
			return;
		}
		for(Collective c: collectives) {
			afinidadColectivo.addItem(c.getName());
			if(c.getName() == text) {
				afinidadColectivo.setSelectedItem(c.getName());
			}
		}
		
		JPanel c = new JPanel();
		//c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
		c.setLayout(new GridLayout(1,3,0,10));

		//Dimension d = new Dimension(1700, 100);
		//Dimension d1 = new Dimension(200,120);
        //c.setMinimumSize(d);
        //c.setMaximumSize(d);
        //c.setPreferredSize(d);
        
		//c.add(Box.createRigidArea(new Dimension(200, 0)));
		
		c.add(Box.createRigidArea(new Dimension(200, 0)));

		JLabel title =new JLabel("Nombre:");
		title.setFont(new Font("serif", Font.ITALIC, 22));
		//title.setMaximumSize(d1);
		//title.setMaximumSize(d1);
		//title.setPreferredSize(d1);
		c.add(title);
		
		//c.add(Box.createRigidArea(new Dimension(200, 0)));
		
		JLabel indice =new JLabel("Afinidad:");
		indice.setFont(new Font("serif", Font.ITALIC, 22));
		//indice.setMaximumSize(d1);
		//indice.setMaximumSize(d1);
		//indice.setPreferredSize(d1);
		c.add(indice);
		
		//c.add(Box.createRigidArea(new Dimension(200, 0)));
		
		JLabel members =new JLabel("Miembros:");
		members.setFont(new Font("serif", Font.ITALIC, 22));
		//members.setMaximumSize(d1);
		//members.setMaximumSize(d1);
		//members.setPreferredSize(d1);
		c.add(members);
		//c.add(Box.createRigidArea(new Dimension(200, 0)));

		pestaniaInformeAfinidad.add(c);

		int i;
		
		for( i = 0; i < list1.size(); i++) {
			
	        JPanel p = new JPanel();
			//p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
			p.setLayout(new GridLayout(1,3,0,10));
			
			p.add(Box.createRigidArea(new Dimension(200, 0)));

	        //p.setMinimumSize(d);
	        //p.setMaximumSize(d);
	        //p.setPreferredSize(d);
	        
			//p.add(Box.createRigidArea(new Dimension(200, 0)));

			JLabel name = new JLabel(list1.get(i).getName());
			//name.setMaximumSize(d1);
			//name.setMaximumSize(d1);
			//name.setPreferredSize(d1);
			name.setFont(new Font("serif", Font.PLAIN, 20));
			p.add(name);
			//p.add(Box.createRigidArea(new Dimension(200, 0)));
			
			JLabel index =new JLabel(String.valueOf(list2.get(i)));
			//index.setMaximumSize(d1);
			//index.setMaximumSize(d1);
			//index.setPreferredSize(d1);
			index.setFont(new Font("serif", Font.PLAIN, 20));
			p.add(index);
			//p.add(Box.createRigidArea(new Dimension(200, 0)));
			
			JLabel memberQuantity =new JLabel(String.valueOf(list3.get(i)));
			//memberQuantity.setMaximumSize(d1);
			//memberQuantity.setMaximumSize(d1);
			//memberQuantity.setPreferredSize(d1);
			memberQuantity.setFont(new Font("serif", Font.PLAIN, 20));
			p.add(memberQuantity);
			
			//p.add(Box.createRigidArea(new Dimension(200, 0)));

			pestaniaInformeAfinidad.add(p);

		}
	}
	
	/**
	 * This function will add or delete the needed components
	 * of the main screen after certain action happen.
	 */
	public void update () {
		
		pestaniaInformePopularidad.removeAll();
		pestaniaInformeAfinidad.removeAll();
		pestaniaMisProyectos.removeAll();
		pestaniaProyectosSeguidos.removeAll();
		pestaniaMisColectivos.removeAll();
		pestaniaColectivosCreados.removeAll();
		pestaniaNotificaciones.removeAll();
		
		int i = 0;
		for (Project p: createdProjects) {
			JPanel cont = representacionProyecto(p);
			JButton b = new JButton("Mas informacion sobre tu proyecto");
			b.addActionListener(listener);
			b.setName(Integer.toString(i));
			cont.add(b);
			JButton bSend;
			if(sentProjects.contains(p)) {
				bSend = new JButton("Proyecto ya enviado");
			}
			else if(pendingProjects.contains(p)) {
				bSend = new JButton("Pendiente de validacion, no se puede enviar");
			}
			else if(financiatedProjects.contains(p)) {
				bSend = new JButton("Proyecto Financiado");
			}
			else if(expiredProjects.contains(p)) {
				bSend = new JButton("Proyecto Expirado");
			}
			else {
				bSend = new JButton("Pulsa aqui para enviar al ayuntamiento");
			}
			bSend.addActionListener(listener);
			bSend.setName(Integer.toString(i));
			cont.add(bSend);
						
			pestaniaMisProyectos.add(cont);
			i++;
		}
		i = 0;
		for (Project p: followedProjects) {
			JPanel cont = representacionProyecto(p);
			JButton b = new JButton("Mas informacion sobre el proyecto");
			b.addActionListener(listener);
			b.setName(Integer.toString(i));
			cont.add(b);
			
			pestaniaProyectosSeguidos.add(cont);
			i++;
		}
		i = 0;
		for (Collective p: collectives) {
			JPanel cont = representacionColectivo(p);
			JButton b = new JButton("Mas informacion el colectivo al que perteneces");
			b.addActionListener(listener);
			b.setName(Integer.toString(i));
			cont.add(b);
			
			pestaniaMisColectivos.add(cont);
			i++;
		}
		i = 0;
		for (Collective p: representedCollectives) {
			JPanel cont1 = representacionColectivo(p);
			JButton b = new JButton("Mas informacion sobre tu colectivo");
			b.addActionListener(listener);
			b.setName(Integer.toString(i));
			cont1.add(b);
			
			pestaniaColectivosCreados.add(cont1);
			i++;
		}
		if (notifications.isEmpty()) {
			pestaniaNotificaciones.add(new JLabel("No hay nuevas notificaciones!"));
		} else {
			for (Notification p: notifications) {
				pestaniaNotificaciones.add(representacionNotificacion(p));
			}
		}
		
		pestaniaInformePopularidad.add(primeraFilaInformePopularidad());
		
		createdProjects.sort(Comparator.comparing(Project::countVotes));
		for(Project p:votedProjects) {
			pestaniaInformePopularidad.add(representacionProyectoEnInformePopularidad(p));
		}
		
		pestaniaInformeAfinidad.add(primeraFilaInformeAfinidad());
		afinidadColectivo.removeAllItems();
		afinidadColectivo.addItem("Ninguno");
		if(collectives == null) {
			return;
		}
		for(Collective c: collectives) {
			afinidadColectivo.addItem(c.getName());
		}
	}
	
	/**
	 * This function will add all the collectives obtained in the search
	 * in the corresponding tab or none if there is not any search result.
	 */
	public void actualizarResultadosBusquedaColectivo () {		
		resultadosBusquedaColectivo.removeAll();
		if(resultadoBusquedaColectivos.isEmpty()) {
			resultadosBusquedaColectivo.add(new JLabel("Sin resultados"));
			return;
		}
		int i = 0;
		for (Collective p: resultadoBusquedaColectivos) {
			JPanel cont = representacionColectivo(p);
			JButton b = new JButton("Mas informacion colectivo");
			b.addActionListener(listener);
			b.setName(Integer.toString(i));
			cont.add(b);
			
			resultadosBusquedaColectivo.add(cont);
			i++;
		}
	}
	
	/**
	 * This function will add all the projects obtained in the search
	 * in the corresponding tab or none if there is not any search result.
	 */
	public void actualizarResultadosBusquedaProyecto () {		
		resultadosBusquedaProyecto.removeAll();
		if(resultadoBusquedaProyectos.isEmpty()) {
			resultadosBusquedaProyecto.add(new JLabel("Sin resultados"));
			return;
		}
		int i = 0;
		for (Project p: resultadoBusquedaProyectos) {
			JPanel cont = representacionProyecto(p);
			JButton b = new JButton("Mas informacion proyecto");
			b.addActionListener(listener);
			b.setName(Integer.toString(i));
			cont.add(b);
			
			resultadosBusquedaProyecto.add(cont);
			i++;
		}
	}
}
