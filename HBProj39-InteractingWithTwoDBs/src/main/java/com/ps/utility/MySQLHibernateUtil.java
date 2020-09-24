package com.ps.utility;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MySQLHibernateUtil {

	private static SessionFactory factory;
	
	static {
		Configuration cfg=null;
		try {
			//bootstrap hibernate
			cfg=new Configuration();
			//configure hibernate cfg file
			cfg.configure("com/ps/cfgs/MySQL-hibernate.cfg.xml");
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
	
	public static Session getSession() {
		Session ses=null;
		if(factory!=null) {
			ses=factory.getCurrentSession();
		}
		return ses;
	}
		
	public static void closeSessionFactory() {
		if(factory!=null)
			factory.close();
	}
	
}//class
