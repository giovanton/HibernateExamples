import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import entities.Employees;
import metodos.ListaEmpleadosMejorPagados;
import metodos.SubidaSueldos;


public class MainSubidaSueldo {

	public static void main(String[] args) {
	
		/*SubidaSueldos su = new SubidaSueldos();
		su.aumentarSueldo();*/
		
		ListaEmpleadosMejorPagados lemp = new ListaEmpleadosMejorPagados();
		Set<Employees> le = lemp.listarEmpleadosMejorPagados();
		Iterator it = le.iterator();

		while (it.hasNext()) {
			Employees e = (Employees) it.next();
			System.out.println(e.getFirstName() + " " + e.getSalary());
		}
	}

}
