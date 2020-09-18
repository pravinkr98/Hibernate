package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ps.entity.MobileCustomer;
import com.ps.utility.HibernateUtil;

public class RetriveVersionCountTest {

	public static void main(String[] args) {
		Session ses=null;
		MobileCustomer customer=null;
		
		//get session
		ses=HibernateUtil.getSession();
		try {
			//retrive person object
			customer=ses.get(MobileCustomer.class, 120);
			if(customer!=null)
				System.out.println(customer);
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