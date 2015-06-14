package hiberDAO;

import java.util.List;

import entities.Departments;

public class DepartmentsDAO extends GenericDAO implements InterfaceDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Departments> consultarTodo() {
		List <Departments> ld = sesion.createSQLQuery("SELECT * FROM departments").addEntity(Departments.class).list();
		return ld;
	}

	@Override
	public void modificar(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object leerRegistro(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
