package vista.inicio;

import javax.swing.*;
import java.awt.*;
import controlador.inicio.*;

public class Ventana extends JFrame {
	
	private Inicio vistaInicio;
	private Registro vistaRegistro;

	private ControlInicio contInicio;
	private ControlCambioRegistro contCambioRegistro;
	private ControlRegistro contRegistro;
	
	private JPanel contentPane;
	
	public Ventana() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout());

		this.vistaInicio = new Inicio();
		contentPane.add(vistaInicio, "incio sesion");

		this.vistaRegistro = new Registro(); 
		contentPane.add(vistaRegistro, "detalleProyecto");
		
	}

	public void setControlador(Controlador controlador) {
		this.contInicio = controlador.getControlInicio();
		vistaInicio.setControladorValidar(contInicio);
		this.contCambioRegistro = controlador.getControlCambioRegistro();
		vistaInicio.setControladorRegistro(contCambioRegistro);
		this.contRegistro = controlador.getControlRegistro();
		vistaRegistro.setControlador(contRegistro);
	}

	public Inicio getVistaInicio() {
		return this.vistaInicio;
	}

	public Registro getVistaRegistro() {
		return this.vistaRegistro;
	}
	
	public void mostrarPanel(String carta) {
		CardLayout l = (CardLayout)contentPane.getLayout();
		l.show(contentPane, carta);
	}

}
