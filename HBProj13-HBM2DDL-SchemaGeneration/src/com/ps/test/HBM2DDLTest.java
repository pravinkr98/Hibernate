package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.Product;
import com.ps.utility.HibernateUtil;

public class HBM2DDLTest {

	public static void main(String[] args) {
		Session ses=null;
		Transaction tx=null;
		Product prod=null;
		boolean flag=false;
		int idVal=0;
		
		//open session object
		ses=HibernateUtil.getSession();
		//add product details in Product object
		prod=new Product();
		prod.setPid(350);
		prod.setPname("Table");
		prod.setPrice(7500);
		prod.setQty(4.0f);
		//start transcation
		try {
			tx=ses.beginTransaction();
			//save/insert object 
			idVal=(int) ses.save(prod);
			System.out.println("Generated id value :: "+idVal);
			flag=true;
			
			Thread.sleep(40000);
		}
		catch(HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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
			//close object
			HibernateUtil.closeSession(ses);
			HibernateUtil.closeSessionFactory();
		}
	}//main

}//class
