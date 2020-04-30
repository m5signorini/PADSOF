package vista.inicio;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InicioSesion extends JPanel implements ActionListener {
	private JLabel etiquetaNif;
	private JLabel etiquetaPwd;
	private JTextField campoNif;
	private JTextField campoPwd;
	private JButton boton;
	private JFrame ventana;
	
	public InicioSesion(JFrame ventana) {
		this.ventana = ventana;	
		this.etiquetaNif = new JLabel("Nif");
		this.campoNif = new JTextField(5);
		this.etiquetaPwd = new JLabel("Pwd");
		this.campoPwd = new JTextField(5);
		this.boton = new JButton("Iniciar Sesion");
		
		// asociar acciones a componentes
		boton.addActionListener( this );
		
		// asignar Layout Manager y añadir componentes 
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(etiquetaNif);
		this.add(campoNif);
		this.add(etiquetaPwd);
		this.add(campoPwd);
		JPanel botonPanel = new JPanel(); 
		botonPanel.add(boton);
		this.add(botonPanel);	
	}


// OJO esto viola el patron MVC. Si la comprobaciñn de paridad es parte
// de la funcionalidad del MODELO de la aplicaciñn, NO DEBE HACERSE en 
// la VISTA sino que el CONTROLADOR asociado con ella es quien debe
// invocar al MODELO para realizar dicha funcionalidad.
	@Override
	public void actionPerformed(ActionEvent ev) {
	  String mensaje = "";
	  try {
		  mensaje = "Nif: "+ this.campoNif.getText() + " Password: " + this.campoPwd.getText();
	  } catch (NumberFormatException e) {
	  } finally {
		  JOptionPane.showMessageDialog(ventana, mensaje);
	  }
	}
	
	public void limpiaCampos() { 
		this.campoNif.setText(""); 
		this.campoPwd.setText(""); 
	}

}
