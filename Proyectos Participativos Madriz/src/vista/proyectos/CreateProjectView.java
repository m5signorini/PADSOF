package vista.proyectos;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.*;


public class CreateProjectView {
	JFrame window;
	//Group of tabs
	JTabbedPane tab;
	//Main panels
	JPanel MainPanelSoc;
	JPanel MainPanelInfr;
	
	//Create button
	JButton create;
	//Cancel button
	JButton cancel;
	
	//SOCIAL
	//Collective option
	JLabel etiquetaColectiveS;
    JComboBox<String> comboS;
	
	//Title project
	JLabel etiquetaTitleS;
	JTextField titleS; 
	
	//Budget
	JLabel etiquetaBudgetS;
	JTextField budgetS;
	
	//Description
	JLabel etiquetaDescS;
	JTextArea descS;
	
	//Ambit
	JPanel ambit;
	JLabel etiquetaAmbitS;
	ButtonGroup groupS;
	JRadioButton nacS;
	JRadioButton interS;
	
	//Social group
	JLabel etiquetaGroupS;
	JTextField group;
	
	//Image
	JLabel etiquetaImage;
	JTextField image;
	
	
	//INFRASTRUCTURAL
	
	//Collective option
	JLabel etiquetaColectiveI;
	JComboBox<String> comboI;
		
	//Title project
	JLabel etiquetaTitleI;
	JTextField titleI; 
		
	//Budget
	JLabel etiquetaBudgetI;
	JTextField budgetI;
		
	//Description
	JLabel etiquetaDescI;
	JTextArea descI;
			
	//Districts
	JPanel districts;
	JLabel etiquetaDistricts = new JLabel("Distritos afectados:");
	JCheckBox d1;
	JCheckBox d2;
	JCheckBox d3;
	JCheckBox d4;
	JCheckBox d5;
	JCheckBox d6;
	JCheckBox d7;
	JCheckBox d8;
	JCheckBox d9;
	JCheckBox d10;
	JCheckBox d11;
	JCheckBox d12;
	JCheckBox d13;
	JCheckBox d14;
	JCheckBox d15;
	JCheckBox d16;
	JCheckBox d17;
	JCheckBox d18;
	JCheckBox d19;
	JCheckBox d20;
	JCheckBox d21;
	
	//Location
	JLabel etiquetaLocation;
	JTextField location;
	
	//Scheme
	JLabel etiquetaSc;
	JTextField scheme;
	
	public CreateProjectView() {
		window = new JFrame("Nuevo proyecto");
		
		Container container = window.getContentPane();
		SpringLayout layout = new SpringLayout();
		MainPanelSoc = new JPanel(layout);
		MainPanelInfr = new JPanel(layout);
		
		container.setLayout(layout);
		
		JTabbedPane tab = new JTabbedPane();
		tab.addTab("Social", MainPanelSoc);
		tab.addTab("Infraestrucutal", MainPanelInfr);
		
		//Hacemos que cuando se cambie de pestaña se borre lo escrito
		tab.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ev) {
				if(tab.getSelectedIndex() == 0) {
					titleI.setText("");
					location.setText("");
					descI.setText("");
					scheme.setText("");
					comboI.setSelectedIndex(1);
					Component[] components = districts.getComponents();
					for(Component c : components) {
						((JCheckBox) c).setSelected(false);
					}
					budgetI.setText("1");
				}
				else {
					titleS.setText("");
					descS.setText("");
					image.setText("");
					comboS.setSelectedIndex(1);
					budgetS.setText("1");
					group.setText("");
					nacS.setSelected(true);
				}
			}
		});
		 
		//Boton crear
		create = new JButton("Crear");
		container.add(create);
		
		layout.putConstraint(SpringLayout.WEST, create, 607, SpringLayout.WEST, container);
		layout.putConstraint(SpringLayout.NORTH, create, 425, SpringLayout.NORTH, container);
		
		//Boton cancelar
		cancel = new JButton("Cancelar");
		container.add(cancel);
		
		layout.putConstraint(SpringLayout.WEST, cancel, 3, SpringLayout.WEST, container);
		layout.putConstraint(SpringLayout.NORTH, cancel, 425, SpringLayout.NORTH, container);
		     
		
		//OPCION SOCIAL
		
		//Colectivo ComboBox
		String[] opciones = {"Sí", "No"};
		etiquetaColectiveS = new JLabel("Crear como colectivo:");
		comboS = new JComboBox<String>(opciones);
		comboS.setSelectedIndex(1);
		etiquetaColectiveS.setLabelFor(comboS);
		MainPanelSoc.add(etiquetaColectiveS);
		MainPanelSoc.add(comboS);
		
		//Titulo proyecto
		etiquetaTitleS = new JLabel("Título del proyecto:");
		titleS = new JTextField(10);
		etiquetaTitleS.setLabelFor(titleS);
		MainPanelSoc.add(etiquetaTitleS);
		MainPanelSoc.add(titleS); 
		
		//Presupuesto
		etiquetaBudgetS = new JLabel("Presupuesto (€):");
		budgetS = new JTextField(10);
		etiquetaBudgetS.setLabelFor(budgetS);
		budgetS.setText("1");
		MainPanelSoc.add(etiquetaBudgetS);
		MainPanelSoc.add(budgetS);
		
		//Descripcion proyecto
		etiquetaDescS = new JLabel("Descripción:");
		descS = new JTextArea(8,6);
		JScrollPane scrollS = new JScrollPane(descS);
		
		etiquetaDescS.setLabelFor(descS);
		MainPanelSoc.add(etiquetaDescS);
		MainPanelSoc.add(scrollS);
		
		//Ambito
		etiquetaAmbitS = new JLabel("Ámbito:");
		nacS = new JRadioButton("Nacional");
		interS = new JRadioButton("Internacional");
		nacS.setSelected(true);
				
		groupS = new ButtonGroup();
		groupS.add(nacS);
		groupS.add(interS);
				
		ambit = new JPanel(new GridLayout(1,2));
		ambit.add(nacS);
		ambit.add(interS);
		
		MainPanelSoc.add(etiquetaAmbitS);
		MainPanelSoc.add(ambit);
		
		//Grupo social
		etiquetaGroupS = new JLabel("Grupo social afectado:");
		group = new JTextField(10);
		etiquetaGroupS.setLabelFor(group);
		MainPanelSoc.add(etiquetaGroupS);
		MainPanelSoc.add(group);
		
		//Esquema
		etiquetaImage = new JLabel("Imagen(Opcional):");
		image = new JTextField(10);
		etiquetaImage.setLabelFor(image);
		MainPanelSoc.add(etiquetaImage);
		MainPanelSoc.add(image);
		
		SpringUtilities.makeCompactGrid(MainPanelSoc,
               7, 2, //rows, cols
                50, 6, //initX, initY
                6, 6); //xPad, yPad
		
		
		
		//OPCION INFRAESTRUCTURAL
		
		//Colectivo ComboBox
		etiquetaColectiveI = new JLabel("Crear como colectivo:");
		JComboBox<String> comboI = new JComboBox<String>(opciones);
		comboI.setSelectedIndex(1);
		etiquetaColectiveI.setLabelFor(comboI);
		MainPanelInfr.add(etiquetaColectiveI);
		MainPanelInfr.add(comboI);
				
		//Titulo proyecto
		etiquetaTitleI = new JLabel("Título del proyecto:");
		titleI = new JTextField(10);
		etiquetaTitleI.setLabelFor(titleI);
		MainPanelInfr.add(etiquetaTitleI);
		MainPanelInfr.add(titleI); 
				
		//Presupuesto
		etiquetaBudgetI = new JLabel("Presupuesto (€):");
		budgetI = new JTextField(10);
		etiquetaBudgetI.setLabelFor(budgetI);
		budgetI.setText("1");
		MainPanelInfr.add(etiquetaBudgetI);
		MainPanelInfr.add(budgetI);
				
		//Descripcion proyecto
		etiquetaDescI = new JLabel("Descripción:");
		descI = new JTextArea(3,7);
		JScrollPane scrollI = new JScrollPane(descI);
				
		etiquetaDescI.setLabelFor(descI);
		MainPanelInfr.add(etiquetaDescI);
		MainPanelInfr.add(scrollI);
				
		//Distritos
		districts = new JPanel(new GridLayout(7,3));
		etiquetaDistricts = new JLabel("Distritos afectados:");
		d1 = new JCheckBox("Arganzuela");
		districts.add(d1);
		d2 = new JCheckBox("Barajas");
		districts.add(d2);
		d3 = new JCheckBox("Carabanchel");
		districts.add(d3);
		d4 = new JCheckBox("Centro");
		districts.add(d4);
		d5 = new JCheckBox("Chamartín");
		districts.add(d5);
		d6 = new JCheckBox("Chamberí");
		districts.add(d6);
		d7 = new JCheckBox("Ciudad Lineal");
		districts.add(d7);
		d8 = new JCheckBox("Fuencarral-El Pardo");
		districts.add(d8);
		d9 = new JCheckBox("Hortaleza");
		districts.add(d9);
		d10 = new JCheckBox("Latina");
		districts.add(d10);
		d11 = new JCheckBox("Moncloa-Aravaca");
		districts.add(d11);
		d12 = new JCheckBox("Moratalaz");
		districts.add(d12);
		d13 = new JCheckBox("Puente de Vallecas");
		districts.add(d13);
		d14 = new JCheckBox("Retiro");
		districts.add(d14);
		d15 = new JCheckBox("Salamanca");
		districts.add(d15);
		d16 = new JCheckBox("San Blas-Canillejas");
		districts.add(d16);
		d17 = new JCheckBox("Tetuán");
		districts.add(d17);
		d18 = new JCheckBox("Usera");
		districts.add(d18);
		d19 = new JCheckBox("Vicálvaro");
		districts.add(d19);
		d20 = new JCheckBox("Villa de Vallecas");
		districts.add(d20);
		d21 = new JCheckBox("Villaverde");
		districts.add(d21);
		
		MainPanelInfr.add(etiquetaDistricts);
		MainPanelInfr.add(districts);
		
		//Localización
		etiquetaLocation = new JLabel("Localización:");
		location = new JTextField(10);
		etiquetaLocation.setLabelFor(location);
		MainPanelInfr.add(etiquetaLocation);
		MainPanelInfr.add(location);
			
		//Esquema
		etiquetaSc = new JLabel("Esquema/Imagen:");
		scheme = new JTextField(10);
		etiquetaSc.setLabelFor(scheme);
		MainPanelInfr.add(etiquetaSc);
		MainPanelInfr.add(scheme);
		
						
		SpringUtilities.makeCompactGrid(MainPanelInfr,
		             7, 2, //rows, cols
		              50, 6, //initX, initY
		              6, 6); //xPad, yPad
		
		container.add(tab);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(690,480);
		window.setVisible(true);
	
	}
	
	/**
	 * Return the project type.
	 * @return the project type.
	 */
	public String getProjectType() {
		if(tab.getSelectedIndex() == 0) {
			return "Social";
		}
		else {
			return "Infrastructural";
		}
	}
	
	/**
	 * Return if the project has been created by the user as a collective or not.
	 * @return if the project has been created by the user as a collective or not.
	 */
	public String getCollectiveOption() {
		if(tab.getSelectedIndex() == 0) {
			if(String.valueOf(comboS.getSelectedItem()) == "No") {
				return "No";
			}
			else {
				return "Sí";
			}
		}
		else {
			if(String.valueOf(comboI.getSelectedItem()) == "No") {
				return "No";
			}
			else {
				return "Sí";
			}
		}
	}
	
	/**
	 * Return the name of the project the user has written.
	 * @return name of the project or null in case there is nothing written
	 */
	public String getTitle() {
		if ((titleS.getText() == null || titleS.getText().equals("")) && (titleI.getText() == null || titleI.getText().equals(""))) {
			JOptionPane.showMessageDialog(null, "You must a project name.", "Wrong project name.", JOptionPane.OK_OPTION);
			return null; 
		}
		if(tab.getSelectedIndex() == 0){
			return titleS.getText();
		}
		else {
			return titleS.getText();
		}
	}
	
	/**
	 * Return the description of the project the user has written.
	 * @return description of the project or null in case there is nothing written
	 */
	public String getDescription() {
		if ((descS.getText() == null || descS.getText().equals("")) && (descI.getText() == null || descI.getText().equals(""))) {
			JOptionPane.showMessageDialog(null, "You must write a description.", "Wrong description.", JOptionPane.OK_OPTION);
			return null; 
		}
		if(tab.getSelectedIndex() == 0){
			return descI.getText();
		}
		else {
			return descS.getText();
		}
	}
	
	/**
	 * Return the budget of the project the user has written.
	 * @return budget of the project or -1 in case there is nothing written or -2 in case the budget is less than or equal to 0.
	 */
	public double getBudget() {
		if ((budgetS.getText() == null || budgetS.getText().equals("")) && (budgetI.getText() == null || budgetI.getText().equals(""))) {
			JOptionPane.showMessageDialog(null, "You must write a description.", "Wrong description.", JOptionPane.OK_OPTION);
			return -1; 
		}
		
		if(Double.parseDouble(budgetS.getText()) <= 0 || Double.parseDouble(budgetI.getText()) <= 0){
			return -2;
		}
		if(tab.getSelectedIndex() == 0) {
			return Double.parseDouble(budgetI.getText());
		}
		else {
			return Double.parseDouble(budgetS.getText());
		}
	}
	
	/**
	 * Return the affected social group of the social project the user has created.
	 * @return the affected social group or null in case there is nothing written.
	 */
	public String getAffectedSocialGroup() {
		if ((group.getText() == null || group.getText().equals(""))) {
			JOptionPane.showMessageDialog(null, "You must write an affected social group.", "Wrong affected social group.", JOptionPane.OK_OPTION);
			return null; 
		}
		else {
			return group.getText();
		}
	}
	
	/**
	 * Return the ambit of the social project the user has created.
	 * @return ambit of the affected social group.
	 */
	public String getAmbit() {
		if (nacS.isSelected() == true) {
			return "Nacional";
		}
		else {
			return "Internacional";
		}
	}
	
	/**
	 * Return the affected districts of an infrastructural project in a list.
	 * @return list of affected districts.
	 */

	public List<String> affectedDistricts() {
		List<String> affected = new ArrayList<String>();
		Component[] components = districts.getComponents();
		for(Component c : components) {
			if(((JCheckBox) c).isSelected() == true) {
				affected.add(((JCheckBox) c).getText());
			}
		}
		return affected;
	}
	
	public void setController(ActionListener c) {
		create.addActionListener(c);
		cancel.addActionListener(c);
	}
	
	public void setCloseOperation(int operation) {
		window.setDefaultCloseOperation(operation);
	}
}

