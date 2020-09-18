package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.BankAccount;
import com.ps.utility.HibernateUtil;

public class AccountModifyTimeStampTest {

	public static void main(String[] args) {
		Session ses=null;
		BankAccount account=null;
		Transaction tx=null;
		boolean modified=false;
		
		//get session
		ses=HibernateUtil.getSession();
				
		try {
			//begin Tx
			tx=ses.beginTransaction();
			//get person object
			account=ses.get(BankAccount.class, 10000L);
			//modify customer object
			if(account!=null) {
				account.setBalance(account.getBalance()+16000);
				modified=true;
			}
			else {
				System.out.println("Record not found");
			}
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//commit or rollback Tx
			if(modified) {
				tx.commit();
				System.out.println("Account modified successfully.");
				System.out.println("Account Opening Date :: "+account.getOpeningDate());
				System.out.println("Last Transaction Date :: "+account.getLastUpdate());
			}
			else {
				tx.rollback();
				System.out.println("Account not modified.");
			}
			//close session
			HibernateUtil.closeSession(ses);
			HibernateUtil.closeSessionFactory();
		}//finally		
	}//main
}//class