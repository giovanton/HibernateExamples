package metodos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
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
		Employees e= null;
		Employees e1 = null;

		try {
			s = ConectaDB.getSesion();
			edao.setSesion(s);
						
			le1.addAll(edao.consultarPor("department_id", 50));
			e = le1.first();
			System.out.println(e.getFirstName() + " " + e.getEmployeeId());

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			ConectaDB.cerrarSesion(s);
		}			
			HashSet<Employees> se = lemp.listarEmpleadosMejorPagados();
			Iterator it = se.iterator();
			while (it.hasNext()) {
				e1 = (Employees)it.next();
				System.out.println(e1.getFirstName()+ " " 
						+ e1.getEmployeeId());
			}
			assertTrue(se.contains(e));
			

	}

}
