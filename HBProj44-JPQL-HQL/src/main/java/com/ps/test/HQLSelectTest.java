package com.ps.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.InsurancePolicy;
import com.ps.utility.HibernateUtil;

public class HQLSelectTest {

	public static void main(String[] args) {
		Session ses = null;

		// get session object
		ses = HibernateUtil.getSession();
		/*try {
			//begin Tx
			Transaction tx=ses.beginTransaction();	//dummy Tx
			//prepare query
			//query=ses.createQuery("FROM com.ps.entity.InsurancePolicy");
			Query query=ses.createQuery("SELECT pi FROM com.ps.entity.InsurancePolicy pi");
			//execute query
			List<InsurancePolicy> list=query.getResultList();
			//process the result
			list.forEach(policy->{				//Java8 forEach with Lambda Expression
				System.out.println(policy);
			});
			
			list.forEach(System.out::println); 		//Java8 forEach with method reference
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close sessionFactory
			HibernateUtil.closeSessionFactory();
		}
		*/

		// ================= Executing HQL Select Entity Query with Iterate()
		// =============
		/*try {
			//begin dummy Tx
			Transaction tx=ses.beginTransaction();
			//create query
			Query<InsurancePolicy> query=ses.createQuery("FROM com.ps.entity.InsurancePolicy");
			//execute query
			Iterator<InsurancePolicy> itr=query.iterate();
			//process the result
			//int i=0;
			while(itr.hasNext()) {
				InsurancePolicy policy=itr.next();
				System.out.println(policy);
				//System.out.println(i++);
			}
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close sessionFactory
			HibernateUtil.closeSessionFactory();
		}*/

		// ================= HQL Select Entity Query with Jpa style positional param
		// =============
		/*try {
			Transaction tx=ses.beginTransaction();
			//prepare query object
			//Query<InsurancePolicy> query=ses.createQuery("FROM com.ps.entity.InsurancePolicy WHERE policyId>=?1 AND policyId<=?2 ");
			Query<InsurancePolicy> query=ses.createQuery("FROM com.ps.entity.InsurancePolicy WHERE policyId>=?5 AND policyId<=?6 ");
			//set query param
			query.setInteger(1, 9001);
			query.setInteger(2, 9004); 	//deprecated from 5.2
			query.setParameter(5, 9001L);
			query.setParameter(6, 9004L);
			//execute query
			List<InsurancePolicy> list=query.getResultList();
			//process the result
			list.forEach(System.out::println);			
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close sessionFactory
			HibernateUtil.closeSessionFactory();
		}*/
		
		//================= HQL Select Entity Query with Named params =============
		try {
			Transaction tx=ses.beginTransaction();
			//prepare query object
			//Query<InsurancePolicy> query=ses.createQuery("FROM com.ps.entity.InsurancePolicy WHERE policyId>=:start AND policyId<=:end ");
			Query<InsurancePolicy> query=ses.createQuery("FROM com.ps.entity.InsurancePolicy WHERE company IN(:org1,:org2)");
			//Query<InsurancePolicy> query=ses.createQuery("FROM com.ps.entity.InsurancePolicy WHERE company IN(?1,:org2)");
			//Query<InsurancePolicy> query=ses.createQuery("FROM com.ps.entity.InsurancePolicy WHERE company IN(:org1,?1)");
			//Query<InsurancePolicy> query=ses.createQuery("FROM :p1 WHERE company IN(:org1,:org2)");	//like this not possible
			//set query param
			//query.setParameter("start", 9001L);
			//query.setParameter("end", 9004L);
			//query.setParameter("p1", "com.ps.entity.InsurancePolicy");	//wrong, not possible
			query.setParameter("org1", "LIC");
			query.setParameter("org2", "AIG");
			//execute query
			List<InsurancePolicy> list=query.getResultList();
			//process the result
			list.forEach(System.out::println);			
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close sessionFactory
			HibernateUtil.closeSessionFactory();
		}

	}// main
}// class