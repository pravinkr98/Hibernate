package com.ps.test;

import java.util.List;

import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.BankAccount;
import com.ps.utility.HibernateUtil;

public class HBFilterTest_HQL {

	public static void main(String[] args) {

		Session ses=null;
		Transaction tx=null;
		
		//get Session obj
		ses=HibernateUtil.getSession();
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive()) {
				tx=ses.beginTransaction();
			}
			//prepare filter object
			Filter filter=ses.enableFilter("FILTER_BANKACCOUNT_STATUS");
			//set param to filter
			filter.setParameter("accountType1", "blocked");
			filter.setParameter("accountType2", "closed");
			//prepare query obj
			Query<BankAccount> query=ses.createQuery("from BankAccount WHERE balance>=:amt");
			//set query param
			query.setParameter("amt", 20000f);
			//execute query using HQL
			List<BankAccount> list=query.getResultList();
			//process result
			list.forEach(System.out::println);
			
			//close filter
			ses.disableFilter("FILTER_BANKACCOUNT_STATUS");
			//prepare query obj
			Query<BankAccount> query1=ses.createQuery("from BankAccount WHERE balance>=:amt");
			//set query param
			query1.setParameter("amt", 20000f);
			//execute query using HQL
			List<BankAccount> list1=query1.getResultList();
			//process result
			list1.forEach(System.out::println);
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close sessionFactory object
			HibernateUtil.closeSessionFactory();
		}
		
	}//main
}//class