package controlador;

import controlador.inicio.ControlCambioInicio;
import controlador.inicio.ControlCambioRegistro;
import controlador.inicio.ControlInicio;
import controlador.inicio.ControlRegistro;
import modelo.*;
import modelo.functionalities.Application;
import vista.*;

public class Controlador {
	
	private Ventana frame;
	private Application app;	

	private ControlInicio contInicio;
	private ControlCambioRegistro contCambioRegistro;
	private ControlCambioInicio contCambioInicio;
	private ControlRegistro contRegistro;

	public Controlador(vista.Ventana frame2, Application app) {
		this.frame = frame2;
		this.app = app;
		this.contInicio = new ControlInicio(frame2, app);
		this.contCambioRegistro= new ControlCambioRegistro(frame2, app);
		this.contCambioInicio= new ControlCambioInicio(frame2, app);
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

	public ControlCambioInicio getControlCambioInicio() {
		return this.contCambioInicio;
	}
}
