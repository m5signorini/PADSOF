package vista;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;


import controlador.Controlador;
import controlador.inicio.ControlInicio;
import controlador.inicio.ControlRegistro;
import controlador.principal.ControlPantallaPrincipal;
import controlador.proyectos.ControlCreateProject;
import vista.inicio.Inicio;
import vista.inicio.Registro;
import vista.principal.PantallaPrincipal;
import vista.proyectos.CreateProjectView;

public class Ventana extends JFrame {
	
	private Inicio vistaInicio;
	private Registro vistaRegistro;
	private PantallaPrincipal vistaPantallaPrincipal;
	private CreateProjectView vistaCreacionProyecto;

	private ControlInicio contInicio;
	private ControlRegistro contRegistro;
	private ControlPantallaPrincipal contPantallaPrincipal;
	private ControlCreateProject contCreateProject;
	
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

		this.vistaRegistro = new Registro(); 
		contentPane.add(vistaRegistro, "REGISTRO");
		
		this.vistaPantallaPrincipal = new PantallaPrincipal(); 
		contentPane.add(vistaPantallaPrincipal, "PRINCIPAL");
		
		this.vistaCreacionProyecto = new CreateProjectView();
		contentPane.add(vistaCreacionProyecto, "CREACION DE PROYECTO");
		
		
		vistaRegistro.setVisible(false);	
		vistaPantallaPrincipal.setVisible(false);	
		vistaCreacionProyecto.setVisible(false);
		
		this.pack();
	}

	public void setControlador(Controlador controlador) {
		this.contInicio = controlador.getControlInicio();
		vistaInicio.setControladorValidar(contInicio);
		vistaInicio.setControladorCambioRegistro(contInicio);
		
		this.contRegistro = controlador.getControlRegistro();
		vistaRegistro.setControladorRegistro(contRegistro);
		vistaRegistro.setControladorCambioInicio(contRegistro);
		
		this.contPantallaPrincipal = controlador.getControlPantallaPrincipal();
		vistaPantallaPrincipal.setControladores(contPantallaPrincipal);
		
		//this.contCreateProject = controlador.getControlCreateProject();
		//vistaCreacionProyecto.setController(contCreateProject);
	}

	public Inicio getVistaInicio() {
		return this.vistaInicio;
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
}
