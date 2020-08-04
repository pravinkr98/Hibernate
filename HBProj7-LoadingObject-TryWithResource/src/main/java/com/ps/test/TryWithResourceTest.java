package com.ps.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ps.entity.InsurancePolicy;
import com.ps.utility.HibernateUtil;

public class TryWithResourceTest {

	public static void main(String[] args) {
		InsurancePolicy policy=null;
		
		SessionFactory factory=HibernateUtil.getSessionFactory();
		Session ses=HibernateUtil.getSession();
		
		//java 9 syntax
		try(factory;ses) {
				//Load object 
				policy=ses.get(InsurancePolicy.class, 9001L);
				//verify object
				if(policy==null)
					System.out.println("Record not found");
				else
					System.out.println(policy);		
			}//try
		
		//java 8 syntax
		/*try(SessionFactory factory=HibernateUtil.getSessionFactory();
				Session ses=HibernateUtil.getSession()) {*/		
		
		/*try(SessionFactory factory=HibernateUtil.getSessionFactory()){
				try(Session ses=HibernateUtil.getSession()) {
					//Load object 
					policy=ses.get(InsurancePolicy.class, 90L);
					//verify object
					if(policy==null)
						System.out.println("Record not found");
					else
						System.out.println(policy);		
				}//nested try
		}//try
		*/		
	}//main

}//class
