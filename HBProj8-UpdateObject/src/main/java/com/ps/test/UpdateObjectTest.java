package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.BankAccount;
import com.ps.utility.HibernateUtil;

public class UpdateObjectTest {

	public static void main(String[] args) {
		Session ses=null;
		BankAccount account=null;
		Transaction tx=null;
		boolean flag=false;
		
		//get session object
		ses=HibernateUtil.getSession();
		//prepare full object for modification
		account=new BankAccount();
		account.setAcno(1001L);
		account.setHolderName("Guddu");
		account.setBalance(200000.0);
		
		try {
			//begin Tx
			tx=ses.beginTransaction();
			ses.update(account);
			flag=true;
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
