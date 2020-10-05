package com.ps.test;

import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.utility.HibernateUtil;

public class HQLSelectTest_Scalar {

	public static void main(String[] args) {
		Session ses=null;
		
		//get session
		ses=HibernateUtil.getSession();		
		/*try {
			//begin dummy Tx
			Transaction tx=ses.beginTransaction();
			//prepare query
			Query query=ses.createQuery("SELECT policyId,policyName,policyType,tenure FROM com.ps.entity.InsurancePolicy WHERE company LIKE :initLetter");
			//set parameter
			query.setParameter("initLetter", "L%");
			//execute query
			List<Object[]> list=query.getResultList();
			//process the result
			list.forEach(row->{
				for(Object policy:row) {
					System.out.print(policy+" ");
				}
				System.out.println();
			});			
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close factory
			HibernateUtil.closeSessionFactory();
		}*/
		
		// ================ HQL SELECT SCALAR Query that single specific col value ===========
		/*try {
			Transaction tx=ses.beginTransaction(); //dummy
			//preprare Query object
			Query query=ses.createQuery("SELECT policyId FROM com.ps.entity.InsurancePolicy WHERE tenure<=:max");
			//set query param value
			query.setParameter("max", 15);
			//execute query
			List<Long> list=query.getResultList();
			//process result
			list.forEach(System.out::println);
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close session factory
			HibernateUtil.closeSessionFactory();
		}*/
		
		// ================ HQL SELECT SCALAR Query that single specific col value using Iterate() ===========
		try {
			Transaction tx=ses.beginTransaction(); //dummy
			//preprare Query object
			Query query=ses.createQuery("SELECT policyId FROM com.ps.entity.InsurancePolicy WHERE tenure<=:max");
			//set query param value
			query.setParameter("max", 17);
			//execute query
			Iterator<Long> it=query.iterate();
			//process result
			while(it.hasNext()) {
				System.out.println(it.next());
			}
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close session factory
			HibernateUtil.closeSessionFactory();
		}
	}//main
}//class