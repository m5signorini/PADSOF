package vista.colectivos;

import java.awt.*;

import java.awt.event.ActionListener;

import javax.swing.*;

import modelo.entities.Collective;
import vista.proyectos.SpringUtilities;

import java.util.List;

/**
 * Class that contains the components and functionalities
 * to create a view for the creation of a collective
 * @author Cesar Ramirez Martinez
 */

public class CreateCollectiveView extends JPanel{

		List<Collective> collectives;

		JFrame window;
		//Main panel
		JPanel MainPanel;
		
		//Create button
		JButton create;
		//Cancel button
		JButton cancel;

		
		//Collective name
		JLabel etiquetaCollectiveName;
		JTextField collectiveName;
			
		//Description
		JLabel etiquetaDescCollective;
		JTextArea descCollective;

		//Father collective
		JLabel etiquetaFather;
		JComboBox<String> father;
		
		/**
		 * Constructor of CreateCollectiveView:   Initializes all the attributes and puts them in the right 
		 * places in the container of the window. It is mainly formed by one tabs, a cancel button 
		 * and a cancel button. The tab gives the user all the options to create a new collective.
		 */
		public CreateCollectiveView() {
			window = new JFrame("Nuevo colectivo");
			
			Container container = window.getContentPane();
			SpringLayout layout = new SpringLayout();
			MainPanel = new JPanel(layout);
			
			container.setLayout(layout);
			 
			//Create button
			create = new JButton("Crear");
			container.add(create);
			
			layout.putConstraint(SpringLayout.WEST, create, 250, SpringLayout.WEST, container);
			layout.putConstraint(SpringLayout.NORTH, create,200, SpringLayout.NORTH, container);
			
			//Cancel button
			cancel = new JButton("Cancelar");
			container.add(cancel);
			
			layout.putConstraint(SpringLayout.WEST, cancel, 3, SpringLayout.WEST, container);
			layout.putConstraint(SpringLayout.NORTH, cancel, 200, SpringLayout.NORTH, container);
			
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			int a = (dim.width/2-this.getSize().width/2)/2;
			int b = (dim.height/2-this.getSize().height/2)/2;
			this.window.setLocation(a, b);
			
			//Collective name
			etiquetaCollectiveName = new JLabel("Nombre del colectivo:");
			collectiveName = new JTextField(10);
			etiquetaCollectiveName.setLabelFor(collectiveName);
			MainPanel.add(etiquetaCollectiveName); 
			MainPanel.add(collectiveName); 
			
			//Collective description
			etiquetaDescCollective = new JLabel("Descripcion:");
			descCollective = new JTextArea(6,8);
			JScrollPane scrollS = new JScrollPane(descCollective);
			
			etiquetaDescCollective.setLabelFor(descCollective);
			MainPanel.add(etiquetaDescCollective);
			MainPanel.add(scrollS);
			
			//Father name
			etiquetaFather = new JLabel("Nombre colectivo padre:");
			father = new JComboBox<String>();
			MainPanel.add(etiquetaFather);
			MainPanel.add(father);

			SpringUtilities.makeCompactGrid(MainPanel,
	               3, 2, //rows, cols
	                50, 6, //initX, initY
	                6, 6); //xPad, yPad
			
			container.add(MainPanel);
			window.setSize(350, 300);
		
		}

		public void setCollectives(List<Collective> c) {
			this.collectives = c;
		}
		
		/**
		 * Shows or hides this window depending on the value of parameter visible (boolean).
		 * @param visible
		 */
		public void setVisible(boolean visible_) {
			window.setVisible(visible_);
		}
		
		
		/**
		 * Return if the collective name.
		 * @return the name of the collective.
		 */
		public String getCollectiveName() {
			if((collectiveName.getText() == null || collectiveName.getText().equals(""))) {
				JOptionPane.showMessageDialog(null, "You must enter a collective name.", "Wrong project name.", JOptionPane.OK_OPTION);
				return null;
			}
			
			else {
				return collectiveName.getText();
			}
		}
		
		
		/**
		 * Return the description of the project the user has written.
		 * @return description of the project or null in case there is nothing written
		 */
		public String getDescription() {
			if ((descCollective.getText() == null || descCollective.getText().equals(""))) {
				JOptionPane.showMessageDialog(null, "You must write a description.", "Wrong description.", JOptionPane.OK_OPTION);
				return null; 
			}
			else {
				return descCollective.getText();
			}
		}
		
		/**
		 * Return the father of the created project.
		 * @return father of the created project.
		 */
		public String getFather() {
			if(father.getSelectedIndex() == 0 ) {
				return null;
			}
			else {
				return father.getItemAt(father.getSelectedIndex());
			}
		}
		
		/**
		 * Updates the options of the JComboBox
		 * to show all the collectives available to
		 * choose as parent
		 */
		public void update () {
			father.removeAllItems();
			father.addItem("Ninguno");
			if(collectives == null) {
				return;
			}
			for(Collective c: collectives) {
				father.addItem(c.getName());
			}
			return;
		}
		
		/**
		 * Sets the actions to perform when the cancel or the create button are clicked.
		 */
		public void setController(ActionListener c) {
			create.addActionListener(c);
			cancel.addActionListener(c);
		}
		
		/**
		 * Sets the operation that will happen by default when the user initiates a "close" on this 
		 * frame.
		 * @param operation
		 */
		public void setCloseOperation(int operation) {
			window.setDefaultCloseOperation(operation);
		}
	}

