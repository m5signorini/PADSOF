package functionalities;
/**
 * 
 */

/**
 * @author
 *
 */

import java.util.*;
import java.io.*;
import entities.individuals.*;
import entities.*;
import projects.*;

public class Application implements Serializable{
	private int minSupports;
	private int maxInactivity;
	
	private List<Project> sentProjects;
	private List<Project> financiatedProjects;
	private List<Project> rejectedProjects;
	private List<Project> publicProjects;
	private List<Project> expiredProjects;
	private List<Project> pendingProjects;
	
	private List<User> registeredUsers;
	private List<User> unregisteredUsers;
	private List<User> bannedUsers;
	
	private List<Collective> collectives;
	
	private SearchEngine searcher;
	private Admin admin;
	private User loggedUser;
	
	public Application() {
		minSupports = 0;
		maxInactivity = 0;
	}
	/* Metodo que crea un usuario como pendiente para que su registro
	 * sea aceptaado por el admin
	 * @param name 	Nombre de usuario
	 * @param nif 	NIF del usuario
	 * @param pwd	Contrase�a del usuario
	 * @return true si se ha podido crear, false si no
	 */
	public boolean register(String name, String nif, String pwd) {
		if(name == null || nif == null || pwd == null) return false;
		/* Controlar nif unico */
		User u = new User(name, pwd, nif);
		unregisteredUsers.add(u);
		return true;
	}
	
	public boolean login(String nif, String pwd) {
		if(nif == null || pwd == null) return false;
		for(User u: registeredUsers) {
			if(u.login(nif, pwd) == true) {
				return true;
			}
		}
		return false;
	}
	
	/* Metodo que usa el usuario estandar para crear un proyecto
	 * conviertiendolo en uno pendiente
	 * @param P Proyecto que se crea
	 * @return Si se ha podido crear true, si no, false
	 */
	public boolean createProject(Project P) {
		if(P == null) return false;
		pendingProjects.add(P);
		return false;
	}
	
	/* Metodo que usa el admin para validar un proyecto pendiente
	 * convirtiendolo en un proyecto publico (se puede votar)
	 * @param P Proyecto que se valida
	 * @return Si se ha podido validar true, si no, false
	 */
	public boolean validateProject(Project P) {
		if(P == null) return false;
		if(pendingProjects.remove(P) != true) {
			return false;
		}
		publicProjects.add(P);
		return true;
	}
	
	/* Metodo que usa el usuario para enviar un proyecto publico
	 * pudiendo no ser posible en caso de que el numero de votos sea incorrecto
	 * @param P Proyecto que se trata de enviar
	 * @return Si se ha podido enviar true, si no, false
	 */
	public boolean sendProject(Project P) {
		if(P == null) return false;
		if(publicProjects.remove(P) != true) {
			return false;
		}
		P.send();
		sentProjects.add(P);
		return true;
	}
	
	/* Metodo que usa el admin para denegar un proyecto pendiente
	 * convirtiendolo en un proyecto rechazado
	 * @param P Proyecto que se deniega
	 * @return Si se ha podido denegar true, si no, false
	 */
	public boolean rejectProject(Project P) {
		if(P == null) return false;
		if(pendingProjects.remove(P) != true) {
			return false;
		}
		rejectedProjects.add(P);
		return true;
	}
	
	/* Metodo que se usa para controlar cuando los proyectos caducan,
	 * se llama al comienzo de la sesion
	 */
	public void updateProjects() {
		
	}
	
	public void logout() {
		
	}
	
	public boolean ban(User u) {
		return false;
	}
	
	public boolean unban(User u) {
		return false;
	}
	
	public boolean validateUser(User u) {
		return false;
	}
	
	public boolean rejectUser(User u) {
		return false;
	}
	
	public boolean notify(User u, Notification n) {
		return false;
	}
	
	public double calcAffinity(Collective c1, Collective c2) {
		return 2.0;
	}
}


