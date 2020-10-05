package com.ps.test;

import java.util.Optional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.utility.HibernateUtil;

public class HQLSelectTestSingleRecord {

	public static void main(String[] args) {
		Session ses = null;

		// get session object
		ses = HibernateUtil.getSession();
		
		//================HQL Select Entity query that gives single record =================== 
		/*try {
			//begin Tx
			Transaction tx=ses.beginTransaction();	//dummy Tx
			//prepare query
			Query query=ses.createQuery("FROM com.ps.entity.InsurancePolicy WHERE policyId=:id");
			//set query params
			query.setParameter("id", 9001L);
			//execute query
			List<InsurancePolicy> list=query.getResultList();
			//process the result
			System.out.println("Size of record in list :: "+list.size());
			if(!list.isEmpty()) {
				System.out.println(list.get(0));
			}
			else {
				System.out.println("record not found");
			}
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close sessionFactory
			HibernateUtil.closeSessionFactory();
		}*/
		
		try {
			//begin Tx
			Transaction tx=ses.beginTransaction();	//dummy Tx
			//prepare query
			Query query=ses.createQuery("FROM com.ps.entity.InsurancePolicy WHERE policyId=:id");
			//set query params
			query.setParameter("id", 9001L);
			//execute query
			/*InsurancePolicy policy=(InsurancePolicy) query.uniqueResult();
			//process the result
			if(policy!=null) {
				System.out.println(policy);
			}
			else {
				System.out.println("record not found");
			}*/
			
			//execute query
			Optional opt=query.uniqueResultOptional();
			//process the result
			if(opt.isPresent()) {
				System.out.println(opt.get());
			}
			else
				System.out.println("record not found");
			
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close sessionFactory
			HibernateUtil.closeSessionFactory();
		}
		
		//================HQL Select Scalar query that gives (multiple column) single record =================== 
		/*try {
			//begin Tx
			Transaction tx=ses.beginTransaction();	//dummy Tx
			//prepare query
			Query query=ses.createQuery("SELECT policyId,policyName,tenure FROM com.ps.entity.InsurancePolicy WHERE policyId=:id");
			//set query params
			query.setParameter("id", 9001L);
			//execute query
			List<Object[]> list=query.getResultList();
			//process the result
			System.out.println("Size of record in list :: "+list.size());
			if(!list.isEmpty()) {
				Object obj[]=list.get(0);				
				System.out.println(obj[0]+" "+obj[1]+" "+obj[2]);
			}
			else {
				System.out.println("record not found");
			}
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close sessionFactory
			HibernateUtil.closeSessionFactory();
		}*/
		
		//================HQL Select Scalar query that gives (aggregate result) single record =================== 
		/*try {
			//begin Tx
			Transaction tx=ses.beginTransaction();	//dummy Tx
			//prepare query
			Query query=ses.createQuery("SELECT count(*) FROM com.ps.entity.InsurancePolicy");
			//execute query
			List<?> list=query.getResultList();
			//Long count=(Long) list.get(0);
			var count=list.get(0);
			System.out.println("Total no. of record :: "+count);
			
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close sessionFactory
			HibernateUtil.closeSessionFactory();
		}*/

		
	}// main
}// class