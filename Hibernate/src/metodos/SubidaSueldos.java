package metodos;

import hiberDAO.EmployeesDAO;
import hiberDAO.GenericDAO;
import hiberDAO.InterfaceDAO;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Employees;

public class SubidaSueldos {
	private static Logger log = Logger.getLogger("dblog");

	private InterfaceDAO edao = null;
	private Transaction t = null;
	private Session se = null;

	public SubidaSueldos() {
		edao = new EmployeesDAO();
	}

	private BigDecimal calcularSubida(BigDecimal porcentaje, BigDecimal sueldo) {
		BigDecimal nuevo_sueldo = null;
		nuevo_sueldo = sueldo.multiply(porcentaje);
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
				emp.setSalary(calcularSubida(new BigDecimal(1.2), salary));
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
