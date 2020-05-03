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


public class ControlCollectiveView implements ActionListener {
	private Application modelo;
	private CreateCollectiveView collectiveView;
	private Ventana frame;
	
	public ControlCollectiveView(Application modelo, Ventana frame2) {
		this.modelo = modelo;
		this.frame = frame2;
		this.collectiveView = frame2.getCreateCollectiveView();
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
	}
	
	private void vuelveAtras() {
		System.out.println("Intentando volver atras");
		
	}
	
	private void intentaAbandonar() {
		System.out.println("Intentando salirte");
		
	}
}
