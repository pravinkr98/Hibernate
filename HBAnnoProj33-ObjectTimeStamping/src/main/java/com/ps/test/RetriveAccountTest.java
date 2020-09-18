package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ps.entity.BankAccount;
import com.ps.utility.HibernateUtil;

public class RetriveAccountTest {

	public static void main(String[] args) {
		Session ses=null;
		BankAccount account=null,account1=null;
		
		//get session
		ses=HibernateUtil.getSession();
		try {
			//retrive person object
			account=ses.get(BankAccount.class, 141L);
			if(account!=null)
				System.out.println(account);
			else
				System.out.println("Record not found");
			
			//clear cache memory
			ses.clear();
			//retrive person object
			account1=ses.get(BankAccount.class, 141L);
			if(account1!=null)
				System.out.println(account1);
			else
				System.out.println("Record not found");
			System.out.println(account.hashCode()+"     "+account1.hashCode());
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close session
			HibernateUtil.closeSession(ses);
			HibernateUtil.closeSessionFactory();
		}//finally		
	}//main
}//class