package controlador.inicio;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import modelo.*;
import modelo.functionalities.Application;
import vista.*;
import vista.inicio.Inicio;
import vista.inicio.Registro;

public class ControlCambioInicio implements ActionListener {
	
	private Registro vista;
	private Ventana frame;
	private Application modelo;
	
	public ControlCambioInicio(Ventana frame2, Application modelo) {
		this.frame = frame2;
		this.vista = frame2.getVistaRegistro();
		this.modelo = modelo;
	}


	public void actionPerformed(ActionEvent e) {
	
		// mostrar nueva vista
		Inicio nuevaVista = frame.getVistaInicio();
		nuevaVista.update();

		nuevaVista.setVisible(true);
		vista.setVisible(false);
		frame.pack();		
		
		// this.frame.mostrarPanel("detalleProyecto"); 
			// alternativa usando CardLayout a las dos lineas anteriores
		
	}
	
}
