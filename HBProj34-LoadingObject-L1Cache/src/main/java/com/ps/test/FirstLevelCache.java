package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ps.entity.InsurancePolicy;
import com.ps.utility.HibernateUtil;

public class FirstLevelCache {

	public static void main(String[] args) {
		Session ses=null;
		InsurancePolicy policy=null,policy1=null;
		
		//get session object from HibernateUtil class
		ses=HibernateUtil.getSession();
		try {
			//Load object 
			policy=ses.get(InsurancePolicy.class, 9001L);
			System.out.println("1"+policy);
			System.out.println("------------------------------");
			policy1=ses.get(InsurancePolicy.class, 9001L);
			System.out.println("2"+policy1);
			System.out.println("-------------------------------");
			ses.evict(policy); 	//removes from L1 cache
			policy1=ses.get(InsurancePolicy.class, 9001L);
			System.out.println("3"+policy1);
			System.out.println("-------------------------------");
			policy1=ses.get(InsurancePolicy.class, 9001L);
			System.out.println("4"+policy);
			System.out.println("-------------------------------");
			ses.clear(); 	//removes all object from L1 cache
			policy1=ses.get(InsurancePolicy.class, 9001L);
			System.out.println("5"+policy);			
		}
		catch(HibernateException he) {
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
