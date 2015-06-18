package dAOS;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import entidades.Employees;

public class EmpHiberDAO implements Recuperable {
	Session s = null;
	SessionFactory factory = null;

	@Override
	public Employees leerEmpleado(int n) {
		Employees emp = null;
		try {
			Configuration conf = new Configuration().configure();
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
					.applySettings(conf.getProperties());
			factory = conf.buildSessionFactory(builder.build());
			s = factory.openSession();

			emp = (Employees) s.get(Employees.class, n);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			s.close();
			factory.close();
		}
		return emp;
	}

}
