package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ps.entity.InsurancePolicy;
import com.ps.utility.HibernateUtil;

public class FirstLevelCache2 {

	public static void main(String[] args) {
		Session ses=null;
		InsurancePolicy policy=null,policy1=null;
		
		//get session object from HibernateUtil class
		ses=HibernateUtil.getSession();
		try {
			//Load object 
			policy=ses.load(InsurancePolicy.class, 9001L);
			System.out.println("1 "+policy);
			System.out.println("------------------------------");
			policy1=ses.load(InsurancePolicy.class, 9001L);
			System.out.println("2 "+policy1);
		}
		catch(HibernateException he) {
			System.out.println("Object not found.");
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}//try
		finally {
			//close session 
			HibernateUtil.closeSession(ses);
			//close SessionFactory
			HibernateUtil.closeSessionFactory();
		}		
		
	}//main

}//class
