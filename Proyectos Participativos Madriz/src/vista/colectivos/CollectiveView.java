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
	
	Collective c;
	
	JLabel title;
	JLabel desc;
	JLabel creator;
	JLabel nMembers;
	
	JButton unirse;
	JButton volver;
	JButton abandonar;
	
	public CollectiveView() {
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
		creator = new JLabel("None");
		nMembers = new JLabel("None");
		
		container.add(Box.createRigidArea(new Dimension(0, 100)));

		title.setFont(new Font("serif", Font.BOLD, 70));
		container.add(title);
		
		container.add(Box.createRigidArea(new Dimension(0, 50)));
		
		JPanel auxDesc = new JPanel();
		auxDesc.setFont(new Font("serif", Font.BOLD, 20));
		container.add(auxDesc);
		auxDesc.setLayout(new BoxLayout(auxDesc, BoxLayout.X_AXIS));
		auxDesc.add(new JLabel("Description: "));
		auxDesc.add(desc);
		
		container.add(Box.createRigidArea(new Dimension(0, 50)));
		
		JPanel auxCreator = new JPanel();
		auxCreator.setFont(new Font("serif", Font.BOLD, 20));
		container.add(auxCreator);
		auxCreator.setLayout(new BoxLayout(auxCreator, BoxLayout.X_AXIS));
		auxCreator.add(new JLabel("Creator: "));
		auxCreator.add(creator);
		
		container.add(Box.createRigidArea(new Dimension(0, 50)));
		
		JPanel auxMembers = new JPanel();
		auxMembers.setFont(new Font("serif", Font.BOLD, 20));
		container.add(auxMembers);
		auxMembers.setLayout(new BoxLayout(auxMembers, BoxLayout.X_AXIS));
		auxMembers.add(new JLabel("Number of members: "));
		auxMembers.add(nMembers);

		unirse = new JButton("Unirse al colectivo");
		volver = new JButton("Volver");
		abandonar = new JButton("Abandonar colectivo");

		container.add(Box.createRigidArea(new Dimension(0, 50)));
		container.add(unirse);
		container.add(Box.createRigidArea(new Dimension(0, 50)));
		container.add(volver);
		container.add(Box.createRigidArea(new Dimension(0, 50)));
		container.add(abandonar);
		
	}
	
	public Collective getCollective() {
		return this.c;
	}

	public void setController(ActionListener a) {
		unirse.addActionListener(a);
		volver.addActionListener(a);
		abandonar.addActionListener(a);		
	}

	public void update(Collective c) {
		this.c = c;
		title.setText(c.getName());
		desc.setText(c.getDescription());
		creator.setText(c.getRepresentative().toString());
		nMembers.setText(Integer.toString(c.getMembers().size()));		
	}	
}