package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.utility.HibernateUtil;

public class HQLUpdateTest {

	public static void main(String[] args) {
		Session ses = null;
		boolean affected=false;
		Transaction tx=null;

		// get session object
		ses = HibernateUtil.getSession();
		
		//================HQL Select Update query =================== 
		try {
			//begin Tx
			tx=ses.beginTransaction();
			//prepare query
			Query query=ses.createQuery("UPDATE com.ps.entity.InsurancePolicy SET tenure=tenure+:bonusYear WHERE policyName LIKE :initLetter");
			//set query param
			query.setParameter("bonusYear", 10);
			query.setParameter("initLetter", "J%");
			//execute query
			int count=query.executeUpdate();
			System.out.println("No. of affected :: "+count);
			affected=true;
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}
		finally {
			//commit and rollback Tx
			if(affected) {
				tx.commit();
				System.out.println("Record is updated");
			}
			else {
				tx.rollback();
				System.out.println("Record is not found for updated");
			}
			//close SessionFactory
			HibernateUtil.closeSessionFactory();
		}//finally
		
	}// main
}// class