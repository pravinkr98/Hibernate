package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ps.entity.PersonInfo;
import com.ps.utility.HibernateUtil;

public class RetriveDateTest {

	public static void main(String[] args) {
		Session ses=null;
		PersonInfo info=null;
		
		//get session
		ses=HibernateUtil.getSession();
		try {
			//retrive person object
			info=ses.get(PersonInfo.class, 5);
			if(info!=null)
				System.out.println(info);
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