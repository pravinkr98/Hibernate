package com.ps.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.Employee;
import com.ps.entity.Person;
import com.ps.entity.Student;
import com.ps.utility.HibernateUtil;

public class InheritenceMappingSelectTest {

	public static void main(String[] args) {

		Session ses=null;
		Transaction tx=null;
				
		//get session obj
		ses=HibernateUtil.getSession();
				
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//prepare object
			Query<Person> query1=ses.createQuery("from Person");
			List<Person> list1=query1.getResultList();
			list1.forEach(System.out::println);
			
			//prepare object
			Query<Student> query2=ses.createQuery("from Student");
			List<Student> list2=query2.getResultList();
			list2.forEach(System.out::println);
			
			//prepare object
			Query<Employee> query3=ses.createQuery("from Employee");
			List<Employee> list3=query3.getResultList();
			list3.forEach(System.out::println);
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close sessionFactory
			HibernateUtil.closeSessionFactory();
		}//finally
		
	}//main
}//class