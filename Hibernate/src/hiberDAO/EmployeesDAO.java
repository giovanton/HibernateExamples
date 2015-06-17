package hiberDAO;

import java.util.List;

import entities.Employees;

public class EmployeesDAO extends GenericDAO implements InterfaceDAO{
	

	public EmployeesDAO() {
	}
	
	/* (non-Javadoc)
	 * @see hiberDAO.InterfaceDAO#consultarEmpleados()
	 */

	@Override
	public List<Employees> consultarTodo(){ 
		
		/*Método que consulta toda la tabla de empleados
		 * devolviendo una lista de empleados
		 */
		@SuppressWarnings("unchecked")
		List<Employees> les = sesion.createSQLQuery("SELECT * FROM employees").addEntity(Employees.class).list();
		
		return les;
	}

	/* (non-Javadoc)
	 * @see hiberDAO.InterfaceDAO#modificarEmpleados(entities.Employees)
	 */
	@Override
	public void modificar(Object o) {
		/*Método para modificar un registro de la tabla EMPLOYEES
		 * recibiendo un objeto employees. No devuelve nada en este
		 * método.
		 */
		
		Employees e = (Employees)o;
			sesion.merge(e);
	}

	@Override
	public Object leerRegistro(int id) {
		/*Método que recupera un registro de la tabla EMPLOYEES
		 * recibiendo como parámetro la id del empleado y
		 * devolviendo un objeto object
		 */
	
		Employees e = (Employees) sesion.get(Employees.class, id);
		return e;
	}

	public List<Employees> consultarPor(String campo,int id) {
		/* Método que recupera uno o varios registros de la tabla EMPLOYEES
		 * recibiendo un campo por el que realizar la búsqueda y la id
		 * del mismo y devolviendo una lista de empleados
		 */
		@SuppressWarnings("unchecked")
		List <Employees> l = sesion.createSQLQuery("SELECT * FROM employees where " + campo + " = " + id).addEntity(Employees.class).list();
		
		return l;
	}
}
