package com.ps.utility;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.ps.entity.Project;

public class HibernateUtil {

	private static SessionFactory factory;
	
	static {
		Configuration cfg=null;
		StandardServiceRegistryBuilder builder=null;	
		ServiceRegistry registry=null;
		try {
			//bootstrap hibernate
			cfg=new Configuration();
			//configure hibernate cfg file
			cfg.configure("com/ps/cfgs/hibernate.cfg.xml");
			cfg.addAnnotatedClass(Project.class);
			//build ServiceRegistry
			builder=new StandardServiceRegistryBuilder();	
			//create ServiceRegistry
			registry=builder.applySettings(cfg.getProperties()).build();		
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
			ses=factory.getCurrentSession();
		return ses;
	}
	
	public static void closeSessionFactory() {
		if(factory!=null)
			factory.close();
	}
	
}//class
