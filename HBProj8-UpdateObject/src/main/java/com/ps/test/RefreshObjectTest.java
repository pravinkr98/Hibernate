package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.BankAccount;
import com.ps.utility.HibernateUtil;

public class RefreshObjectTest {

	public static void main(String[] args) {
		Session ses=null;
		BankAccount account=null;
				
		//get session object
		ses=HibernateUtil.getSession();
		try {
			//load object for partial modification
			account=ses.get(BankAccount.class, 1001L);	
			if(account!=null) {				
				System.out.println(account);
				
				System.out.println("modify db table by using sql prompt or sql developer");
				try {
					Thread.sleep(40000); 	//changing db table using sql
				}
				catch(InterruptedException ie) {
					ie.printStackTrace();
				}
				
				ses.refresh(account);	//db table row to entity object sync
				System.out.println(account);
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
			
		//close session
		HibernateUtil.closeSession(ses);
		//close SessionFactory
		HibernateUtil.closeSessionFactory();
		
	}//main

}//class
