package vista.proyectos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.proyectos.ControlProjectView;
import modelo.entities.Collective;
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

	JButton seguir;
	JButton dejarDeSeguir;
	JButton apoyar;
	JButton volver;
	
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
		
		container.add(Box.createRigidArea(new Dimension(0, 50)));
		
		JPanel auxDesc = new JPanel();
		container.add(auxDesc);
		auxDesc.setLayout(new BoxLayout(auxDesc, BoxLayout.X_AXIS));
		auxDesc.add(new JLabel("Description: "));
		auxDesc.add(desc);
		
		container.add(Box.createRigidArea(new Dimension(0, 50)));
		
		JPanel auxType = new JPanel();
		container.add(auxType);
		auxType.setLayout(new BoxLayout(auxType, BoxLayout.X_AXIS));
		auxType.add(new JLabel("Type: "));
		auxType.add(type);
		
		container.add(Box.createRigidArea(new Dimension(0, 50)));
		
		JPanel auxBudget = new JPanel();
		container.add(auxBudget);
		auxBudget.setLayout(new BoxLayout(auxBudget, BoxLayout.X_AXIS));
		auxBudget.add(new JLabel("Budget: "));
		auxBudget.add(budget);
		
		container.add(Box.createRigidArea(new Dimension(0, 50)));
		
		JPanel auxDate = new JPanel();
		container.add(auxDate);
		auxDate.setLayout(new BoxLayout(auxDate, BoxLayout.X_AXIS));
		auxDate.add(new JLabel("Creation date: "));
		auxDate.add(creationDate);
		
		container.add(Box.createRigidArea(new Dimension(0, 50)));
		
		JPanel auxCreator = new JPanel();
		container.add(auxCreator);
		auxCreator.setLayout(new BoxLayout(auxCreator, BoxLayout.X_AXIS));
		auxCreator.add(new JLabel("Creator: "));
		auxCreator.add(creator);
		
		container.add(Box.createRigidArea(new Dimension(0, 50)));
		
		JPanel auxVotes = new JPanel();
		container.add(auxVotes);
		auxVotes.setLayout(new BoxLayout(auxVotes, BoxLayout.X_AXIS));
		auxVotes.add(new JLabel("Number of votes: "));
		auxVotes.add(nVoters);
		
		seguir = new JButton("Seguir al proyecto para mantenerte al dia");
		volver = new JButton("Volver");
		apoyar = new JButton("Votar por el proyecto");
		dejarDeSeguir = new JButton("Dejar de seguir el proyecto");

		container.add(Box.createRigidArea(new Dimension(0, 50)));
		container.add(seguir);
		container.add(Box.createRigidArea(new Dimension(0, 50)));
		container.add(volver);
		container.add(Box.createRigidArea(new Dimension(0, 50)));
		container.add(apoyar);
		container.add(Box.createRigidArea(new Dimension(0, 50)));
		container.add(dejarDeSeguir);
		
	}
	
	public Project getProject() {
		return this.p;
	}

	public void setController(ActionListener a) {
		seguir.addActionListener(a);
		volver.addActionListener(a);
		apoyar.addActionListener(a);	
		dejarDeSeguir.addActionListener(a);
	}

	public void update(Project p) {
		this.p = p;
		title.setText(p.getTitle());
		desc.setText(p.getDescription());
		creator.setText(p.getCreator().toString());
		nVoters.setText(Integer.toString(p.getVoters().size()));
	}	
	
	
}

