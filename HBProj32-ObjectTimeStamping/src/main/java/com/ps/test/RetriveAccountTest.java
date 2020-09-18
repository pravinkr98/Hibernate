package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ps.entity.BankAccount;
import com.ps.utility.HibernateUtil;

public class RetriveAccountTest {

	public static void main(String[] args) {
		Session ses=null;
		BankAccount account=null;
		
		//get session
		ses=HibernateUtil.getSession();
		try {
			//retrive person object
			account=ses.get(BankAccount.class, 10000L);
			if(account!=null)
				System.out.println(account);
			else
				System.out.println("Record not found");
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