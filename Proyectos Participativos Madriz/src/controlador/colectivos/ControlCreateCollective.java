package controlador.colectivos;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JButton;

import modelo.entities.Collective;
import modelo.entities.individuals.User;
import modelo.functionalities.Application;
import vista.Ventana;
import vista.colectivos.CreateCollectiveView;

/** Class that contains the components and functionalities
 * to control the CreateCollectiveView screen.
 * @author Cesar Ramirez Martinez
 */
public class ControlCreateCollective implements ActionListener {
	private Application modelo;
	private CreateCollectiveView collectiveView;
	private Ventana frame;
	
	public ControlCreateCollective(Application modelo, Ventana frame2) {
		this.modelo = modelo;
		this.frame = frame2;
		this.collectiveView = frame2.getCreateCollectiveView();
	}
	
	/** 
	 * Here we set the action for every button in a CreateCollectiveView. 
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		JButton button = (JButton)arg0.getSource();
		Collective finalC;
		User u = this.modelo.getLoggedUser();
		
		switch(button.getActionCommand()) {
		case "Crear":
			String name = collectiveView.getCollectiveName();
			if(name == null) {
				return;
			}
			String description = collectiveView.getDescription();
			if(description == null) {
				return;
			}
			String father = collectiveView.getFather();
			if(father != null) {
				Collective fatherC = null;
				List<Collective> collectives = this.modelo.getCollectives();
				for(Collective c:collectives) {
					if(c.getName() == father) {
						fatherC = c;
					}
				}
				//Como colectivo sin padre
				if(fatherC == null) {
					finalC = new Collective(name, description, u);
					u.createCollective(finalC);
					
				}
				//Colectivo con padre
				else {
					finalC = new Collective(name, description, u, fatherC);
					u.createCollective(finalC);
				}
			}
			else {
				finalC = new Collective(name, description, u);
				u.createCollective(finalC);
			}
			this.modelo.createCollective(finalC);
			this.collectiveView.setVisible(false);
			break;
		case "Cancelar":
			this.collectiveView.setVisible(false);
			break;
		default:
			break;
		}
	}
	
	/**
	 * ControlCreateCollective sets the action for every button in an CreateCollectiveView. 
	 * @param l the list of collectives that will be set in the CreateCollectiveView attribute
	 */
	public void setCollectivos(List<Collective> l) {
		this.collectiveView.setCollectives(l);
	}
}
