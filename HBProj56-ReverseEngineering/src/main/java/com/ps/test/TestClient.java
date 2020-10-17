package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.Project;
import com.ps.utility.HibernateUtil;

public class TestClient {

	public static void main(String[] args) {

		Session ses=null;
		Transaction tx=null;
		
		//create ses object
		ses=HibernateUtil.getSession();
		try {
			//get tx object
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			Project proj=ses.get(Project.class, 2L);
			if(proj!=null)
				System.out.println(proj);
			else
				System.out.println("Record not found");
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close Session factory
			HibernateUtil.closeSessionFactory();
		}
	}//main
}//class