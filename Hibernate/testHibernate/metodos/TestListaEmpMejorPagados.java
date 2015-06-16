package metodos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import hiberDAO.DepartmentsDAO;
import hiberDAO.EmployeesDAO;

import org.hibernate.Session;
import org.junit.Test;

import entities.Employees;

public class TestListaEmpMejorPagados {

	@Test
	public void testListarEmpleadosMejorPagados() {
		Session s = null;
		EmployeesDAO edao = new EmployeesDAO();
		ListaEmpleadosMejorPagados lemp = new ListaEmpleadosMejorPagados();
		TreeSet<Employees> le1 = new TreeSet<Employees>();
		try {
			s = ConectaDB.getSesion();
			edao.setSesion(s);
			
			le1.addAll(edao.consultarPor("department_id", 80));
			Employees e = le1.first();
			System.out.println(e.getFirstName() + " " + e.getEmployeeId());
			Set<Employees> se = lemp.listarEmpleadosMejorPagados();
			Iterator it = se.iterator();
			while (it.hasNext()) {
				Employees e1 = (Employees)it.next();
				System.out.println(e1.getFirstName()+ " " 
						+ e1.getEmployeeId());
			}
			assertTrue(se.contains(e));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConectaDB.cerrarSesion(s);
		}
	}

}
