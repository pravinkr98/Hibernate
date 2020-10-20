package com.ps.test;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.BankAccount;
import com.ps.utility.HibernateUtil;

public class HBFilterTest_JPA_QBC {

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
			//prepare criteriaBuilder object
			CriteriaBuilder ctBuilder=ses.getCriteriaBuilder();
			//prepare CriteriaQuery object
			CriteriaQuery<BankAccount> ctQuery= ctBuilder.createQuery(BankAccount.class);
			//prepare root object
			Root<BankAccount> root=ctQuery.from(BankAccount.class);
			ctQuery.select(root);
			//prepare query obj
			Query<BankAccount> query=ses.createQuery(ctQuery);
			//execute query using HQL
			List<BankAccount> list=query.getResultList();
			//process result
			list.forEach(System.out::println);
			
			//close filter
			ses.disableFilter("FILTER_BANKACCOUNT_STATUS");
			//prepare criteriaBuilder object
			CriteriaBuilder ctBuilder1=ses.getCriteriaBuilder();
			//prepare CriteriaQuery object
			CriteriaQuery<BankAccount> ctQuery1= ctBuilder1.createQuery(BankAccount.class);
			//prepare root object
			Root<BankAccount> root1=ctQuery1.from(BankAccount.class);
			ctQuery1.select(root1);
			//prepare query obj
			Query<BankAccount> query1=ses.createQuery(ctQuery1);
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