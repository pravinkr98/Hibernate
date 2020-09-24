package com.ps.utility;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory factory;
	
	static {
		Configuration cfg=null;
		InputStream is=null;
		Properties props=null;
		try {
			//load and read Properties file
			is=new FileInputStream("src/main/java/com/ps/commons/myFile.properties");
			//put properties file content to java.util.Properties object
			props=new Properties();
			props.load(is);
			//boot strap hibernate
			cfg=new Configuration();
			cfg.setProperties(props);
			//specify the name of the mapping file
			cfg.addFile("src/main/java/com/ps/entity/insurancePolicy.hbm.xml");
			//cfg.addAnnotatedClass(InsurancePolicy.class);
			//build session factory object
			factory=cfg.buildSessionFactory();			
		}//try
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
