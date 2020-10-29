package com.ps.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.CardPayment;
import com.ps.entity.ChequePayment;
import com.ps.entity.Payment;
import com.ps.utility.HibernateUtil;

public class AnnoInheritanceMappingSelectTest {

	public static void main(String[] args) {

		Session ses=null;
		Transaction tx=null;
		
		//get session
		ses=HibernateUtil.getSession();
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			
			//prepare query
			Query<Payment> query1=ses.createQuery("FROM com.ps.entity.Payment");
			List<Payment> list1=query1.getResultList();
			list1.forEach(System.out::println);
			
			Query<CardPayment> query2=ses.createQuery("FROM com.ps.entity.CardPayment");
			List<CardPayment> list2=query2.getResultList();
			list2.forEach(System.out::println);
			
			Query<ChequePayment> query3=ses.createQuery("FROM com.ps.entity.ChequePayment");
			List<ChequePayment> list3=query3.getResultList();
			list3.forEach(System.out::println);
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close session factory
			HibernateUtil.closeSessionFactory();
		}//finally
		
	}//main
}//class
