package gym.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class DbOperation {

	private static DbOperation instance;
	private SessionFactory factory;

	public static DbOperation Instance() {
		if (instance == null)
			instance = new DbOperation();
		return instance;
	}

	public DbOperation() {
		try {
			factory = new Configuration().configure("loyaltyHibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * METODA KOJA SLUŽI ZA DOBIVANJE VRIJEDNOSTI IZ BAZE. OBAVEZNI PARAMETRI SU
	 * HASH MAPA KOJA KAO KLJUC IMA NAZIV KOLONE RESTRIKCIJE, KAO VRIJEDNOSTI IMA
	 * POLJE KOJE BI TREBALO SADRŽAVATI 2 VRIJEDNOSTI. PRVA VRIJEDNOST TIP
	 * RESTRIKCIJE,DRUGA VRIJEDNOST JE RESTRIKCIJA
	 * 
	 * @param criteria,klasa
	 * @return {@link org.hibernate.mapping.List}
	 */

	@SuppressWarnings("rawtypes")
	public List getValue(HashMap<String, String[]> criteria, Class klasa) {

		List list = null;
		Session s = factory.openSession();
		Transaction tx = null;
		try {
			Criteria c = s.createCriteria(klasa);
			if (!criteria.isEmpty()) {
				for (Map.Entry<String, String[]> entry : criteria.entrySet()) {
					String tmp[] = entry.getValue();
					if (tmp[0].equals("eq"))
						c.add(Restrictions.eq(entry.getKey(), tmp[1]));
				}
			}
			tx = s.beginTransaction();
			list = c.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			s.close();
		}
		return list;
	}

}
