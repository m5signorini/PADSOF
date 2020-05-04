package vista;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;


import controlador.Controlador;
import controlador.colectivos.ControlCollectiveView;
import controlador.colectivos.ControlCreateCollective;
import controlador.inicio.*;
import controlador.principal.ControlAdmin;
import controlador.principal.ControlPantallaPrincipal;
import controlador.proyectos.ControlCreateProject;
import controlador.proyectos.ControlProjectView;
import vista.colectivos.CollectiveView;
import vista.colectivos.CreateCollectiveView;
import vista.inicio.*;
import vista.principal.*;
import vista.proyectos.CreateProjectView;
import vista.proyectos.ProjectView;


/**
 * The Ventana class contains all the graphic objects developed in the different view's classes.
 * It handle all the views and controllers, setting the adequate controllers to each view.
 * @author Pedro Urbina Rodriguez 
 * @author Cesar Ramirez
 * @author Martin Sanchez
 */

public class Ventana extends JFrame {
	
	private Inicio vistaInicio;
	private InicioAdmin vistaInicioAdmin;
	private AdminRegistersView vistaAdminRegisters;
	private AdminProjectsView vistaAdminProjects;
	private Registro vistaRegistro;
	private PantallaPrincipal vistaPantallaPrincipal;
	private CreateProjectView vistaCreacionProyecto;
	private CreateCollectiveView vistaCreacionColectivo;
	private ProjectView vistaProjectView;
	private CollectiveView vistaCollectiveView;

	private ControlInicio contInicio;
	private ControlInicioAdmin contInicioAdmin;
	private ControlAdmin contAdmin;
	private ControlRegistro contRegistro;
	private ControlPantallaPrincipal contPantallaPrincipal;
	private ControlCreateProject contCreateProject;
	private ControlCreateCollective contCreateCollective;
	private ControlCollectiveView contCollectiveView;
	private ControlProjectView contProjectView;
	
	private JPanel contentPane;
	
	public Ventana() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		setContentPane(contentPane);

		// Para que se cree la ventana en el centro de la pantalla.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int a = (dim.width/2-this.getSize().width/2)/2;
		int b = (dim.height/2-this.getSize().height/2)/2;
		this.setLocation(a, b);
		
		this.vistaInicio = new Inicio();
		contentPane.add(vistaInicio, "INICIO DE SESION");
		
		this.vistaInicioAdmin = new InicioAdmin();
		contentPane.add(vistaInicioAdmin, "INICIO DE ADMIN");
		
		this.vistaAdminRegisters = new AdminRegistersView();
		contentPane.add(vistaAdminRegisters, "ADMINISTRAR REGISTROS");
		
		this.vistaAdminProjects = new AdminProjectsView();
		contentPane.add(vistaAdminProjects, "ADMINISTRAR PROYECTOS");
		
		this.vistaRegistro = new Registro(); 
		contentPane.add(vistaRegistro, "REGISTRO");
		
		this.vistaPantallaPrincipal = new PantallaPrincipal(); 
		contentPane.add(vistaPantallaPrincipal, "PRINCIPAL");
		
		this.vistaCreacionProyecto = new CreateProjectView();
		contentPane.add(vistaCreacionProyecto, "CREACION DE PROYECTO");
		
		this.vistaCreacionColectivo = new CreateCollectiveView();
		contentPane.add(vistaCreacionColectivo, "CREACION DE COLECTIVO");

		this.vistaProjectView = new ProjectView();
		contentPane.add(vistaProjectView, "VISTA DE PROYECTO");
		
		this.vistaCollectiveView = new CollectiveView();
		contentPane.add(vistaCollectiveView, "VISTA DE COLECTIVO");

		this.setAllInvisible();
		vistaInicio.setVisible(true);
		
		this.pack();
	}


	/**
	 * Sets all the controllers of all the different views in the application.
	 * @param controlador Controlador which contains all the controllers for the different views.
	 * @return None.
	 */
	public void setControlador(Controlador controlador) {
		this.contInicio = controlador.getControlInicio();
		vistaInicio.setControladores(contInicio);
		
		this.contInicioAdmin = controlador.getControlInicioAdmin();
		vistaInicioAdmin.setControladorLoginAdmin(contInicioAdmin);
		vistaInicioAdmin.setControladorVolver(contInicioAdmin);
		
		this.contAdmin = controlador.getControlAdmin();
		vistaAdminRegisters.setControladorLogout(contAdmin.controlLogout());
		vistaAdminRegisters.setControladorGotoProjects(contAdmin.controlGotoProjects());
		vistaAdminProjects.setControladorLogout(contAdmin.controlLogout());
		vistaAdminProjects.setControladorGotoRegisters(contAdmin.controlGotoRegisters());
		vistaAdminProjects.setControladorCaduca(contAdmin.controlCaduca());
		vistaAdminProjects.setControladorApoyos(contAdmin.controlApoyos());
		
		this.contRegistro = controlador.getControlRegistro();
		vistaRegistro.setControladores(contRegistro);
		
		this.contPantallaPrincipal = controlador.getControlPantallaPrincipal();
		vistaPantallaPrincipal.setControladores(contPantallaPrincipal);
		
		this.contCreateProject = controlador.getControlCreateProject();
		vistaCreacionProyecto.setController(contCreateProject);
		
		this.contCreateCollective = controlador.getControlCreateCollective();
		vistaCreacionColectivo.setController(contCreateCollective);
		
		this.contProjectView = controlador.getControlProjectView();
		vistaProjectView.setController(contProjectView);
		
		this.contCollectiveView = controlador.getControlCollectiveView();
		vistaCollectiveView.setController(contCollectiveView);
	}

	public Inicio getVistaInicio() {
		return this.vistaInicio;
	}
	
	public InicioAdmin getVistaInicioAdmin() {
		return this.vistaInicioAdmin;
	}
	
	public AdminRegistersView getVistaAdminRegisters() {
		return this.vistaAdminRegisters;
	}
	
	public AdminProjectsView getVistaAdminProjects() {
		return this.vistaAdminProjects;
	}

	public Registro getVistaRegistro() {
		return this.vistaRegistro;
	}
	
	public PantallaPrincipal getVistaPantallaPrincipal() {
		return this.vistaPantallaPrincipal;
	}
	
	public CreateProjectView getCreateProjectView() {
		return this.vistaCreacionProyecto;
	}
	
	public CreateCollectiveView getCreateCollectiveView() {
		return this.vistaCreacionColectivo;
	}
	
	public ProjectView getProjectView() {
		return this.vistaProjectView;
	}
	
	public CollectiveView getCollectiveView() {
		return this.vistaCollectiveView;
	}
	
	
	//
	
	public ControlCreateCollective getControlCreateCollective() {
		return this.contCreateCollective;
	}
	
	public ControlPantallaPrincipal getControlPantallaPrincipal() {
		return this.contPantallaPrincipal;
	}
	
	public ControlCreateProject getControlCreateProject() {
		return this.contCreateProject;
	}
	
	public ControlAdmin getControlAdmin() {
		return this.contAdmin;
	}
	

	/**
	 * All the different views contained in the current object are made invisible.
	 * @return None.
	 */
	public void setAllInvisible() {
		vistaInicio.setVisible(false);
		vistaInicioAdmin.setVisible(false);
		vistaAdminRegisters.setVisible(false);
		vistaAdminProjects.setVisible(false);
		vistaRegistro.setVisible(false);
		vistaPantallaPrincipal.setVisible(false);
		vistaCreacionProyecto.setVisible(false);
		vistaCreacionColectivo.setVisible(false);
		vistaProjectView.setVisible(false);
		vistaCollectiveView.setVisible(false);
		vistaInicioAdmin.setVisible(false);
	}
}
