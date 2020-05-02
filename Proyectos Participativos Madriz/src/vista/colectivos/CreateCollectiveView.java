package vista.colectivos;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import vista.proyectos.SpringUtilities;

import java.util.*;

public class CreateCollectiveView extends JPanel{

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

		//Father colective
		JLabel etiquetaFather;
		JTextField father;
		
		
		public CreateCollectiveView() {
			window = new JFrame("Nuevo colectivo");
			
			Container container = window.getContentPane();
			SpringLayout layout = new SpringLayout();
			MainPanel = new JPanel(layout);
			
			container.setLayout(layout);
			 
			//Boton crear
			create = new JButton("Crear");
			container.add(create);
			
			layout.putConstraint(SpringLayout.WEST, create, 375, SpringLayout.WEST, container);
			layout.putConstraint(SpringLayout.NORTH, create,200, SpringLayout.NORTH, container);
			
			//Boton cancelar
			cancel = new JButton("Cancelar");
			container.add(cancel);
			
			layout.putConstraint(SpringLayout.WEST, cancel, 3, SpringLayout.WEST, container);
			layout.putConstraint(SpringLayout.NORTH, cancel, 200, SpringLayout.NORTH, container);
			
			//Collective name
			etiquetaCollectiveName = new JLabel("Nombre del colectivo:");
			collectiveName = new JTextField(10);
			etiquetaCollectiveName.setLabelFor(collectiveName);
			MainPanel.add(etiquetaCollectiveName); 
			MainPanel.add(collectiveName); 
			
			//Collective description
			etiquetaDescCollective = new JLabel("Descripción:");
			descCollective = new JTextArea(6,8);
			JScrollPane scrollS = new JScrollPane(descCollective);
			
			etiquetaDescCollective.setLabelFor(descCollective);
			MainPanel.add(etiquetaDescCollective);
			MainPanel.add(scrollS);
			
			//Father name
			//etiquetaFather = new JLabel("<html>Nombre colectivo padre(Opcional):  <br> (Si el nombre no es correcto el <br> colectivo será creado sin padre.)</html>");
			etiquetaFather = new JLabel("Nombre colectivo padre(Opcional):");
			father = new JTextField();
			MainPanel.add(etiquetaFather);
			MainPanel.add(father);

			SpringUtilities.makeCompactGrid(MainPanel,
	               3, 2, //rows, cols
	                50, 6, //initX, initY
	                6, 6); //xPad, yPad
			
			container.add(MainPanel);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setSize(450, 250);
			window.setVisible(true);
		
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
		 * Return the budget of the project the user has written.
		 * @return budget of the project or -1 in case there is nothing written or -2 in case the budget is less than or equal to 0.
		 */
		public String getFather() {
			if ((father.getText() == null || father.getText().equals(""))) {
				return null;
			}
			else {
				return father.getText();
			}
		}
		
		
		public void setController(ActionListener c) {
			create.addActionListener(c);
			cancel.addActionListener(c);
		}
		
		public void setCloseOperation(int operation) {
			window.setDefaultCloseOperation(operation);
		}
	}

