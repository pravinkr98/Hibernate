package com.ps.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.InsurancePolicy;
import com.ps.utility.HibernateUtil;

public class AggregateAssignmentTest {

	public static void main(String[] args) {
		Session ses = null;

		// get session object
		ses = HibernateUtil.getSession();

		// ========Get InsurancePolicy details who is having highest nth tenure ===========
		try {
			//begin Tx
			Transaction tx=ses.beginTransaction();
			//prepare query
			Query query=ses.createQuery("FROM com.ps.entity.InsurancePolicy WHERE tenure=(SELECT MAX(tenure) FROM com.ps.entity.InsurancePolicy)");
			//execute query
			List<InsurancePolicy> list=query.getResultList();
			//process the result
			if(!list.isEmpty()) {
				System.out.println("========================= Record who's having highest tenure ==============================");
				list.forEach(System.out::println);
				//System.out.println(list.get(0));
			}
			else {
				System.out.println("No record found");
			}			
		}//try
		catch (HibernateException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close SessionFactory
			HibernateUtil.closeSessionFactory();
		}//finally
							

		// ========Get InsurancePolicy details who is having lowest policy id ===========
		/*try {
			// begin Tx
			Transaction tx = ses.beginTransaction();
			// prepare query
			Query query = ses.createQuery(
					"FROM com.ps.entity.InsurancePolicy WHERE policyId=(SELECT MIN(policyId) FROM com.ps.entity.InsurancePolicy)");
			// execute query
			List<InsurancePolicy> list = query.getResultList();
			// process the result
			if (!list.isEmpty()) {
				System.out.println(
						"========================= Record who's having lowest policy id ==============================");
				System.out.println(list.get(0));
			} else {
				System.out.println("No record found");
			}
		} // try
		catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close SessionFactory
			HibernateUtil.closeSessionFactory();
		} // finally
		*/		
	}// main
}// class