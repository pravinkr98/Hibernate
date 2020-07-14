package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ps.entity.Product;

public class SavingObjectTest {

	public static void main(String[] args) {
		Configuration cfg=null;
		SessionFactory factory=null;
		Session ses=null;
		Transaction tx=null;
		Product prod=null;
		boolean flag=false;
		
		//create configuration object
		cfg=new Configuration();
		//map hibernate configuration file with cfg object
		cfg.configure("com/ps/cfgs/Product.cfg.xml");
		//create session factory object
		factory=cfg.buildSessionFactory();
		//open session object
		ses=factory.openSession();
		//add product details in Product object
		prod=new Product();
		prod.setPid(346);
		prod.setPname("Chair");
		prod.setPrice(6500);
		prod.setQty(2);
		//start transcation
		try {
			tx=ses.beginTransaction();
			//save/insert object data(Product details) into database table
			ses.save(prod);
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
