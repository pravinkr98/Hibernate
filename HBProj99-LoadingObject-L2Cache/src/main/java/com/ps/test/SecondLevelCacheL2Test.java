package com.ps.test;

import org.hibernate.Cache;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ps.entity.InsurancePolicy;
import com.ps.utility.HibernateUtil;

public class SecondLevelCacheL2Test {

	public static void main(String[] args) {
		SessionFactory factory=HibernateUtil.getSessionFactory();
		Session ses=factory.openSession();
		//Session ses=HibernateUtil.getSession();
		InsurancePolicy policy=null;
		
		try {
			//Load object 
			policy=ses.get(InsurancePolicy.class, 9001L);	// gets from DB and keeps L2 and L1 cache
			System.out.println(policy);
			System.out.println("-------------------------------");
			policy=ses.get(InsurancePolicy.class, 9001L);	// gets from L1 cache
			System.out.println(policy);
			ses.evict(policy);	//removes from L1 cache
			System.out.println("--------------------------------");
			policy=ses.get(InsurancePolicy.class, 9001L);	// gets from L2 cache and keeps in L1 cache
			System.out.println(policy);
			System.out.println("--------------------------------");
			ses.clear();	// remove from L1 cache
			try {
				Thread.sleep(20000);	// removes from L2 cache (idle timeout)
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			policy=ses.get(InsurancePolicy.class, 9001L);	// gets from DB and keeps L2 and L1 cache
			System.out.println(policy);
			ses.clear();
			Cache cache=factory.getCache();
			cache.evictAll();
			System.out.println("----------------------------------");
			policy=ses.get(InsurancePolicy.class, 9001L);	// gets from DB and keeps L2 and L1 cache
			System.out.println(policy);
			
		}//try
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//close session 
		HibernateUtil.closeSession(ses);
		//close SessionFactory
		HibernateUtil.closeSessionFactory();
		
	}//main

}//class
