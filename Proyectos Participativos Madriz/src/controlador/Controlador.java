package controlador;

import controlador.colectivos.ControlCreateCollective;
import controlador.inicio.*;
import controlador.principal.ControlAdmin;
import controlador.principal.ControlPantallaPrincipal;
import controlador.proyectos.ControlCreateProject;
import modelo.*;
import modelo.functionalities.Application;
import vista.*;

public class Controlador {
	
	private Ventana frame;
	private Application app;	

	private ControlInicio contInicio;
	private ControlInicioAdmin contInicioAdmin;
	private ControlAdmin contAdmin;
	private ControlRegistro contRegistro;
	private ControlPantallaPrincipal contPantallaPrincipal;
	private ControlCreateProject contCreateProject;
	private ControlCreateCollective contCreateCollective;

	public Controlador(Ventana frame2, Application app) {
		this.frame = frame2;
		this.app = app;
		
		this.contInicio = new ControlInicio(frame, app);
		this.contInicioAdmin = new ControlInicioAdmin(frame, app);
		this.contAdmin = new ControlAdmin(frame, app);
		this.contRegistro= new ControlRegistro(frame, app);
		this.contPantallaPrincipal = new ControlPantallaPrincipal(frame, app);
		this.contCreateProject = new ControlCreateProject(app, frame);
		this.contCreateCollective = new ControlCreateCollective(app, frame);
	}

	public ControlInicio getControlInicio() {
		return this.contInicio;
	}
	
	public ControlInicioAdmin getControlInicioAdmin() {
		return this.contInicioAdmin;
	}
	
	public ControlAdmin getControlAdmin() {
		return this.contAdmin;
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
}
