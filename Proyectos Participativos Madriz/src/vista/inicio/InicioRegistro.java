package vista.inicio;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class InicioRegistro {
	public static void main(String[] args) {
		final JFrame ventana = new JFrame("");
		// obtener contenedor, asignar layout
		Container contenedor = ventana.getContentPane();
		contenedor.setLayout(new FlowLayout());

		// crear componentes
		JLabel etiqueta = new JLabel("");
		 // Creamos un panel por cada pestaña
		final InicioSesion inicioSesion = new InicioSesion(ventana);		
		final Registro registro = new Registro(ventana);
		        
		// Crear contenedor JTabbedPane para varios panels
		JTabbedPane pestañas = new JTabbedPane();
		pestañas.addTab("Iniciar sesion",  inicioSesion);
		pestañas.addTab("Registrarse",  registro);

		// asociar acciones a componentes
		// Para acciones al cambiar de pestañas definimos un ChangeListener
		pestañas.addChangeListener(new ChangeListener() {
		       public void stateChanged(ChangeEvent ev) { 
		    	   inicioSesion.limpiaCampos();
		    	   registro.limpiaCampos();
		       }
		});

		// añadir componentes al contenedor
		contenedor.add(etiqueta);
		contenedor.add(pestañas);

		// mostrar ventana
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(360,240);
		ventana.setVisible(true);
	}
}
