package hiberDAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Employees;

public class EmployeesDAO extends EmployeesP implements InterfaceDAO{
	

	public EmployeesDAO() {
	}
	
	/* (non-Javadoc)
	 * @see hiberDAO.InterfaceDAO#consultarEmpleados()
	 */
	@Override
	public void setSesion(Session s) {
		// TODO Auto-generated method stub
		super.setSesion(s);
	}
	
	@Override
	public List<Employees> consultarTodo(){ 
		
	
		List<Employees> les = sesion.createSQLQuery("SELECT * FROM employees WHERE department_id=80").addEntity(Employees.class).list();
		
		return les;
	}

	/* (non-Javadoc)
	 * @see hiberDAO.InterfaceDAO#modificarEmpleados(entities.Employees)
	 */
	@Override
	public void modificar(Employees e) {

			sesion.merge(e);
	}
}
