package com.ps.test;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ps.entity.InsurancePolicy;
import com.ps.utility.HibernateUtil;

public class SavingObjectTest {

	public static void main(String[] args) {
		
		InsurancePolicy policy=null;		
		SessionFactory factory=HibernateUtil.getSessionFactory();
		Session ses=factory.openSession();
		Transaction tx=null;
		Boolean flag=false;
			
		//java 9 syntax
		try(factory;ses){
			policy=new InsurancePolicy();
			policy.setPolicyName("Rakshak");
			policy.setPolicyType("life");
			//policy.setCompany("LIC");
			policy.setTenure(15);
			
			try {
				//save object
				tx=ses.beginTransaction();
				ses.save(policy);
				flag=true;
			}
			catch(HibernateException he) {
				flag=false;
				he.printStackTrace();
			}
			finally {
				if(flag)
					tx.commit();
				else
					tx.rollback();
			}//finally
		}//try with resource
				
	}//main

}//class
