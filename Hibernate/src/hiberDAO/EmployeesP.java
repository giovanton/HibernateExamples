package hiberDAO;

import metodos.ConectaDB;

import org.hibernate.Session;

public class EmployeesP {

	protected Session sesion = null;

	public EmployeesP() {
	}

	public Session getSesion() {
		return sesion;
	}

	public void setSesion(Session s) {
		// TODO Auto-generated method stub
		this.sesion = s;
		
	}

}