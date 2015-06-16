package metodos;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import hiberDAO.EmployeesDAO;

import org.hibernate.Session;
import org.junit.Test;

import entities.Employees;

public class TestSubidaSueldos {

	@Test
	public void testAumentarSueldo() {
		Session s = null;
	try {
		s = ConectaDB.getSesion();
		EmployeesDAO edao = new EmployeesDAO();
		edao.setSesion(s);
		Employees e1 = (Employees) edao.leerRegistro(159);
		SubidaSueldos su = new SubidaSueldos();
		su.aumentarSueldo();
		Employees e2 = (Employees) edao.leerRegistro(159);
		assertEquals((e1.getSalary()).multiply(new BigDecimal(1.2)), e2.getSalary());
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		ConectaDB.cerrarSesion(s);
	}
	}
}
