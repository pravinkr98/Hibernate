package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ps.entity.Product;

public class PersistObjectTest {

	public static void main(String[] args) {
		Configuration cfg=null;
		SessionFactory factory=null;
		Session ses=null;
		Transaction tx=null;
		Product prod=null;
		boolean flag=false;
		
		//create configuration object
		cfg=new Configuration();
		System.out.println(cfg.getProperties());
		//map hibernate configuration file with cfg object
		cfg.configure("com/ps/cfgs/Product.cfg.xml");
		System.out.println(cfg.getProperties());
		//create session factory object
		factory=cfg.buildSessionFactory();
		System.out.println("SessionFactory class object name :: "+factory.getClass());
		//open session object
		ses=factory.openSession();
		System.out.println("Session class object name :: "+ses.getClass());
		//add product details in Product object
		prod=new Product();
		prod.setPid(355);
		prod.setPname("Aalmirah");
		prod.setPrice(15500);
		prod.setQty(2);
		//start transaction
		try {
			tx=ses.beginTransaction();
			System.out.println("Transaction class object name :: "+tx.getClass());
			//save/insert object data(Product details) into database table
			ses.persist(prod);
			System.out.println("Id value :: "+prod.getPid());
			flag=true;
		}
		catch(HibernateException he) {
			he.printStackTrace();
			flag=false;
		}
		finally {
			//commit or rollback transaction
			if(flag==true) {
				tx.commit();
				System.out.println("Object is saved successfully.");
			}
			else {
				tx.rollback();
				System.out.println("Object saving is failed.");
			}
			//close session object
			ses.close();
			//close session factory object
			factory.close();
		}
	}//main

}//class
