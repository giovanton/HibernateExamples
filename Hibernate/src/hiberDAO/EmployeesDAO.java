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
		
		
		@SuppressWarnings("unchecked")
		List<Employees> les = sesion.createSQLQuery("SELECT * FROM employees").addEntity(Employees.class).list();
		
		return les;
	}

	/* (non-Javadoc)
	 * @see hiberDAO.InterfaceDAO#modificarEmpleados(entities.Employees)
	 */
	@Override
	public void modificar(Object o) {
		
		Employees e = (Employees)o;
			sesion.merge(e);
	}

	@Override
	public Object leerRegistro(int id) {
	
		Employees e = (Employees) sesion.get(Employees.class, id);
		return e;
	}

	public List<Employees> consultarPor(String campo,int id) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List <Employees> l = sesion.createSQLQuery("SELECT * FROM employees where " + campo + " = " + id).addEntity(Employees.class).list();
		
		return l;
	}
}
