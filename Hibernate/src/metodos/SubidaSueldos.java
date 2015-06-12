package metodos;

import hiberDAO.EmployeesDAO;
import hiberDAO.InterfaceDAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Employees;

public class SubidaSueldos {
	private static Logger log = Logger.getLogger("dblog");
	
	private BigDecimal nuevo_sueldo;
	private EmployeesDAO edao;
	
	public BigDecimal getSueldo() {
		return nuevo_sueldo;
	}
	
	public SubidaSueldos() {
		// TODO Auto-generated constructor stub
		nuevo_sueldo = null;
		edao = new EmployeesDAO();
	}
	
	private BigDecimal calcularSubida(Double porcentaje,BigDecimal sueldo) {
		BigDecimal subida = null;
		nuevo_sueldo = subida.valueOf(sueldo.doubleValue()*porcentaje);
		return nuevo_sueldo;
	}
	
	public void aumentarSueldo() {
		
		Session se = ConectaDB.getSesion();
		edao.setSesion(se);
		Transaction t = se.beginTransaction();
		try {		
		List<Employees> le = edao.consultarTodo();
		Iterator<Employees> it = le.iterator();
		Employees emp = null;
		while (it.hasNext()) {
			emp = it.next();
			//System.out.println(emp.getFirstName() + " " + emp.getSalary());
			BigDecimal salary = emp.getSalary();
			emp.setSalary(calcularSubida(1.2, salary));
			//System.out.println(emp.getSalary());
		}
		t.commit();
		System.out.println("Transacción realizada correctamente");
		} catch (Exception e) {
			t.rollback();
			log.error("Fallo al realizar la operación SubidaSueldos");
			e.printStackTrace();
			throw e;
		} finally {
			ConectaDB.cerrarSesion(se);
			System.out.println("Sesión finalizada.");
		}
	}
	
	

}
