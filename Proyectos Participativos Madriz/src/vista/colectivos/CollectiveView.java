package vista.colectivos;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import controlador.colectivos.ControlCollectiveView;
import controlador.proyectos.ControlProjectView;
import modelo.entities.Collective;
import modelo.projects.Project;
import vista.proyectos.SpringUtilities;

import java.util.List;

public class CollectiveView extends JPanel{
	
	JPanel container;
	
	Project p;
	
	JLabel title;
	JLabel desc;
	JLabel type;
	JLabel budget;
	JLabel creationDate;
	JLabel creator;
	JLabel nVoters;
	
	public CollectiveView() {
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		this.add(container);
	
		Dimension d = new Dimension(1000, 700);
        this.setMinimumSize(d);
        this.setMaximumSize(d);
        this.setPreferredSize(d);

		title = new JLabel("None");
		desc = new JLabel("None");
		type = new JLabel("None");
		budget = new JLabel("None");
		creationDate = new JLabel("None");
		creator = new JLabel("None");
		nVoters = new JLabel("None");
		
		JPanel auxTitle = new JPanel();
		container.add(auxTitle);
		auxTitle.setLayout(new BoxLayout(auxTitle, BoxLayout.X_AXIS));
		auxTitle.add(new JLabel("Title: "));
		auxTitle.add(title);
		
		JPanel auxDesc = new JPanel();
		container.add(auxDesc);
		auxDesc.setLayout(new BoxLayout(auxDesc, BoxLayout.X_AXIS));
		auxDesc.add(new JLabel("Description: "));
		auxDesc.add(desc);
		
		JPanel auxType = new JPanel();
		container.add(auxType);
		auxType.setLayout(new BoxLayout(auxType, BoxLayout.X_AXIS));
		auxType.add(new JLabel("Type: "));
		auxType.add(type);
		
		JPanel auxBudget = new JPanel();
		container.add(auxBudget);
		auxBudget.setLayout(new BoxLayout(auxBudget, BoxLayout.X_AXIS));
		auxBudget.add(new JLabel("Budget: "));
		auxBudget.add(budget);
		
		JPanel auxDate = new JPanel();
		container.add(auxDate);
		auxDate.setLayout(new BoxLayout(auxDate, BoxLayout.X_AXIS));
		auxDate.add(new JLabel("Creation date: "));
		auxDate.add(creationDate);
		
		JPanel auxCreator = new JPanel();
		container.add(auxCreator);
		auxCreator.setLayout(new BoxLayout(auxCreator, BoxLayout.X_AXIS));
		auxCreator.add(new JLabel("Creator: "));
		auxCreator.add(creator);
		
		JPanel auxVotes = new JPanel();
		container.add(auxVotes);
		auxVotes.setLayout(new BoxLayout(auxVotes, BoxLayout.X_AXIS));
		auxVotes.add(new JLabel("Number of votes: "));
		auxVotes.add(nVoters);
		
	}

	public void setController(ControlCollectiveView contCollectiveView) {
		// TODO Auto-generated method stub
		
	}

	public void update(Collective c) {
		title.setText(c.getName());
	}	
}