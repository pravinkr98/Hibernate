package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.BankAccount;
import com.ps.utility.HibernateUtil;

public class SoftDeletionTest {

	public static void main(String[] args) {

		Session ses=null;
		Transaction tx=null;
		BankAccount account=null;
		boolean deleted=false;
		
		//get session obj
		ses=HibernateUtil.getSession();
		try {
			//create or locate tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//prepare bankaccount obj
			account=new BankAccount();
			account.setAcno(1001);
			//execute delete()
			ses.delete(account);
			deleted=true;			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//commit or rollback tx
			if(deleted) {
				tx.commit();
				System.out.println("Record deleted(Soft deletion)");
			}
			else {
				tx.rollback();
				System.out.println("Record not found for deletion");
			}
			//close session Factory
			HibernateUtil.closeSessionFactory();
		}//finally		
	}//main
}//class