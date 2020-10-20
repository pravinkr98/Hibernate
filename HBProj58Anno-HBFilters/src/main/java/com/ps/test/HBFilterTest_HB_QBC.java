package com.ps.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.BankAccount;
import com.ps.utility.HibernateUtil;

public class HBFilterTest_HB_QBC {

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
			Criteria criteria=ses.createCriteria(BankAccount.class);
			//execute query using HQL
			List<BankAccount> list=criteria.list();
			//process result
			list.forEach(System.out::println);
			
			//close filter
			ses.disableFilter("FILTER_BANKACCOUNT_STATUS");
			//prepare query obj
			Criteria criteria1=ses.createCriteria(BankAccount.class);
			//execute query using HQL
			List<BankAccount> list1=criteria1.list();
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