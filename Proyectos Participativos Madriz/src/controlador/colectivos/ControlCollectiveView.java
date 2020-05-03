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


public class ControlCollectiveView implements ActionListener {
	private Application modelo;
	private Ventana frame;
	private CollectiveView colectiveView;
	
	public ControlCollectiveView(Application modelo, Ventana frame2) {
		this.modelo = modelo;
		this.frame = frame2;
		this.colectiveView = frame.getCollectiveView();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		JButton button = (JButton)arg0.getSource();
		Collective finalC;
		User u = this.modelo.getLoggedUser();
		
		switch(button.getActionCommand()) {
		case "Unirse al colectivo":
			intentaUnirte();
			break;
		case "Volver":
			vuelveAtras();
			break;
		case "Abandonar colectivo":
			intentaAbandonar();
			break;
		default:
			break;
		}
	}
	
	private void intentaUnirte() {
		System.out.println("Intentando unirte");
		
		User u = modelo.getLoggedUser();
		Collective col = colectiveView.getCollective();
		
		try {
			if(col.join(u)) {
				JOptionPane.showMessageDialog(colectiveView, "¡Te has unido al colectivo!");
			} 
		} catch(JoinException e) {
			JOptionPane.showMessageDialog(colectiveView, "No puedes unirte a este colectivo.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void vuelveAtras() {
		System.out.println("Intentando volver atras");
		

		frame.setAllInvisible();
		frame.getVistaPantallaPrincipal().setVisible(true);
		frame.pack();
		
	}
	
	private void intentaAbandonar() {
		System.out.println("Intentando salirte");
		
	}
}