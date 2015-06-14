package metodos;

import java.util.List;

import org.hibernate.Session;
import entities.Employees;

public class ConsultaSalariosVentas {
	private static Employees emp = null;
	//private static List<Employees> les = null;
	
	public ConsultaSalariosVentas() {
		// TODO Auto-generated constructor stub
	}
	
	public Employees getEmp() {
		return emp;
	}
	
//	public List<Employees> getLes() {
//		return les;
//	}
	
	@SuppressWarnings("unchecked")
	public static List<Employees> consultaEmpleados(){ //método estático para obtener una lista de los empleados de ventas

		List<Employees> les = null;
		Session sesion = ConectaDB.getSesion();
		try {
			les = sesion.createSQLQuery("SELECT * FROM employees WHERE department_id=80").addEntity(Employees.class).list();
		} catch (Exception e) {
			System.out.println("Fallo al obtener los empleados de ventas");
			e.printStackTrace();
			throw e;
		} finally {
			sesion.close();
		}
		return les;
	}

}
