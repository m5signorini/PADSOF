package controlador;

import controlador.inicio.ControlInicio;
import controlador.inicio.ControlRegistro;
import controlador.principal.ControlPantallaPrincipal;
import modelo.*;
import modelo.functionalities.Application;
import vista.*;

public class Controlador {
	
	private Ventana frame;
	private Application app;	

	private ControlInicio contInicio;
	private ControlRegistro contRegistro;
	private ControlPantallaPrincipal contPantallaPrincipal;

	public Controlador(vista.Ventana frame2, Application app) {
		this.frame = frame2;
		this.app = app;
		
		this.contInicio = new ControlInicio(frame, app);
		this.contRegistro= new ControlRegistro(frame, app);
		this.contPantallaPrincipal = new ControlPantallaPrincipal(frame, app);
	}

	public ControlInicio getControlInicio() {
		return this.contInicio;
	}
	
	public ControlRegistro getControlRegistro() {
		return this.contRegistro;
	}
	
	public ControlPantallaPrincipal getControlPantallaPrincipal() {
		return this.contPantallaPincipal;
	}
}
