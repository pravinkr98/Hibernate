package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.Product;
import com.ps.utility.HibernateUtil;

public class GeneratorIdTest {

	public static void main(String[] args) {
		Session ses=null;
		Transaction tx=null;
		Product prod=null;
		boolean flag=false;
		String idVal=null;
		
		//get session object from HibernateUtil factory class
		ses=HibernateUtil.getSession();
		//add product details in Product object
		prod=new Product();
		prod.setPname("Almirah");
		prod.setPrice(12000.0f);
		prod.setQty(5.0f);
		
		//begin Tx
		try {
			tx=ses.beginTransaction();
			//save object
			idVal= (String) ses.save(prod);
			System.out.println("Generated id value :: "+idVal);
			flag=true;
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//commit or rollback Tx
			if(flag) {
				tx.commit();
				System.out.println("Object is saved successfully.");
			}
			else {
				tx.rollback();
				System.out.println("Object saving is failed.");
			}
			//close session object
			HibernateUtil.closeSession(ses);
			//close session factory object
			HibernateUtil.closeSessionFactory();
		}
	}//main

}//class
