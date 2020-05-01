package controlador.inicio;

import java.awt.*;
import java.awt.event.*;
import modelo.*;
import modelo.functionalities.Application;
import vista.*;
import vista.inicio.Registro;
import vista.inicio.Ventana;

public class ControlRegistro implements ActionListener {
	
	private Ventana frame;
	private Registro vista;
	private Application modelo;
	
	public ControlRegistro(Ventana frame2, Application modelo) {
		this.frame = frame2;
		this.vista = this.frame.getVistaRegistro(); 
		this.modelo = modelo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mostrarPanelRegistro();
	}
	
	private void mostrarPanelRegistro() {
		this.frame.getVistaRegistro().setVisible(true);
		this.vista.setVisible(false);		
		this.frame.getVistaRegistro().update();
	}
	
}
