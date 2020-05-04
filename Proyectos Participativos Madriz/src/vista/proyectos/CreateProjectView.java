package vista.proyectos;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
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

import modelo.entities.Collective;
import modelo.projects.District;
import modelo.projects.Project;

import java.util.*;


public class CreateProjectView extends JPanel{
	
	List<Collective> representedCollectives;
	List<District> availableDistricts;
	
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
	JLabel etiquetaCollectiveS;
	JComboBox<String>comboS;
	//JTextField collectiveNameS;
	
	//Title project
	JLabel etiquetaTitleS;
	JTextField titleS; 
	
	//Cost
	JLabel etiquetaCostS;
	JTextField costS;
	
	//Description
	JLabel etiquetaDescS;
	JTextArea descS;
	
	//Scope
	JPanel scope;
	JLabel etiquetaScopeS;
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
	
	JLabel etiquetaCollectiveI;
	JComboBox<String>comboI;
	//JTextField collectiveNameI;
		
	//Title project
	JLabel etiquetaTitleI;
	JTextField titleI; 
		
	//Cost
	JLabel etiquetaCostI;
	JTextField costI;
		
	//Description
	JLabel etiquetaDescI;
	JTextArea descI;
			
	//Districts
	JPanel districts;
	JLabel etiquetaDistricts = new JLabel("Distritos afectados:");
	List<JCheckBox> ds;
	
	//Location
	JLabel etiquetaLocation;
	JTextField location;
	
	//Scheme
	JLabel etiquetaSc;
	JTextField scheme;
	
	//Auxiliar
	String aux = "Social";
	
	public CreateProjectView() {
		window = new JFrame("Nuevo proyecto");
		
		//Container container = window.getContentPane();
		JPanel container = new JPanel();
		SpringLayout layout = new SpringLayout();
		MainPanelSoc = new JPanel(layout);
		MainPanelInfr = new JPanel(layout);
		
		container.setLayout(layout);
		//window.setLayout(layout);
		
		JTabbedPane tab = new JTabbedPane();
		tab.addTab("Social", MainPanelSoc);
		tab.addTab("Infraestrucutal", MainPanelInfr);
		
		//Hacemos que cuando se cambie de pestania se borre lo escrito
		tab.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ev) {
				if(tab.getSelectedIndex() == 0) {
					titleI.setText("");
					location.setText("");
					descI.setText("");
					scheme.setText("");
					//collectiveNameI.setText("");
					comboI.setSelectedIndex(0);
					Component[] components = districts.getComponents();
					for(Component c : components) {
						((JCheckBox) c).setSelected(false);
					}
					costI.setText("1");
					aux = "Social";
				}
				else {
					titleS.setText("");
					descS.setText("");
					image.setText("");
					//collectiveNameS.setText("");
					comboS.setSelectedIndex(0);
					costS.setText("1");
					group.setText("");
					nacS.setSelected(true);
					aux = "Infrastructural";
				}
			}
		});
		
		//Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		//int a = (dim.width/2-this.getSize().width/2)/2;
		//int b = (dim.height/2-this.getSize().height/2)/2;
		//this.window.setLocation(a, b);
		 
		//Boton crear
		create = new JButton("Crear");
		container.add(create);
		//window.add(create);
		
		layout.putConstraint(SpringLayout.WEST, create, 600, SpringLayout.WEST, container);
		layout.putConstraint(SpringLayout.NORTH, create, 430, SpringLayout.NORTH, container);
		
		//Boton cancelar
		cancel = new JButton("Cancelar");
		container.add(cancel);
		//window.add(cancel);
		
		layout.putConstraint(SpringLayout.WEST, cancel, 3, SpringLayout.WEST, container);
		layout.putConstraint(SpringLayout.NORTH, cancel, 430, SpringLayout.NORTH, container);
		
		
		//OPCION SOCIAL
		
		//Colectivo 
		//String[] opciones = {"Si", "No"};
		//etiquetaColectiveS = new JLabel("Crear como colectivo:");
		//comboS = new JComboBox<String>(opciones);
		//comboS.setSelectedIndex(1);
		//etiquetaColectiveS.setLabelFor(comboS);
		
		//etiquetaCollectiveS = new JLabel("<html>Nombre colectivo (Opcional): <br> (Si el nombre no es correcto usted sera asignado como creador.)</html>");
		//collectiveNameS = new JTextField();
		
		etiquetaCollectiveS = new JLabel("Nombre del colectivo:");
		comboS = new JComboBox<String>();
		MainPanelSoc.add(etiquetaCollectiveS);
		MainPanelSoc.add(comboS);
		
		//Titulo proyecto
		etiquetaTitleS = new JLabel("Titulo del proyecto:");
		titleS = new JTextField(10);
		etiquetaTitleS.setLabelFor(titleS);
		MainPanelSoc.add(etiquetaTitleS);
		MainPanelSoc.add(titleS); 
		
		//Presupuesto
		etiquetaCostS = new JLabel("Presupuesto (euros):");
		costS = new JTextField(10);
		etiquetaCostS.setLabelFor(costS);
		costS.setText("1");
		MainPanelSoc.add(etiquetaCostS);
		MainPanelSoc.add(costS);
		
		//Descripcion proyecto
		etiquetaDescS = new JLabel("Descripcion:");
		descS = new JTextArea(8,6);
		JScrollPane scrollS = new JScrollPane(descS);
		
		etiquetaDescS.setLabelFor(descS);
		MainPanelSoc.add(etiquetaDescS);
		MainPanelSoc.add(scrollS);
		
		//Ambito
		etiquetaScopeS = new JLabel("Ambito:");
		nacS = new JRadioButton("Nacional");
		interS = new JRadioButton("Internacional");
		nacS.setSelected(true);
				
		groupS = new ButtonGroup();
		groupS.add(nacS);
		groupS.add(interS);
				
		scope = new JPanel(new GridLayout(1,2));
		scope.add(nacS);
		scope.add(interS);
		
		MainPanelSoc.add(etiquetaScopeS);
		MainPanelSoc.add(scope);
		
		//Grupo social
		etiquetaGroupS = new JLabel("Grupo social afectado:");
		group = new JTextField(10);
		etiquetaGroupS.setLabelFor(group);
		MainPanelSoc.add(etiquetaGroupS);
		MainPanelSoc.add(group);
		
		//Imagen
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
		//etiquetaColectiveI = new JLabel("Crear como colectivo:");
		//JComboBox<String> comboI = new JComboBox<String>(opciones);
		//comboI.setSelectedIndex(1);
		//etiquetaColectiveI.setLabelFor(comboI);
	
		etiquetaCollectiveI = new JLabel("Nombre del colectivo");
		comboI = new JComboBox<String>();
		MainPanelInfr.add(etiquetaCollectiveI);
		MainPanelInfr.add(comboI);
				
		//Titulo proyecto
		etiquetaTitleI = new JLabel("Titulo del proyecto:");
		titleI = new JTextField(10);
		etiquetaTitleI.setLabelFor(titleI);
		MainPanelInfr.add(etiquetaTitleI);
		MainPanelInfr.add(titleI); 
				
		//Presupuesto
		etiquetaCostI = new JLabel("Presupuesto (euros):");
		costI = new JTextField(10);
		etiquetaCostI.setLabelFor(costI);
		costI.setText("1");
		MainPanelInfr.add(etiquetaCostI);
		MainPanelInfr.add(costI);
				
		//Descripcion proyecto
		etiquetaDescI = new JLabel("Descripcion:");
		descI = new JTextArea(3,7);
		JScrollPane scrollI = new JScrollPane(descI);
				
		etiquetaDescI.setLabelFor(descI);
		MainPanelInfr.add(etiquetaDescI);
		MainPanelInfr.add(scrollI);
				
		//Distritos
		districts = new JPanel(new GridLayout(7,3));
		etiquetaDistricts = new JLabel("Distritos afectados:");
		
		MainPanelInfr.add(etiquetaDistricts);
		MainPanelInfr.add(districts);
		
		//Localizacion
		etiquetaLocation = new JLabel("Localizacion:");
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
		window.add(container);
		//window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(700,520);
		window.setLocationRelativeTo(null);
		//window.setVisible(true);
	}

	public void setRepresentedCollectives(List<Collective> c) {
		this.representedCollectives = c;
	}
	public void setDistricts(List<District> dlist) {
		this.availableDistricts = dlist;
		districts.removeAll();
		for(District d: dlist) {
			districts.add(new JCheckBox(d.getName()));
		}
	}
	
	/**
	 * Shows or hides this window depending on the value of parameter visible (boolean).
	 * @param visible
	 */
	public void setVisible(boolean visible_) {
		window.setVisible(visible_);
	}
	
	/**
	 * Return the project type.
	 * @return the project type.
	 */
	public String getProjectType() {
		return aux;
	}
	
	/**
	 * Return if the project has been created by the user as a collective or not.
	 * @return the name collective that has created the project or null if it has been created by the user.
	 */
	public String getCollectiveName() {
		if(aux == "Social" && comboS.getSelectedIndex() == 0 ) {
			return null;
		}
		if(aux == "Infrastructural"&& comboI.getSelectedIndex() == 0) {
			return null;
		}
		if(aux == "Social" && comboS.getSelectedIndex() != 0 ) {
			return comboS.getItemAt(comboS.getSelectedIndex());
		}
		else {
			return comboI.getItemAt(comboI.getSelectedIndex());
		}
	}
	
	/**
	 * Return the name of the project the user has written.
	 * @return name of the project or null in case there is nothing written
	 */
	public String getTitle() {
		if ((titleS.getText() == null || titleS.getText().equals("")) && (titleI.getText() == null || titleI.getText().equals(""))) {
			JOptionPane.showMessageDialog(null, "You must enter a project name.", "Wrong project name.", JOptionPane.OK_OPTION);
			return null; 
		}
		if(aux == "Social"){
			return titleS.getText();
		}
		else {
			return titleI.getText();
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
		if(aux == "Social"){
			return descS.getText();
		}
		else {
			return descI.getText();
		}
	}
	
	/**
	 * Return the cost of the project the user has written.
	 * @return cost of the project or -1 in case there is nothing written or -2 in case the cost is less than or equal to 0.
	 */
	public double getCost() {
		if ((costS.getText() == null || costS.getText().equals("")) && (costI.getText() == null || costI.getText().equals(""))) {
			JOptionPane.showMessageDialog(null, "You must write a cost.", "Wrong cost.", JOptionPane.OK_OPTION);
			return -1; 
		}
		if(aux =="Social") {
			if(costS.getText().matches((".*[a-zA-Z]+.*"))) {
				JOptionPane.showMessageDialog(null, "A cost must be a number.", "You must write a valid cost.", JOptionPane.OK_OPTION);
				return -3;
			}
		}
		else {
			if(costI.getText().matches((".*[a-zA-Z]+.*"))) {
				JOptionPane.showMessageDialog(null, "A cost must be a number.", "You must write a valid cost.", JOptionPane.OK_OPTION);
				return -3;
			}
		}
		if(Double.parseDouble(costS.getText()) <= 1 && Double.parseDouble(costI.getText()) <= 1){
			JOptionPane.showMessageDialog(null, "You must write a valid cost.", "You must write a valid cost.", JOptionPane.OK_OPTION);
			return -2;
		}
		if(aux == "Social") {
			return Double.parseDouble(costS.getText());

		}
		else {
			return Double.parseDouble(costI.getText());
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
	public String getScope() {
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

	public List<String> getAffectedDistricts() {
		List<String> affected = new ArrayList<String>();
		Component[] components = districts.getComponents();
		int aux = 0;
		for(Component c : components) {
			if(((JCheckBox) c).isSelected() == true) {
				affected.add(((JCheckBox) c).getText());
			}
			else {
				aux = aux + 1;
			}
		}
		
		if(aux == 21) {
			JOptionPane.showMessageDialog(null, "You must select at least one district.", "District Error.", JOptionPane.OK_OPTION);
			return null; 
		}
		else {
			return affected;
		}
	}
	
	/**
	 * Return the image(Optional) districts of a social project.
	 * @return the image as a string.
	 */
	public String getImage() {
		if(image.getText() == null || image.getText().equals("")) {
			return null;
		}
		return image.getText();
	}
	
	/**
	 * Return the location of a infrastructural project.
	 * @return the location as a string.
	 */
	public String getLocationP() {
		if(location.getText() == null || location.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "You must write a location for the project.", "Wrong location.", JOptionPane.OK_OPTION);
			return null; 
		}
		return location.getText();
	}
	
	/**
	 * Return the scheme of a infrastructural project.
	 * @return the scheme as a string.
	 */
	public String getScheme() {
		if(scheme.getText() == null || scheme.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "You must write an scheme for the project.", "Wrong scheme.", JOptionPane.OK_OPTION);
			return null; 
		}
		return scheme.getText();
	}
	
	public void update () {
		comboS.removeAllItems();
		comboS.addItem("Ninguno");
		comboI.removeAllItems();
		comboI.addItem("Ninguno");
		if(representedCollectives == null) {
			return;
		}
		for(Collective co: representedCollectives) {
			comboS.addItem(co.getName());
			comboI.addItem(co.getName());
		}
		return;
	}
	
	public void setController(ActionListener c) {
		create.addActionListener(c);
		cancel.addActionListener(c);
	}
	
	public void setCloseOperation(int operation) {
		window.setDefaultCloseOperation(operation);
	}
}

