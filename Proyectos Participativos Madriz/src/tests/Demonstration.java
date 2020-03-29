/**
 * 
 */
package tests;

import java.util.*;
import java.io.*;
import functionalities.*;
import entities.*;
import entities.individuals.*;
import projects.*;
/**
 * @author mssig
 *
 */
public class Demonstration {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* First we create a new Application with a custom made administrator
		 * and initial values (minSupports and maxInactivity can be later changed)
		 */
		Admin admin = new Admin("admin","admin");
		Application app = new Application(admin, 2, 2);
		User firstUser = new User("Bob Dylan", "0000000A", "24051941");
		
		/* Register first user */
		if(app.register(firstUser) == false) {
			System.out.println("Error: Registration");
		}
		/* Login as administrator */
		if(app.login("admin", "admin") == false) {
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
		Social sProj = new Social("TITLE", "DESCRIPTION", 1000.666, new Date(), app.getLoggedUser(), ScopeType.national, "GRUPO", "pic");
		Infrastructural iProj = new Infrastructural("TITLE", "DESCRIPTION", 1000.666, new Date(), app.getLoggedUser(), "scheme", "LOCATION");
		if(app.createProject(sProj) == false) {
			System.out.println("Error: Project Creation");
		}
		if(app.createProject(iProj) == false) {
			System.out.println("Error: Project Creation");
		}
		System.out.print(app.toString());
		app.logout();
		app.login("admin", "admin");
		if(app.validateProject(sProj) == false) {
			System.out.println("Error: Project Validation");
		}
		if(app.validateProject(iProj) == false) {
			System.out.println("Error: Project Validation");
		}
		System.out.print(app.toString());
		app.logout();
		
		/* VOTING */
		app.login("0000000A", "24051941");
		sProj.support(app.getLoggedUser());
		
		/* Registrar usuario 	*/
		/* Entrar como admin 	*/
		/* Aceptar registro  	*/
		/* Salir de admin    	*/
		/* Iniciar como usuario */
		/* Crear proyecto 		*/
		/* Crear colectivo 		*/
		/* Salir del todo		*/
		/* Revivir aplicacion	*/
		/* Controlar correccion	*/
	}

}
