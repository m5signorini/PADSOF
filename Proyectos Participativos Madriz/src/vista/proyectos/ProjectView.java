package vista.proyectos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.proyectos.ControlProjectView;
import modelo.entities.Collective;
import modelo.projects.Project;


/**
 * The ProjectView class is an extension of JPanels which contains all the swing
 * components to create the view which contains information about the project which
 * which contains as an attribute.
 * @author Pedro Urbina Rodriguez 
 */

public class ProjectView extends JPanel{
	
	private JPanel container;
	private JPanel info;
	private JPanel action;
	
	private Project p;
	
	private JLabel title;
	private JLabel desc;
	private JLabel type;
	private JLabel cost;
	private JLabel creationDate;
	private JLabel creator;
	private JLabel nVoters;
	private BufferedImage image;
	private JPanel imagePanel;

	private JButton seguir;
	private JButton dejarDeSeguir;
	private JButton apoyar;
	private JButton volver;
	private JComboBox<String> votarRepresentante;
	private List<Collective> represented;
	
	public ProjectView() {
		this.setLayout(new BorderLayout());
		
		info = new JPanel();
		action = new JPanel();
		info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
		info.setBackground(new Color(190,255,255));
		action.setLayout(new BoxLayout(action, BoxLayout.Y_AXIS));
		action.setBackground(new Color(190,255,255));
		//Dimension d = new Dimension(1000, 1000);
		//container.setMinimumSize(d);
		//container.setMaximumSize(d);
		//container.setPreferredSize(d);

		title = new JLabel("None");
		desc = new JLabel("None");
		type = new JLabel("None");
		cost = new JLabel("None");
		creationDate = new JLabel("None");
		creator = new JLabel("None");
		nVoters = new JLabel("None");

		title.setFont(new Font("serif", Font.BOLD, 50));
		info.add(title);
		
		info.add(Box.createRigidArea(new Dimension(0, 50)));
		
		JPanel auxDesc = new JPanel();
		info.add(auxDesc);
		auxDesc.setLayout(new BoxLayout(auxDesc, BoxLayout.X_AXIS));
		auxDesc.add(new JLabel("Descripcion: "));
		auxDesc.add(desc);
		
		info.add(Box.createRigidArea(new Dimension(0, 30)));
		
		JPanel auxType = new JPanel();
		info.add(auxType);
		auxType.setLayout(new BoxLayout(auxType, BoxLayout.X_AXIS));
		auxType.add(new JLabel("Tipo: "));
		auxType.add(type);
		
		info.add(Box.createRigidArea(new Dimension(0, 30)));
		
		JPanel auxCost = new JPanel();
		info.add(auxCost);
		auxCost.setLayout(new BoxLayout(auxCost, BoxLayout.X_AXIS));
		auxCost.add(new JLabel("Coste: "));
		auxCost.add(cost);
		
		info.add(Box.createRigidArea(new Dimension(0, 30)));
		
		JPanel auxDate = new JPanel();
		info.add(auxDate);
		auxDate.setLayout(new BoxLayout(auxDate, BoxLayout.X_AXIS));
		auxDate.add(new JLabel("Fecha de Creacion: "));
		auxDate.add(creationDate);
		
		info.add(Box.createRigidArea(new Dimension(0, 30)));
		
		JPanel auxCreator = new JPanel();
		info.add(auxCreator);
		auxCreator.setLayout(new BoxLayout(auxCreator, BoxLayout.X_AXIS));
		auxCreator.add(new JLabel("Creador: "));
		auxCreator.add(creator);
		
		info.add(Box.createRigidArea(new Dimension(0, 30)));
		
		JPanel auxVotes = new JPanel();
		info.add(auxVotes);
		auxVotes.setLayout(new BoxLayout(auxVotes, BoxLayout.X_AXIS));
		auxVotes.add(new JLabel("Numero de apoyos: "));
		auxVotes.add(nVoters);
		
		seguir = new JButton("Seguir al proyecto para mantenerte al dia");
		volver = new JButton("Volver");
		apoyar = new JButton("Votar por el proyecto");
		dejarDeSeguir = new JButton("Dejar de seguir el proyecto");
		
		imagePanel = new JPanel();
		
		action.add(imagePanel);
		action.add(Box.createRigidArea(new Dimension(0, 30)));
		action.add(seguir);
		action.add(Box.createRigidArea(new Dimension(0, 30)));
		action.add(volver);
		action.add(Box.createRigidArea(new Dimension(0, 30)));
		action.add(apoyar);
		action.add(Box.createRigidArea(new Dimension(0, 30)));
		action.add(dejarDeSeguir);
		action.add(Box.createRigidArea(new Dimension(0, 30)));
		
		action.add(new JLabel("Votar como representante: "));
		
		String[] opciones = {"Ninguno"};
		votarRepresentante = new JComboBox<String>(opciones);
		votarRepresentante.setBackground(new Color(190,255,255));
		action.add(votarRepresentante);
		
		this.add(info, BorderLayout.WEST);
		this.add(action, BorderLayout.EAST);
	}

	/**
	 * Sets all the ActionListeners of all the buttons in the current object.
	 * @param c ActionListener for the buttons in ProjectView.
	 * @return None.
	 */
	public void setController(ActionListener a) {
		seguir.addActionListener(a);
		volver.addActionListener(a);
		apoyar.addActionListener(a);	
		dejarDeSeguir.addActionListener(a);
	}
	
	/**
	 * Returns the project represented by this view.
	 * @return The project represented in the view.
	 */
	public Project getProject() {
		return this.p;
	}

	/**
	 * Returns the list of collectives that can currently vote the project because 
	 * are represented by the logged user.
	 * @return A List of collectives containing the collectives that can currently vote the represented project.
	 */
	public List<Collective> getRepresentedCollectives(){
		return this.represented;
	}

	/**
	 * Returns the index selected from the JComBox which contains the list of collectives 
	 * that can currently vote the project because are represented by the logged user.
	 * are represented by the logged user.
	 * @return Integer representing the selected Collective.
	 */
	public int getSelected() {
		return votarRepresentante.getSelectedIndex();
	}

	/**
	 * Reloads all the content in the view (usually beacause the Project attribute has changed).
	 * @return None.
	 */
	public void update(Project p, List<Collective> col) {
		this.p = p;
		this.represented = col;

		votarRepresentante.removeAllItems();
		votarRepresentante.addItem("Ninguno");
		for(Collective c: col) {
			votarRepresentante.addItem(c.getName());
		}
		
		title.setText(p.getTitle());
		desc.setText(p.getDescription());
		creator.setText(p.getCreator().toString());
		nVoters.setText(Integer.toString(p.getVoters().size()));
		type.setText(p.typeToString());
		cost.setText(p.getCost() + "");
		creationDate.setText(p.getCreationDate().toString());
		
		try {
			image = ImageIO.read(new File(p.getImagePath()));
			JLabel imglabel = new JLabel(new ImageIcon(image.getScaledInstance(300, 200, Image.SCALE_SMOOTH)));
			imagePanel.removeAll();
			imagePanel.add(imglabel);
		}
		catch(Exception ex) {
			imagePanel.removeAll();
		}
	}	
	
	
}

