import java.util.Iterator;

import entities.Employees;
import metodos.ListaEmpleadosMejorPagados;
import metodos.SubidaSueldos;


public class MainSubidaSueldo {

	public static void main(String[] args) {
	
		/*SubidaSueldos su = new SubidaSueldos();
		su.aumentarSueldo();*/
		
		ListaEmpleadosMejorPagados lemp = new ListaEmpleadosMejorPagados();
		Iterator it = lemp.listarEmpleadosMejorPagados().iterator();

		while (it.hasNext()) {
			Employees e = (Employees) it.next();
			System.out.println(e.getFirstName() + " " + e.getSalary());
		}
	}

}
