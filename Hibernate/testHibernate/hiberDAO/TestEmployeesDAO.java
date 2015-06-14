package hiberDAO;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import metodos.ConectaDB;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import entities.Employees;

public class TestEmployeesDAO {

	@Test
	public void testSetSesion() {
		Session s = ConectaDB.getSesion();
		EmployeesDAO edao = new EmployeesDAO();
		edao.setSesion(s);
		assertEquals(s, edao.getSesion());
		s.close();
	}

	@Test
	public void testConsultarTodo() {
		Session s = ConectaDB.getSesion();
		EmployeesDAO edao = new EmployeesDAO();
		edao.setSesion(s);
		Transaction t = s.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Employees> l = edao.getSesion().createSQLQuery("Select * From employees where department_id = 80").addEntity(Employees.class).list();
		assertEquals(l, edao.consultarTodo());
		t.commit();
		s.close();
	}

	@Test
	public void testModificar() {
		Session s = ConectaDB.getSesion();
		EmployeesDAO edao = new EmployeesDAO();
		edao.setSesion(s);
		Transaction t = s.beginTransaction();
		Employees e = new Employees();
		e = (Employees) s.get(Employees.class, 179);
		e.setSalary(new BigDecimal(100001));
		edao.modificar(e);
		t.commit();
		Employees e1 = (Employees)s.get(Employees.class, 179);
		assertEquals(new BigDecimal(100001), e1.getSalary());
		s.close();
	}

	@Test
	public void testGetSesion() {
		Session s = ConectaDB.getSesion();
		EmployeesDAO edao = new EmployeesDAO();
		edao.setSesion(s);
		assertNotNull(edao.getSesion());
		s.close();
	}

}
