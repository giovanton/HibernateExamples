package metodos;

import static org.junit.Assert.*;

import java.util.List;

import hiberDAO.DepartmentsDAO;
import hiberDAO.EmployeesDAO;

import org.hibernate.Session;
import org.junit.Test;

import entities.Departments;
import entities.Employees;

public class TestListaXDepartamento {

	@Test
	public void testObtenerListaPorDepartamento() {
		EmployeesDAO edao = new EmployeesDAO();
		DepartmentsDAO ddao = new DepartmentsDAO();
		ListaEmpleadosXDepartamento ledep = new ListaEmpleadosXDepartamento();
		Session s = null;
		Session s1 = null;
		try {
			s = ConectaDB.getSesion();
			s1 = ConectaDB.getSesion();
			edao.setSesion(s);
			ddao.setSesion(s1);
			
			List<Employees> le1 = edao.consultarPor("department_id", 80);
			Departments d = (Departments) ddao.leerRegistro(80);
			System.out.println(d.getDepartmentName());
			List<Employees> le2 = ledep.obtenerListaPorDepartamento(d);
			int cuentaEmp = 0;
			for (Employees e:le2) {
				for (Employees e1:le1) {
					if (e1.getEmployeeId() == e.getEmployeeId()) {
						 cuentaEmp++;
					}
				}
			}
			assertEquals(le2.size(),cuentaEmp);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConectaDB.cerrarSesion(s);
			ConectaDB.cerrarSesion(s1);
		}
	}

}
