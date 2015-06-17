package hiberDAO;

import java.util.List;

import entities.Departments;

public class DepartmentsDAO extends GenericDAO implements InterfaceDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Departments> consultarTodo() {
		/*Método para consultar toda la tabla departments
		 * devolviendo una lista de departments
		 */
		List <Departments> ld = sesion.createSQLQuery("SELECT * FROM departments").addEntity(Departments.class).list();
		return ld;
	}

	@Override
	public void modificar(Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object leerRegistro(int id) {
		/*Método para obtener un solo registro de la tabla departments
		 * devolviendo un objeto object
		 */
		Integer i = id;
		Departments dep = (Departments)sesion.get(Departments.class, i.shortValue());
		return dep;
	}

}
