package com.ps.utility;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory factory;
	private static ThreadLocal<Session> threadLocal=new ThreadLocal<>();
	
	static {
		Configuration cfg=null;
		try {
			//bootstrap hibernate
			cfg=new Configuration();
			//configure hibernate cfg file
			cfg.configure("com/ps/cfgs/hibernate.cfg.xml");
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
		//get session object from threadLocal
		ses=threadLocal.get();
		if(ses==null) {
			if(factory!=null) {
				ses=factory.openSession();
				threadLocal.set(ses);
			}
		}
		return ses;
	}
	
	public static void closeSession() {
		Session ses=threadLocal.get();
		if(ses!=null) {
			ses.close();
			threadLocal.remove();
		}
	}
	
	public static void closeSessionFactory() {
		if(factory!=null)
			factory.close();
	}
	
}//class
