package metodos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class ConectaDB {
	private static SessionFactory factory;
	private static Session sesion;
	
	static {
		Configuration conf = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
		factory = conf.buildSessionFactory(builder.build());
	}
	
	private ConectaDB() {
	}

	public static Session getSesion() {
		sesion = factory.openSession();
		return sesion;
	}
	
	public static void cerrarSesion(Session s) {
		s.close();
	}
}
