package gym.db;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class DbOperation {
	
	public DbOperation() {
		try {
			factory = new Configuration().configure("loyaltyHibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

	private SessionFactory factory;

	public List getValue(String kor_ime) {

		List<Korisnik> list = null;
		Session s = factory.openSession();
		Transaction tx = null;
		try {
			Criteria c = s.createCriteria(Korisnik.class);
				c.add(Restrictions.eq("kor_ime",kor_ime));
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
