package com.ps.utility;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory factory;
	
	static {
		Configuration cfg=null;
		try {
			//bootstrap hibernate
			cfg=new Configuration();
			//configure hibernate cfg file
			cfg.configure("com/ps/cfgs/insurancePolicy.cfg.xml");
			//build session factory object
			factory=cfg.buildSessionFactory();			
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//static
	
	public static SessionFactory getSessionFactory() {
		if(factory!=null)
			return factory;
		else
			throw new HibernateException("Problem in SessionFactory object creation");
	}
	
	public static Session getSession() {
		Session ses=null;
		if(factory!=null)
			ses=factory.openSession();
		return ses;
	}
	
	//optional while working with Try with Resource
	public static void closeSession(Session ses) {
		if(ses!=null)
			ses.close();
	}
	
	//close while working with Try with Resource
	public static void closeSessionFactory() {
		if(factory!=null)
			factory.close();
	}
	
}//class
