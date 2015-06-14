import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import entities.Countries;
import entities.Regions;


public class MainLazy {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<Countries> paises = null;
		
		Configuration conf = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(conf.getProperties());
		SessionFactory factory = conf.buildSessionFactory(builder.build());
		Session sesion = factory.openSession();
		paises = new HashSet<Countries>();
			try {
			List<Regions> lr = sesion.createSQLQuery("SELECT * FROM regions").addEntity(Regions.class).list();
			Iterator<Regions> it = lr.iterator();
			while (it.hasNext()) {
				Regions r = it.next();
				System.out.println(r.getRegionName());
				paises.addAll(r.getCountrieses());
			}
			
			for (Countries c:paises) {
				System.out.println(c.getCountryName());
			}
			}catch (Exception e) {
				e.printStackTrace();
			} finally 
			{
			sesion.close();
			factory.close();
			}
		}

}
