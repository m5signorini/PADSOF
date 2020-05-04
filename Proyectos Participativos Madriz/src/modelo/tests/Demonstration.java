/**
 * 
 */
package modelo.tests;

import java.util.*;
import java.io.*;

import es.uam.eps.sadp.grants.CCGG;
import modelo.entities.*;
import modelo.entities.individuals.*;
import modelo.fechasimulada.*;
import modelo.functionalities.*;
import modelo.projects.*;
/**
 * Class that represents a demo of the application.
 * The main method starts an application, demonstrating its
 * use and printing information as well.
 * @author Martin Sanchez Signorini
 */
public class Demonstration {
	
	public static void main(String[] args) throws Exception{
		/* First we create a new Application with a custom made administrator
		 * and initial values (minSupports and maxInactivity can be later changed)
		 */
		Admin admin = new Admin("admin");
		Application app = Application.getApplication();
		
		app.setAdmin(admin);
		app.setMaxInactivity(2);
		app.setMinSupports(2);
		
		User firstUser = new User("Bob Dylan", "24051941", "0000000A");
		
		/* Register first user */
		if(app.register(firstUser) == false) {
			System.out.println("Error: Registration");
		}
		/* Login as administrator */
		if(app.loginAdmin("admin") == false) {
			System.out.println("Error: Admin login");
		}
		/* Accept registration */
		if(app.validateUser(firstUser) == false) {
			System.out.println("Error: Validation");
		}
		/* Logout */
		app.logout();
		/* Login as user */
		if(app.login("0000000A", "24051941") == false) {
			System.out.println("Error: User login");
		}
		
		/* PROJECT CREATION AND VALIDATION */
		ArrayList<District> aff = new ArrayList<District>();
		aff.add(new District("Barajas"));
		Social sProj = new Social("TITLE", "DESCRIPTION", 1000.666, new Date(), app.getLoggedUser(), ScopeType.national, "GRUPO", "pic");
		Infrastructural iProj = new Infrastructural("TITLE", "DESCRIPTION", 1000.666, new Date(), app.getLoggedUser(), aff, "scheme", "LOCATION" );
		if(app.createProject(sProj) == false) {
			System.out.println("Error: Project Creation");
		}
		if(app.createProject(iProj) == false) {
			System.out.println("Error: Project Creation");
		}
		System.out.print(app.toString()+"\n");
		app.logout();
		app.loginAdmin("admin");
		if(app.validateProject(sProj) == false) {
			System.out.println("Error: Project Validation");
		}
		if(app.validateProject(iProj) == false) {
			System.out.println("Error: Project Validation");
		}
		System.out.print(app.toString() + "\n");
		app.logout();
		
		/* COLLECTIVE */
		app.login("0000000A", "24051941");
		Collective c1 = new Collective("Deporte", "Para los amantes del deporte", app.getLoggedUser());
		Collective c2 = new Collective("Futbol", "Soccer para los ingleses", app.getLoggedUser(), c1);
		app.createCollective(c1);
		app.createCollective(c2);
		app.logout();
		
		/* ADD MEMBER */
		User secondUser = new User("Jesus", "a", "a");
		app.register(secondUser);
		app.loginAdmin("admin");
		app.validateUser(secondUser);
		app.logout();
		app.login("a", "a");
		if(c1.join(secondUser) == false) {
			System.out.println("Error: Joining Collective");
		}
		app.logout();
		
		/* VOTING */
		app.login("0000000A", "24051941");
		sProj.support(app.getLoggedUser());
		iProj.support(c1);
		int voteCount = iProj.countVotes();
		iProj.support(app.getLoggedUser());
		if(voteCount != iProj.countVotes()) {
			System.out.println("Error: Vote Counting");
		}
		
		/* AFFINITY */
		c2 = new Collective("Tecnologia", "Robots y demas", app.getLoggedUser());
		sProj = new Social("Proyecto C1", "Desc", 10.0, new Date(), c1, ScopeType.international, "", "");
		app.createProject(sProj);
		sProj.support(c2);
		sProj = new Social("Proyecto C2", "Desc", 10.0, new Date(), c2, ScopeType.international, "", "");
		app.createProject(sProj);
		sProj.support(c1);
		// Expected 1
		System.out.println("Affinity between c1 and c2: " + app.calcAffinity(c1, c2) + "\n");
		app.logout();
		
		/* PROJECT LIFE */
		app.login("a", "a");
		sProj = new Social("Proyecto C1", "Desc", 10000.0, new Date(), app.getLoggedUser(), ScopeType.international, "", "");
		app.createProject(sProj);
		//
		app.logout();
		app.loginAdmin("admin");
		app.setMinSupports(1);
		app.validateProject(sProj);
		app.logout();
		System.out.print(app.toString()+"\n");
		//
		app.login("a", "a");
		if(app.sendProject(sProj) == false) {
			System.out.println("Error: Sending Project");
		}
		app.logout();
		System.out.print(app.toString()+"\n");
		//
		CCGG gateway = CCGG.getGateway();
		FechaSimulada.restablecerHoyReal();
		FechaSimulada.avanzar(10);
		gateway.setDate(FechaSimulada.getHoy());
		//
		app.login("a", "a");
		System.out.print(app.toString()+"\n");
		
		/* FILE WRITING AND READING */
		if(app.writeToFile("data")) {
			System.out.println("The application was succesfully written to a file");
		}
		app = null;
		app = Application.readFromFile("data");
		System.out.print(app.toString()+"\n");
	}

}
