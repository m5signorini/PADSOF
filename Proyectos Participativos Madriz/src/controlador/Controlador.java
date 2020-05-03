package controlador;

import controlador.colectivos.ControlCollectiveView;
import controlador.colectivos.ControlCreateCollective;
import controlador.inicio.ControlInicio;
import controlador.inicio.ControlInicioAdmin;
import controlador.inicio.ControlRegistro;
import controlador.principal.ControlPantallaPrincipal;
import controlador.proyectos.ControlCreateProject;
import controlador.proyectos.ControlProjectView;
import modelo.functionalities.Application;
import vista.Ventana;

public class Controlador {
	
	private Ventana frame;
	private Application app;	

	private ControlInicio contInicio;
	private ControlInicioAdmin contInicioAdmin;
	private ControlRegistro contRegistro;
	private ControlPantallaPrincipal contPantallaPrincipal;
	private ControlCreateProject contCreateProject;
	private ControlCreateCollective contCreateCollective;	
	private ControlCollectiveView contCollectiveView;
	private ControlProjectView contProjectView;

	public Controlador(Ventana frame2, Application app) {
		this.frame = frame2;
		this.app = app;
		
		this.contInicio = new ControlInicio(frame, app);
		this.contInicioAdmin = new ControlInicioAdmin(frame, app);
		this.contRegistro= new ControlRegistro(frame, app);
		this.contPantallaPrincipal = new ControlPantallaPrincipal(frame, app);
		this.contCreateProject = new ControlCreateProject(app, frame);
		this.contCreateCollective = new ControlCreateCollective(app, frame);
		this.contCollectiveView = new ControlCollectiveView(app, frame);
		this.contProjectView = new ControlProjectView(app, frame);
	}

	public ControlInicio getControlInicio() {
		return this.contInicio;
	}
	
	public ControlInicioAdmin getControlInicioAdmin() {
		return this.contInicioAdmin;
	}
	
	public ControlRegistro getControlRegistro() {
		return this.contRegistro;
	}
	
	public ControlPantallaPrincipal getControlPantallaPrincipal() {
		return this.contPantallaPrincipal;
	}
	
	public ControlCreateProject getControlCreateProject() {
		return this.contCreateProject;
	}

	public ControlCreateCollective getControlCreateCollective() {
		return this.contCreateCollective;
	}
	
	public ControlProjectView getControlProjectView() {
		return this.contProjectView;
	}

	public ControlCollectiveView getControlCollectiveView() {
		return this.contCollectiveView;
	}
}
