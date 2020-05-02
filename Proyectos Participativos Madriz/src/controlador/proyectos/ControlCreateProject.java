package controlador.proyectos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;

import javax.swing.*;
import java.util.*;

import modelo.entities.Collective;
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
			String collective = this.projectView.getCollectiveName();
			//We get the collective if it exist in the represented collective of the loggedUser
			Collective col = null;
			if(collective != null && collective != "") {
				Collective[] collectives= new Collective[this.modelo.getLoggedUser().getRepresentedCollectives().size()] ;
				this.modelo.getLoggedUser().getRepresentedCollectives().toArray(collectives);
				for(Collective c:collectives) {
					if(c.getName().equals(collective)) {
						col = c;
					}
				}
			}

			String title = this.projectView.getTitle();
			if(title == null) {
				return;
			}
			String description = this.projectView.getDescription();
			if(description == null) {
				return;
			}
			Double budget = this.projectView.getBudget();
			if(budget <= 0) {
				return;
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			Project project = null;
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
				if(group == null) {
					return;
				}
				
				String picture = this.projectView.getImage();
				//Lo crea como usuario
				if(collective == null || collective.equals("") || col == null) {
					if(picture == null) {
						project = new Social(title, description, budget, date, loggedUser, aux, group);
						loggedUser.addCreatedProject(project);
					}
					else {
						project = new Social(title, description, budget, date, loggedUser, aux, group, picture);
						loggedUser.addCreatedProject(project);
					}
				}
				//Sino
				else {
					if(picture == null) {
						project = new Social(title, description, budget, date, col, aux, group);
						col.addCreatedProject(project);
					}
					else {
						project = new Social(title, description, budget, date, col, aux, group, picture);
						col.addCreatedProject(project);
					}
				}
			}	
			//Infraestructural
			else {
				List<String> districts = new ArrayList<String>();
				districts = this.projectView.getAffectedDistricts();
				if(districts == null) {
					return;
				}
				ArrayList<District> affectedDistricts = new ArrayList<District>();
				District[] Districts= new District[this.modelo.getDistricts().size()];
				this.modelo.getDistricts().toArray(Districts);
				for(String s:districts) {
					for(District d:Districts) {
						if(s.equals(d.getName())) {
							affectedDistricts.add(d);
						}
					}
				}
				String location = this.projectView.getLocation();
				if(location == null) {
					return;
				}
				String scheme = this.projectView.getScheme();
				if(scheme == null) {
					return;
				}
				//Lo crea como usuario
				if(collective == null || collective.equals("") || col == null) {
					project = new Infrastructural(title, description, budget, date, loggedUser, affectedDistricts, scheme, location);
					loggedUser.addCreatedProject(project);  
				}
				//Sino
				else {
					project = new Infrastructural(title, description, budget, date, col, affectedDistricts, scheme, location);
					col.addCreatedProject(project);
				}
			}
			this.modelo.getPendingProjects().add(project);
			this.projectView.setVisible(false);
			
			break;
		case "Cancelar":
			this.projectView.setVisible(false);
		}
	}
}
