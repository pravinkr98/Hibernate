package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ps.entity.Product;
import com.ps.utility.HIbernateUtil;

public class SavingObjectTest {

	public static void main(String[] args) {
		Session ses=null;
		Transaction tx=null;
		Product prod=null;
		boolean flag=false;
		int idVal=0;
		
		//get session object from HibernateUtil factory class
		ses=HIbernateUtil.getSession();
		System.out.println("Session class object name :: "+ses.getClass());
		//add product details in Product object
		prod=new Product();
		prod.setPid(356);
		prod.setPname("Bed");
		//prod.setPrice(7500);
		//prod.setQty(4);
		//start transcation
		try {
			tx=ses.beginTransaction();
			System.out.println("Transaction class object name :: "+tx.getClass());
			//save/insert object data(Product details) into database table
			ses.save(prod);
			//idVal=(int) ses.save(prod);
			//System.out.println("Generated id value :: "+idVal);
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
			HIbernateUtil.closeSession(ses);
			//close session factory object
			HIbernateUtil.closeSessionFactory();
		}
	}//main

}//class
