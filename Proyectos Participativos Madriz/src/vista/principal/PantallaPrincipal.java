package vista.principal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PantallaPrincipal extends JPanel {

	
	private JButton botonMiPagina;
	private JButton botonBuscarProyecto;
	private JButton botonBuscarColectivo;
	private JButton botonCrearColectivo;
	private JButton botonCrearProyecto;
	private JButton botonCerrarSesion;
	
	public PantallaPrincipal() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBackground(new Color(204,255,204));
		this.setPreferredSize(new Dimension(2000, 1000));

		JPanel cont = new JPanel();
		cont.setLayout(new BoxLayout(cont, BoxLayout.Y_AXIS));
		cont.setBackground(new Color(255,255,204));
		cont.setPreferredSize(new Dimension(300, 1000));
		
		cont.add(Box.createRigidArea(new Dimension(0, 40)));
		
		JLabel etiqueta = new JLabel("Navegacion:");
		etiqueta.setFont(new Font("serif", Font.BOLD, 25));
		etiqueta.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(etiqueta);

		cont.add(Box.createRigidArea(new Dimension(0, 40)));

		this.botonMiPagina = new JButton("Mi Pagina");
		this.botonMiPagina.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonMiPagina);

		cont.add(Box.createRigidArea(new Dimension(0, 40)));
		
		this.botonBuscarColectivo = new JButton("Buscar Colectivo");
		this.botonBuscarColectivo.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonBuscarColectivo);

		cont.add(Box.createRigidArea(new Dimension(0, 20)));
		
		this.botonBuscarProyecto = new JButton("Buscar Proyecto");
		this.botonBuscarProyecto.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonBuscarProyecto);

		cont.add(Box.createRigidArea(new Dimension(0, 40)));
		
		this.botonCrearColectivo = new JButton("Crear Colectivo");
		this.botonCrearColectivo.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonCrearColectivo);

		cont.add(Box.createRigidArea(new Dimension(0, 20)));
		
		this.botonCrearProyecto = new JButton("Crear Proyecto");
		this.botonCrearProyecto.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonCrearProyecto);

		cont.add(Box.createRigidArea(new Dimension(0, 50)));
		
		this.botonCerrarSesion = new JButton("Cerrar Sesion");
		this.botonCerrarSesion.setAlignmentX(CENTER_ALIGNMENT);
		cont.add(this.botonCerrarSesion);
		
		cont.add(Box.createRigidArea(new Dimension(0, 40)));

		cont.add(Box.createRigidArea(new Dimension(0, 20)));
		
		this.add(cont);
		this.add(Box.createRigidArea(new Dimension(130, 70)));


		JTabbedPane pestanias = new JTabbedPane(2);
		
		JPanel pestaniaMisColectivos = new JPanel();
		JPanel pestaniaMisProyectos = new JPanel();
		JPanel pestaniaInformePoularidad = new JPanel();
		JPanel pestaniaInformeAfinidad = new JPanel();
		JPanel pestaniaProyectosSeguidos = new JPanel();
		JPanel pestaniaNotificaciones = new JPanel();
		pestaniaMisColectivos.setPreferredSize(new Dimension(500, 500));

		pestanias.addTab("Mis Proyectos", pestaniaMisProyectos);
		pestanias.addTab("Mis Colectivos", pestaniaMisColectivos);
		pestanias.addTab("Informe de Popularidad", pestaniaInformePoularidad);
		pestanias.addTab("Informe Afinidad", pestaniaInformeAfinidad);
		pestanias.addTab("Proyectos Seguidos", pestaniaProyectosSeguidos);
		pestanias.addTab("Notificaciones", pestaniaNotificaciones);
		
		// Podemos seleccionar una pestaña del contendor con setSelectedIndex(<indice>)
		pestanias.setSelectedIndex(0);
		// Para realizar acciones al cambiar de pestañas definiremos un ChangeListener
		pestanias.addChangeListener(new ChangeListener() {
			 public void stateChanged(ChangeEvent e) { 
				 
			 }
		});
		this.add(pestanias);
	}

	public void setControladorMiPagina(ActionListener c) {  
		botonMiPagina.addActionListener(c);
	}

	public void setControladorBuscarProyecto(ActionListener c) {  
		botonCrearProyecto.addActionListener(c);
	}

	public void setControladorBuscarColectivo(ActionListener c) {  
		botonBuscarColectivo.addActionListener(c);
	}

	public void setControladorCrearProyecto(ActionListener c) {  
		botonCrearProyecto.addActionListener(c);
	}

	public void setControladorCrearColectivo(ActionListener c) {  
		botonCrearColectivo.addActionListener(c);
	}

	public void setControladorCerrarSesion(ActionListener c) {  
		botonCerrarSesion.addActionListener(c);
	}
	
	public void update () {
	}
}
