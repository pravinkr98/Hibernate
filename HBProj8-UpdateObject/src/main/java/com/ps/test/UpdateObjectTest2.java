package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.BankAccount;
import com.ps.utility.HibernateUtil;

public class UpdateObjectTest2 {

	public static void main(String[] args) {
		Session ses=null;
		BankAccount account=null;
		Transaction tx=null;
		boolean flag=false;
		
		//get session object
		ses=HibernateUtil.getSession();
		try {
			//begin Tx
			tx=ses.beginTransaction();
			//load object for partial modification
			account=ses.get(BankAccount.class, 1001L);	
			if(account!=null) {				
				//modify object
				account.setBalance(170000L);
				flag=true;
			}
			else {
				System.out.println("Object/Record not found");
				return;
			}
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(flag==true) {
				tx.commit();
				System.out.println("Object updated");
			}
			else {
				tx.rollback();
				System.out.println("Object not updated");
			}
		}//finally
		
		//close session
		HibernateUtil.closeSession(ses);
		//close SessionFactory
		HibernateUtil.closeSessionFactory();
		
	}//main

}//class
