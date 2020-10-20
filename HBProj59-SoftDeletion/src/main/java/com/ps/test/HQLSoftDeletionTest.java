package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.BankAccount;
import com.ps.utility.HibernateUtil;

public class HQLSoftDeletionTest {

	public static void main(String[] args) {

		Session ses=null;
		Transaction tx=null;
		BankAccount account=null;
		boolean deleted=false;
		int count=0;
		
		//get session obj
		ses=HibernateUtil.getSession();
		try {
			//create or locate tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//prepare query obj
			Query query=ses.createQuery("UPDATE BankAccount SET status='closed' WHERE acno=:no");
			//set query param
			query.setParameter("no", 1003);
			//execute query
			count=query.executeUpdate();	//Soft deletion is not working other than ses.delete();
			deleted=true;			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//commit or rollback tx
			if(deleted) {
				tx.commit();
				System.out.println(count+" Record deleted");
			}
			else {
				tx.rollback();
				System.out.println("Record not found for deletion");
			}
			//close sessionFactory
			HibernateUtil.closeSessionFactory();
		}//finally		
	}//main
}//class