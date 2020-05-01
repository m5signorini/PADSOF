package controlador.inicio;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import modelo.*;
import modelo.functionalities.Application;
import vista.*;
import vista.inicio.Inicio;
import vista.inicio.Registro;
import vista.inicio.Ventana;

public class ControlCambioRegistro implements ActionListener {
	
	private Inicio vista;
	private Ventana frame;
	private Application modelo;
	
	public ControlCambioRegistro(Ventana frame2, Application modelo) {
		this.frame = frame2;
		this.vista = frame2.getVistaInicio();
		this.modelo = modelo;
	}

	public void actionPerformed(ActionEvent e) {
		Registro nuevaVista = frame.getVistaRegistro();
		nuevaVista.update();

		nuevaVista.setVisible(true);
		vista.setVisible(false);
		frame.pack();		
	}
	
}
