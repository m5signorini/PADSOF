package controlador.proyectos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;

import javax.swing.*;
import java.util.*;
import modelo.entities.individuals.*;
import modelo.functionalities.Application;
import modelo.projects.*;
import vista.proyectos.CreateProjectView;

public class ControlCreateProject implements ActionListener {
	private Application modelo;
	private CreateProjectView projectView;
	
	public ControlCreateProject(Application modelo, CreateProjectView projectView) {
		this.modelo = modelo;
		this.projectView = projectView;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		JButton button = (JButton)arg0.getSource();
		switch(button.getActionCommand()) {
		case "Crear":
			User loggedUser = this.modelo.getLoggedUser();
			String colectively = this.projectView.getCollectiveOption();
			String title = this.projectView.getTitle();
			String description = this.projectView.getDescription();
			Double budget = this.projectView.getBudget();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			//Social
			if(this.projectView.getProjectType() == "Social") {
				String scope = this.projectView.getScope();
				ScopeType aux;
				if(scope == "National") {
					aux = ScopeType.national;
				}
				else {
					aux = ScopeType.international;
				}
				String group = this.projectView.getAffectedSocialGroup();
				String picture = this.projectView.getImage();
				//Lo crea como usuario
				if() {
					if(picture == null) {
						Social project = new Social(title, description, budget, date, loggedUser, aux, group);
						loggedUser.addCreatedProject(project);
					}
					else {
						Social project = new Social(title, description, budget, date, loggedUser, aux, group, picture);
						loggedUser.addCreatedProject(project);
					}
				}
				//Sino
				else {
					if(picture == null) {
						Social project = new Social(title, description, budget, date, algunColectivo, aux, group);
						algunColectivo.addCreatedProject(project);
					}
					else {
						Social project = new Social(title, description, budget, date, algunColectivo, aux, group, picture);
						algunColectivo.addCreatedProject(project);
					}
				}
			}	
			//Infraestructural
			else {
				List<String> districts = new ArrayList<String>();
				districts = this.projectView.getAffectedDistricts();
				ArrayList<District> affectedDistricts = new ArrayList<District>();
				for(String s:districts) {
					for(District d:this.modelo.getDistricts()) {
						if(s == d.getName()) {
							affectedDistricts.add(d);
						}
					}
				}
				String location = this.projectView.getLocation();
				String scheme = this.projectView.getScheme();
				//Lo crea como usuario
				if() {
					 Infrastructural project = new Infrastructural(title, description, budget, date, loggedUser, affectedDistricts, scheme, location);
						loggedUser.addCreatedProject(project);
				}
				//Sino
				else {
					 Infrastructural project = new Infrastructural(title, description, budget, date, algunColectivo, affectedDistricts, scheme, location);
						algunColectivo.addCreatedProject(project);
				}
			}
			this.modelo.getPendingProjects().add(project);
			this.projectView.setCloseOperation(JFrame.EXIT_ON_CLOSE);
			break;
		case "Cancelar":
			this.projectView.setCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}
}
