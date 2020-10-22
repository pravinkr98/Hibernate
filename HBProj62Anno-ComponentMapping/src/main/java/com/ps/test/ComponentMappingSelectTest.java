package com.ps.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.StudentInfo;
import com.ps.utility.HibernateUtil;

public class ComponentMappingSelectTest {

	public static void main(String[] args) {
		Session ses=null;
		Transaction tx=null;
		
		//get session obj
		ses=HibernateUtil.getSession();
		try {
			//begin Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//prepare query
			Query<StudentInfo> query=ses.createQuery("FROM com.ps.entity.StudentInfo WHERE areaName=:area");
			//set query param
			query.setParameter("area", "Chandwara");
			//execute query
			List<StudentInfo> list=query.getResultList();
			//process result
			list.forEach(System.out::println);			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close SessionFactory
			HibernateUtil.closeSessionFactory();
		}
		
	}//main
}//class