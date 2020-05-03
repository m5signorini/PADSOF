package vista.proyectos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.proyectos.ControlProjectView;
import modelo.projects.Project;

public class ProjectView extends JPanel{
	
	JPanel container;
	
	Project p;
	
	JLabel title;
	JLabel desc;
	JLabel type;
	JLabel budget;
	JLabel creationDate;
	JLabel creator;
	JLabel nVoters;
	
	public ProjectView() {
		container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		this.add(container);
		container.setBackground(new Color(190,255,255));
		Dimension d = new Dimension(1000, 1000);
		container.setMinimumSize(d);
		container.setMaximumSize(d);
		container.setPreferredSize(d);

		title = new JLabel("None");
		desc = new JLabel("None");
		type = new JLabel("None");
		budget = new JLabel("None");
		creationDate = new JLabel("None");
		creator = new JLabel("None");
		nVoters = new JLabel("None");

		title.setFont(new Font("serif", Font.BOLD, 50));
		container.add(title);
		
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

	public void setController(ControlProjectView contProjectView) {
		// TODO Auto-generated method stub
		
	}

	public void update(Project p) {
		// TODO Auto-generated method stub
		
	}	
	
	
}

