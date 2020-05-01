package controlador.inicio;

import modelo.*;
import modelo.functionalities.Application;
import vista.*;
import vista.inicio.Ventana;

public class Controlador {
	
	private ControlInicio contInicio;
	private ControlCambioRegistro contCambioRegistro;
	private ControlRegistro contRegistro;
	private Ventana frame;
	private Application app;

	public Controlador(vista.inicio.Ventana frame2, Application app) {
		this.frame = frame2;
		this.app = app;
		this.contInicio = new ControlInicio(frame2, app);
		this.contCambioRegistro= new ControlCambioRegistro(frame2, app);
		this.contRegistro= new ControlRegistro(frame2, app);
	}

	public ControlInicio getControlInicio() {
		return this.contInicio;
	}
	
	public ControlCambioRegistro getControlCambioRegistro() {
		return this.contCambioRegistro;
	}
	
	public ControlRegistro getControlRegistro() {
		return this.contRegistro;
	}
}
