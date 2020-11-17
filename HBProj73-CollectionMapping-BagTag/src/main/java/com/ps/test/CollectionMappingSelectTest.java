package com.ps.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.Employee;
import com.ps.utility.HibernateUtil;

public class CollectionMappingSelectTest {

	public static void main(String[] args) {
		
		Session ses=null;
		Transaction tx=null;
		
		//get session obj
		ses=HibernateUtil.getSession();
		try {
			//begin tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();			
			//prepare query
			Query<Employee> query=ses.createQuery("FROM com.ps.entity.Employee");
			List<Employee> list=query.getResultList();
			list.forEach(System.out::println);					
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close SessionFactory
			HibernateUtil.closeSessionFactory();
		}//finally
		
	}//main
}//class