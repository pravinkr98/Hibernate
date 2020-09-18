package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.BankAccount;
import com.ps.utility.HibernateUtil;

public class AccountOpeningTimeStampTest {

	public static void main(String[] args) {
		Session ses=null;
		BankAccount account=null;
		Transaction tx=null;
		long idVal=0;
		boolean saved=false;
		
		//get session
		ses=HibernateUtil.getSession();
		//prepare person object
		account=new BankAccount();
		account.setHolderName("Mahadev");
		account.setBalance(275000.0);
		account.setType("Savings");
		
		try {
			//begin Tx
			tx=ses.beginTransaction();
			//save object
			idVal= (long) ses.save(account);
			saved=true;
			System.out.println("Generated id: "+idVal);			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//commit or rollback Tx
			if(saved) {
				tx.commit();
				System.out.println("Account created successfully.");
				System.out.println("Account Opening Date :: "+account.getOpeningDate());
				System.out.println("Opening Transaction Date :: "+account.getLastUpdate());
				System.out.println("Account version count :: "+account.getVersionCount());
			}
			else {
				tx.rollback();
				System.out.println("Account creation failed.");
			}
			//close session
			HibernateUtil.closeSession(ses);
			HibernateUtil.closeSessionFactory();
		}//finally		
	}//main
}//class