package hiberDAO;

import org.hibernate.Session;

public class GenericDAO {

	protected Session sesion = null;

	public GenericDAO() {
	}

	public Session getSesion() {
		return sesion;
	}

	public void setSesion(Session s) {
		this.sesion = s;
		
	}

}