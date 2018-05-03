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
	 * HASH MAPA KOJA KAO KLJUC KOJA KAO VRIJEDNOSTI IMA
	 * POLJE KOJE BI TREBALO SADRŽAVATI 2 VRIJEDNOSTI. PRVA VRIJEDNOST TIP
	 * RESTRIKCIJE,DRUGA VRIJEDNOST NAZIV KOLONE. KAO VRIJEDNOST HASH MAPE NALAZI SE VRIJEDNOST NEPOZNATOG TIPA
	 * 
	 * @param criteria,klasa
	 * @return {@link org.hibernate.mapping.List}
	 */

	@SuppressWarnings("rawtypes")
	public List getValue(List<HashMap<String[], ?>> criteria, Class klasa) {

		List list = null;
		Session s = factory.openSession();
		Transaction tx = null;
		try {
			Criteria c = s.createCriteria(klasa);
			if (!criteria.isEmpty()) {				
				for (HashMap<String[],?> object : criteria) {
					for (Map.Entry<String[], ?> entry : object.entrySet()) {
						String tmp[] = entry.getKey();
						if (tmp[0].equals("eq"))
							c.add(Restrictions.eq(tmp[1],entry.getValue()));
						else if (tmp[0].equals("gt")) 
							c.add(Restrictions.gt(tmp[1],entry.getValue()));						
					}
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
	
	/**
	 * 
	 * @return true - Uspiješan unos, false - neuspiješan unos
	 * */
	public boolean insertValue(Dolasci dolasci) {
		Session s = factory.openSession();
		Transaction tx = null;
		tx=s.beginTransaction();
		s.save(dolasci);
		s.flush();
		tx.commit();		
		return true;
	}
	


}
