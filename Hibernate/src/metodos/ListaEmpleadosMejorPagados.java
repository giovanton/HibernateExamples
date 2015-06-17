package metodos;

import hiberDAO.DepartmentsDAO;
import hiberDAO.EmployeesDAO;
import hiberDAO.GenericDAO;
import hiberDAO.InterfaceDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

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
	private Session se2 = null;
	
	public ListaEmpleadosMejorPagados() {
		ddao = new DepartmentsDAO();
		edao = new EmployeesDAO();
		new EmployeesDAO();
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
		} finally {
			ConectaDB.cerrarSesion(se);
		}
		return ld;
	}

	@SuppressWarnings("unchecked")
	public HashSet<Employees> listarEmpleadosMejorPagados() {
		HashSet<Employees> lemp = new HashSet<Employees>();
		try {
		se = ConectaDB.getSesion();
		se2 = ConectaDB.getSesion();
		((GenericDAO) ddao).setSesion(se);
		((GenericDAO) edao).setSesion(se2);
		t = se.beginTransaction();
		List<Departments> ld = listarDepartamentos();
		TreeMap<Integer,Employees> empleadosOrdenados = new TreeMap<Integer,Employees>();
		
		List<Employees> ltemp = new ArrayList<Employees>();
		for (Departments d:ld) {
			
			//System.out.println(d.getDepartmentId() + " " + d.getDepartmentName());
			ltemp = (((EmployeesDAO) edao).consultarPor("department_id",d.getDepartmentId()));
			//TreeSet<Employees> empleadosOrdenados = new TreeSet<Employees>();
			if (!ltemp.isEmpty()) {
			for(Employees e:ltemp) {
				if (ltemp.size() > 1) {
				System.out.println(e.getEmployeeId() + " " + e.getFirstName());
				empleadosOrdenados.put(e.getSalary().intValue(),e);
				//	empleadosOrdenados.addAll(ltemp);
				
				} else if (ltemp.size() == 1) {
					lemp.add(e);
				}
			} 
			
			
			if (!empleadosOrdenados.isEmpty()) {
				/*int indexEO = empleadosOrdenados.lastKey();
				System.out.println(indexEO);*/
				//lemp.add((Employees) empleadosOrdenados.first());
				lemp.add((Employees) empleadosOrdenados.get(empleadosOrdenados.lastKey()));
				System.out.println(empleadosOrdenados.get(empleadosOrdenados.lastKey()).getFirstName());
			empleadosOrdenados.clear();
			} /*else {
				if(lemp.size() == 1)
				{System.out.println("no hay empleados en este departamento");}
			}*/
			
			}
			ltemp.clear();
		}
		t.commit();
		} catch (Exception e) {
			t.rollback();
			log.error("Error al obtener lista de mejor pagados", e);
			throw e;
		} finally {
			ConectaDB.cerrarSesion(se2);
			System.out.println("Sesión finalizada.");
		}	
		return lemp;
	}
}
