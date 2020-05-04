package controlador.colectivos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modelo.entities.Collective;
import modelo.entities.individuals.User;
import modelo.exceptions.JoinException;
import modelo.functionalities.Application;
import vista.Ventana;
import vista.colectivos.CollectiveView;

/**
 * The ControlCollectiveView class implements the actions that should be performed when 
 * the different buttons in the CollectiveView view are clicked.
 * @author Pedro Urbina Rodriguez 
 */

public class ControlCollectiveView implements ActionListener {
	private Application modelo;
	private Ventana frame;
	private CollectiveView colectiveView;
	
	public ControlCollectiveView(Application modelo, Ventana frame2) {
		this.modelo = modelo;
		this.frame = frame2;
		this.colectiveView = frame.getCollectiveView();
	}

	/**
	 * When the event occurs in the views which have this controller correctly set,
	 * different actions are performed depending on the origin of the action.
	 * @param e ActionEvent which caused the controller to act.
	 * @return None.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		JButton button = (JButton)arg0.getSource();
		
		switch(button.getActionCommand()) {
		case "Unirse al colectivo":
			intentaUnirte();
			break;
		case "Volver":
			vuelveAtras();
			break;
		case "Abandonar colectivo":
			intentaAbandonar();
			vuelveAtras();
			break;
		default:
			break;
		}
	}

	/**
	 * The current logged user tries to enter the collective in the view from which he clicked the button.
	 * @return None.
	 */
	private void intentaUnirte() {
		
		User u = modelo.getLoggedUser();
		Collective col = colectiveView.getCollective();
		
		try {
			if(col.join(u)) {
				JOptionPane.showMessageDialog(colectiveView, "Te has unido al colectivo");
			} else {
				JOptionPane.showMessageDialog(colectiveView, "No puedes unirte a este colectivo.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch(JoinException e) {
			JOptionPane.showMessageDialog(colectiveView, "No puedes unirte a este colectivo.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Changes the view, making invisible all the other views and making visible the PantallaPrincipal view.
	 * @return None.
	 */	
	private void vuelveAtras() {
		frame.setAllInvisible();
		frame.getVistaPantallaPrincipal().update();
		frame.getVistaPantallaPrincipal().setVisible(true);
		frame.pack();
		
	}

	/**
	 * The current logged user tries to abandon the collective of the view from which he clicked the button.
	 * @return None.
	 */
	private void intentaAbandonar() {

		User u = modelo.getLoggedUser();
		Collective col = colectiveView.getCollective();
		
		if(u.exitCollective(col)) {
			JOptionPane.showMessageDialog(colectiveView, "Has abandonado el colectivo");
		} else {
			JOptionPane.showMessageDialog(colectiveView, "No puedes salir de este colectivo.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
