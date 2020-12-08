package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.InsurancePolicy;
import com.ps.utility.HibernateUtil;

public class PessimisticLockingTest2 {

	public static void main(String[] args) {
		
		Session ses=HibernateUtil.getSession();
		InsurancePolicy policy=null;
		Transaction tx=null;
		boolean updated=false;
		
		try {
			tx=ses.beginTransaction();
			//Load object 
			policy=ses.get(InsurancePolicy.class, 9001L, LockMode.UPGRADE_NOWAIT);	
			System.out.println(policy);
			policy.setTenure(22);
			updated=true;
			
		}//try
		catch(HibernateException he) {
			updated=false;
			he.printStackTrace();
		}
		catch(Exception e) {
			updated=false;
			e.printStackTrace();
		}
		finally {
			//perform tx mgmt
			if(updated) {
				tx.commit();
				System.out.println("Object is updated");
			}
			else {
				tx.rollback();
				System.out.println("Problem in updating object");
			}
		}//finally
		
		//close session 
		HibernateUtil.closeSession(ses);
		//close SessionFactory
		HibernateUtil.closeSessionFactory();
		
	}//main

}//class
