package com.ps.utility;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory factory;
	
	static {
		Configuration cfg=null;
		StandardServiceRegistryBuilder builder=null;	//5.4.18 Final
		//ServiceRegistryBuilder builder=null;	//4.2.2 Final
		ServiceRegistry registry=null;
		try {
			//bootstrap hibernate
			cfg=new Configuration();
			//configure hibernate cfg file
			cfg.configure("com/ps/cfgs/insurancePolicy.cfg.xml");
			cfg.addResource("com/ps/entity/insurancePolicy.hbm.xml");
			//for Annotation Entity class
			//cfg.addAnnotatedClass(InsurancePolicy.class);
			//build ServiceRegistry
			builder=new StandardServiceRegistryBuilder();		//5.4.18 Final
			//builder=new ServiceRegistryBuilder();	//4.2.2 Final
			//create ServiceRegistry
			registry=builder.applySettings(cfg.getProperties()).build();		//5.4.18 Final
			//registry=builder.applySettings(cfg.getProperties()).buildServiceRegistry();	//4.2.2 Final
			//build session factory object
			factory=cfg.buildSessionFactory(registry);			
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
		if(factory!=null)
			ses=factory.openSession();
		return ses;
	}
	
	public static void closeSession(Session ses) {
		if(ses!=null)
			ses.close();
	}
	
	public static void closeSessionFactory() {
		if(factory!=null)
			factory.close();
	}
	
}//class
