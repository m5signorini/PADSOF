package vista.inicio;

import javax.swing.*;
import java.awt.*;
import controlador.inicio.*;

public class Ventana extends JFrame {
	
	private Inicio vistaInicio;
	private Registro vistaRegistro;

	private ControlInicio contInicio;
	private ControlRegistro contRegistro;
	private ControlCambioRegistro contCambioRegistro;
	private ControlCambioInicio contCambioInicio;
	
	private JPanel contentPane;
	
	public Ventana() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);

		this.setLocationRelativeTo(null);

		this.vistaInicio = new Inicio();
		contentPane.add(vistaInicio, "INICIO DE SESION");

		this.vistaRegistro = new Registro(); 
		contentPane.add(vistaRegistro, "REGISTRO");
		
		vistaRegistro.setVisible(false);		

		this.pack();		
	}

	public void setControlador(Controlador controlador) {
		this.contInicio = controlador.getControlInicio();
		vistaInicio.setControladorValidar(contInicio);

		this.contCambioRegistro = controlador.getControlCambioRegistro();
		vistaInicio.setControladorCambioRegistro(contCambioRegistro);
		
		this.contRegistro = controlador.getControlRegistro();
		vistaRegistro.setControladorRegistro(contRegistro);

		this.contCambioInicio = controlador.getControlCambioInicio();
		vistaRegistro.setControladorCambioInicio(contCambioInicio);
	}

	public Inicio getVistaInicio() {
		return this.vistaInicio;
	}

	public Registro getVistaRegistro() {
		return this.vistaRegistro;
	}
}
