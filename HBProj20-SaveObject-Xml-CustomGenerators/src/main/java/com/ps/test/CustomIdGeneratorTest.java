package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.Product;
import com.ps.utility.HibernateUtil;

public class CustomIdGeneratorTest {

	public static void main(String[] args) {
		Session ses=null;
		Transaction tx=null;
		Product prod=null,prod1=null;
		boolean flag=false;
		Long idVal=0L,idVal1=0L;
		
		//get session object from HibernateUtil factory class
		ses=HibernateUtil.getSession();
		//add product details in Product object
		prod=new Product();
		prod1=new Product();
		//prod.setPid(402L);
		prod.setPname("Almirah2");
		prod.setPrice(12000.0f);
		prod.setQty(5.0f);
		//product2 obj
		//prod1.setPid(201L);
		prod1.setPname("Box2");
		prod1.setPrice(9000.0f);
		prod1.setQty(3.0f);
		//start transcation
		try {
			tx=ses.beginTransaction();
			//save object
			idVal=(Long) ses.save(prod);
			idVal1=(Long) ses.save(prod1);
			System.out.println("Generated id value :: "+idVal+"   "+idVal1);
			flag=true;
			System.out.println("id value :: "+prod.getPid()+"   "+prod1.getPid());
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
