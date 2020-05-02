package controlador;

import controlador.inicio.ControlInicio;
import controlador.inicio.ControlRegistro;
import modelo.*;
import modelo.functionalities.Application;
import vista.*;

public class Controlador {
	
	private Ventana frame;
	private Application app;	

	private ControlInicio contInicio;
	private ControlRegistro contRegistro;

	public Controlador(vista.Ventana frame2, Application app) {
		this.frame = frame2;
		this.app = app;
		this.contInicio = new ControlInicio(frame2, app);
		this.contRegistro= new ControlRegistro(frame2, app);
	}

	public ControlInicio getControlInicio() {
		return this.contInicio;
	}
	
	public ControlRegistro getControlRegistro() {
		return this.contRegistro;
	}
}
