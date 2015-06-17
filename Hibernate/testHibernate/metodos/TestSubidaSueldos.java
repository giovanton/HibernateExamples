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
		SubidaSueldos su = new SubidaSueldos();
	try {
		s = ConectaDB.getSesion();
		EmployeesDAO edao = new EmployeesDAO();
		edao.setSesion(s);
		Employees e1 = (Employees) edao.leerRegistro(159);
		System.out.println(e1.getSalary());
		ConectaDB.cerrarSesion(s);
		s = ConectaDB.getSesion();
		edao.setSesion(s);
		su.aumentarSueldo();
		Employees e2 = (Employees) edao.leerRegistro(159);
		System.out.println(e2.getSalary());
		assertEquals((e1.getSalary()).multiply(new BigDecimal(1.2)).intValue(), (e2.getSalary()).intValue());
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		ConectaDB.cerrarSesion(s);
	}
	}
}
