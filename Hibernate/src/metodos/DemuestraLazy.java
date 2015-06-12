package metodos;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cache.spi.Region;

import entities.Countries;
import entities.Regions;

public class DemuestraLazy {
	Set<Countries> paises = null;
	
	public DemuestraLazy() {
		// TODO Auto-generated constructor stub
	}
	
	public void mostrarPaises() {
		
		Session s = ConectaDB.getSesion();
		try {
		Transaction t = s.beginTransaction();
		List<Regions> lr = s.createSQLQuery("SELECT * FROM regions").addEntity(Regions.class).list();
		Iterator<Regions> it = lr.iterator();
		while (it.hasNext()) {
			paises = it.next().getCountrieses();
		}
		
		for (Countries c:paises) {
			System.out.println(c.getCountryName());
		}
		}catch (Exception e) {
			e.printStackTrace();
		} finally 
		{
		s.close();
		}
	}

}
