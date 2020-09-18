package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ps.entity.InsurancePolicy;
import com.ps.utility.HibernateUtil;

public class LoadingObjectTest1 {

	public static void main(String[] args) {
		Session ses=null;
		InsurancePolicy policy=null;
		
		//get session object from HibernateUtil class
		ses=HibernateUtil.getSession();
		try {
			//Load object 
			policy=ses.load(InsurancePolicy.class, 9001L);
			System.out.println(policy.getClass()+"  "+policy.getClass().getSuperclass());
			//verify object
			System.out.println("Record found and displayed");
			System.out.println(policy);
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}//try
		
		//close session 
		HibernateUtil.closeSession(ses);
		//close SessionFactory
		HibernateUtil.closeSessionFactory();
		
	}//main

}//class
