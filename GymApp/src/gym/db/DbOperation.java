package gym.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
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
			factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * METODA KOJA SLU�I ZA DOBIVANJE VRIJEDNOSTI IZ BAZE. OBAVEZNI PARAMETRI SU
	 * HASH MAPA KOJA KAO KLJUC KOJA KAO VRIJEDNOSTI IMA POLJE KOJE BI TREBALO
	 * SADR�AVATI 2 VRIJEDNOSTI. PRVA VRIJEDNOST TIP RESTRIKCIJE,DRUGA VRIJEDNOST
	 * NAZIV KOLONE. KAO VRIJEDNOST HASH MAPE NALAZI SE VRIJEDNOST NEPOZNATOG TIPA
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
				for (HashMap<String[], ?> object : criteria) {
					for (Map.Entry<String[], ?> entry : object.entrySet()) {
						String tmp[] = entry.getKey();
						if (tmp[0].equals("eq"))
							c.add(Restrictions.eq(tmp[1], entry.getValue()));
						else if (tmp[0].equals("gt"))
							c.add(Restrictions.gt(tmp[1], entry.getValue()));
						else if(tmp[0].equals("le"))
							c.add(Restrictions.le(tmp[1], entry.getValue()));
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
	 * @return true - Uspije�an unos, false - neuspije�an unos
	 */
	public boolean insertValue(Object klasa) {
		Session s = factory.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.saveOrUpdate(klasa);
			s.flush();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		s.close();

		return true;
	}

	/**
	 * METODA ZA DELETE.
	 * 
	 * @param id
	 *            - Primarni ključ
	 * @param klasa
	 *            - Objekt koji se briše/mjenja
	 */
	public boolean deleteByPrimaryKey(int id, Object klasa) {
		boolean status = false;
		Session s = factory.openSession();
		Transaction tx = null;
		try {
			Object k = (Object) s.load(klasa.getClass(), id);
			tx = s.beginTransaction();
			s.delete(k);

			tx.commit();
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			status = false;
		}
		s.close();
		return status;
	}

}
