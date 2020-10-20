package com.ps.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.BankAccount;
import com.ps.utility.HibernateUtil;

public class SelectTestWhereClause {

	public static void main(String[] args) {

		Session ses=null;
		Transaction tx=null;
		
		//get session obj
		ses=HibernateUtil.getSession();
		try {
			//create or locate tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//prepare query obj
			Query query=ses.createQuery("FROM BankAccount");
			//execute query
			List<BankAccount> list=query.getResultList();
			//process result
			list.forEach(System.out::println);						
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close session Factory obj
			HibernateUtil.closeSessionFactory();
		}//finally		
	}//main
}//class