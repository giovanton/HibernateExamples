import dAOS.EmpHiberDAO;
import dAOS.EmpJDBCDAO;
import dAOS.EmpJPADAO;
import dAOS.Recuperable;
import entidades.Empleado;
import entidades.Employee;
import entidades.Employees;
import servicios.EmpService;


public class MainRecuperaPorDAO {

	public static void main(String[] args) {

		Empleado emp = null;
		Employees emp1 = null;
		Employee emp2 = null;
		EmpService es = new EmpService();
		Recuperable recj = new EmpJDBCDAO();
		Recuperable rech = new EmpHiberDAO();
		Recuperable recjp = new EmpJPADAO();
		
		es.setRecuperable(recj);
		emp = (Empleado)es.leerEmpleado(150);
		System.out.println(emp.getEmployee_id() + " Soy " + emp.getFirst_name() + " " + emp.getLast_name() +
				" y vengo de JDBC.");

		es.setRecuperable(rech);
		emp1 = (Employees)es.leerEmpleado(160);
		System.out.println(emp1.getEmployeeId() + " Soy " + emp1.getFirstName() + " " + emp1.getLastName() +
				" y vengo de Hibernate.");
		
		es.setRecuperable(recjp);
		emp2 = (Employee)es.leerEmpleado(170);
		System.out.println(emp2.getEmployeeId() + " " + emp2.getFirstName() + " " + emp2.getLastName() +
				" y vengo de JPA.");
	}

}
