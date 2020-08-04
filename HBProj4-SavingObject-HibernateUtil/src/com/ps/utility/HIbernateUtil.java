package com.ps.utility;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HIbernateUtil {
	private static SessionFactory factory;
	
	static {
		Configuration cfg=null;
		try {
			//create configuration object or Bootstrap Hibernate
			cfg=new Configuration();
			//input hibernate cfg file
			cfg.configure("com/ps/cfgs/Product.cfg.xml");
			//create SessionFactory object
			factory=cfg.buildSessionFactory();
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//static
	
	/*public static Session getSession() {
		if(factory!=null)
			return factory.openSession();
		else
			return null;
	}*/
	
	public static Session getSession() {
		Session session=null;
		if(factory!=null)
			session= factory.openSession();
		return session;
	}//create session
	
	public static void closeSession(Session ses) {
		if(ses!=null)
			ses.close();
	}//close session
	
	public static void closeSessionFactory() {
		if(factory!=null)
			factory.close();
	}//close sessionFactory
}
