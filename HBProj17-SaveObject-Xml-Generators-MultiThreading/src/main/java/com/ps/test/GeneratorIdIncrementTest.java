package com.ps.test;

import org.hibernate.Session;

import com.ps.entity.Product;
import com.ps.utility.HibernateUtil;

public class GeneratorIdIncrementTest {

	public static void main(String[] args) throws InterruptedException {
		Session ses1=null,ses2=null,ses3=null;
		Product prod=null,prod1=null,prod2=null;
		MyRequest req1=null,req2=null,req3=null;
		Thread t1=null,t2=null,t3=null;
				
		//get session object from HibernateUtil factory class
		ses1=HibernateUtil.getSession();
		ses2=HibernateUtil.getSession();
		ses3=HibernateUtil.getSession();
		
		//add product details in Product object
		prod=new Product();
		//prod.setPid(404);
		prod.setPname("Almirah");
		prod.setPrice(12000.0f);
		prod.setQty(5.0f);
		
		//product2 obj
		prod1=new Product();
		//prod1.setPid(405);
		prod1.setPname("Box");
		prod1.setPrice(9000.0f);
		prod1.setQty(3.0f);

		//product3 obj
		prod2=new Product();
		//prod2.setPid(406);
		prod2.setPname("BedBox");
		prod2.setPrice(19000.0f);
		prod2.setQty(2.0f);
		
		req1=new MyRequest(ses1, prod);
		req2=new MyRequest(ses2, prod1);
		req3=new MyRequest(ses3, prod2);
		t1=new Thread(req1);	t1.setName("T1");		
		t2=new Thread(req2);	t2.setName("T2");		
		t3=new Thread(req3);	t3.setName("T3");
		t1.start();		t2.start();		t3.start();
		
		Thread.sleep(6000);
		//close session object
		HibernateUtil.closeSession(ses1);
		HibernateUtil.closeSession(ses2);
		HibernateUtil.closeSession(ses3);
		//close session factory object
		HibernateUtil.closeSessionFactory();
		
	}//main

}//class
