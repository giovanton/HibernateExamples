package metodos;

import hiberDAO.DepartmentsDAO;
import hiberDAO.EmployeesDAO;
import hiberDAO.GenericDAO;
import hiberDAO.InterfaceDAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Departments;
import entities.Employees;

public class SubidaSueldos {
	private static Logger log = Logger.getLogger("dblog");

	private InterfaceDAO edao = null;
	private Transaction t = null;
	private Session se = null;

	public SubidaSueldos() {
		edao = new EmployeesDAO();
	}

	private BigDecimal calcularSubida(Double porcentaje, BigDecimal sueldo) {
		BigDecimal nuevo_sueldo = null;
		nuevo_sueldo = BigDecimal.valueOf(sueldo.doubleValue() * porcentaje);
		return nuevo_sueldo;
	}

	public void aumentarSueldo() {
		try {
			se = ConectaDB.getSesion();
			((GenericDAO) edao).setSesion(se);
			t = se.beginTransaction();

			List<Employees> le = ((EmployeesDAO) edao).consultarPor(
					"department_id", 80);
			Iterator<Employees> it = le.iterator();
			Employees emp = null;
			while (it.hasNext()) {
				emp = it.next();
				// System.out.println(emp.getFirstName() + " " +
				// emp.getSalary());
				BigDecimal salary = emp.getSalary();
				emp.setSalary(calcularSubida(1.2, salary));
				// System.out.println(emp.getSalary());
			}
			t.commit();
			System.out.println("Transacción realizada correctamente");
		} catch (Exception e) {
			t.rollback();
			log.error("Fallo al realizar la operación SubidaSueldos");
			log.error(e);
			e.printStackTrace();
			throw e;
		} finally {
			ConectaDB.cerrarSesion(se);
			System.out.println("Sesión finalizada.");
		}

	}

	

}
