package dAOS;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Empleado;
import entidades.Employee;
import entidades.Employees;

public class EmpJPADAO implements Recuperable {

	@Override 
	public Object leerEmpleado(int n) {
	
		Employee emp = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RecuperaPorDAOs");
		EntityManager em = emf.createEntityManager();
		try {
			emp = em.find(Employee.class, new Long(n));
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

		return emp;
	}

}
