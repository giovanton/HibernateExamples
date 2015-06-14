package metodos;

import hiberDAO.DepartmentsDAO;
import hiberDAO.EmployeesDAO;
import hiberDAO.GenericDAO;
import hiberDAO.InterfaceDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Departments;
import entities.Employees;

public class ListaEmpleadosMejorPagados {
	private static Logger log = Logger.getLogger("dblog");
	private InterfaceDAO ddao = null;
	private InterfaceDAO edao = null;
	private Transaction t = null;
	private Session se = null;
	
	public ListaEmpleadosMejorPagados() {
		ddao = new DepartmentsDAO();
		edao = new EmployeesDAO();
	}
	@SuppressWarnings("unchecked")
	private List<Departments> listarDepartamentos() {
		List<Departments> ld = null;
		try {
			ld = ddao.consultarTodo();
		} catch (Exception e) {
			log.error("Fallo al obtener departamentos", e);
			e.printStackTrace();
			throw e;
		}
		return ld;
	}

	@SuppressWarnings("unchecked")
	public List<Employees> listarEmpleadosMejorPagados() {
		List<Employees> lemp = new ArrayList<Employees>();
		try {
		se = ConectaDB.getSesion();
		((GenericDAO) ddao).setSesion(se);
		t = se.beginTransaction();
		
		for (Departments d : listarDepartamentos()) {
			System.out.println(d.getDepartmentId()+ " " + d.getDepartmentName());
			List<Employees> ltemp = new ArrayList<Employees>();
			TreeMap<Integer, Employees> empleadosOrdenados = 
					new TreeMap<Integer, Employees>();
			ltemp.addAll(d.getEmployeeses());
			for (Employees e : ltemp) {
				if (ltemp.size() >= 1)
				empleadosOrdenados.put(e.getSalary().intValue(), e);

			if (empleadosOrdenados.size() >= 1) {
			lemp.add((Employees) empleadosOrdenados.get(empleadosOrdenados.lastKey()));
			} else {
				lemp.add(empleadosOrdenados.get(0));
			}
		}
		}
		t.commit();
		} catch (Exception e) {
			t.rollback();
			log.error("Error al obtener lista de mejor pagados", e);
			throw e;
		} finally {
			ConectaDB.cerrarSesion(se);
		}	
		return lemp;
	}
}
