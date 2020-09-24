package com.ps.test;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.InsurancePolicy;
import com.ps.utility.HibernateUtil;

public class LoadingObjectRegistryTest {

	public static void main(String[] args) {
		Session ses=null;
		InsurancePolicy policy=null;
		Transaction tx=null;
		boolean updated=false;
		
		//get session object from HibernateUtil class
		ses=HibernateUtil.getSession();
		try {
			//Load object 
			policy=ses.get(InsurancePolicy.class, 9001L);
			//verify object
			if(policy==null)
				System.out.println("Record not found");
			else {
				System.out.println("Record found and displayed");
				System.out.println(policy);
				System.out.println("-------------------------------------");
				tx=ses.beginTransaction();
				policy.setTenure(13);
				ses.update(policy);
				System.out.println(policy);
				System.out.println("----------------------------------------");
				policy.setTenure(17);
				policy.setPolicyType("home");
				ses.update(policy);
				System.out.println(policy);
				updated=true;
			}
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}//try
		finally {
			//Commit or Rollback Tx
			if(updated) {
				tx.commit();
				System.out.println("Object updated");
			}
			else {
				tx.rollback();
				System.out.println("Object updation failed");
			}
			//close session 
			HibernateUtil.closeSession(ses);
			//close SessionFactory
			HibernateUtil.closeSessionFactory();
		}		
		
	}//main

}//class
