package metodos;

import java.util.List;

import hiberDAO.EmployeesDAO;
import hiberDAO.GenericDAO;
import hiberDAO.InterfaceDAO;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Departments;
import entities.Employees;

public class ListaEmpleadosXDepartamento {
	private static Logger log = Logger.getLogger("dblog");
	private InterfaceDAO edao = null;
	private Transaction t = null;
	private Session se = null;
	
	public ListaEmpleadosXDepartamento() {
		edao = new EmployeesDAO();
	}
	
	public List<Employees> obtenerListaPorDepartamento(Departments d){
		List<Employees> ledep = null;
		try {
			se = ConectaDB.getSesion();
			((GenericDAO) edao).setSesion(se);
			t = se.beginTransaction();
			System.out.println(d.getDepartmentId());
		    ledep = ((EmployeesDAO) edao).consultarPor("department_id",d.getDepartmentId());
		    t.commit();
		} catch (Exception e) {
			log.error("Fallo al obtener la lista de empleados por departamento",e);
			e.printStackTrace();
			t.rollback();
			throw e;
		} finally {
			ConectaDB.cerrarSesion(se);
		}
		return ledep;
	}
}
