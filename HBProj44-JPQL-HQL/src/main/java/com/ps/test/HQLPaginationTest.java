package com.ps.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.InsurancePolicy;
import com.ps.utility.HibernateUtil;

public class HQLPaginationTest {

	public static void main(String[] args) {
		Session ses = null;

		// get session object
		ses = HibernateUtil.getSession();
		try {
			//begin Tx
			Transaction tx=ses.beginTransaction();	//dummy Tx
			//prepare query
			Query query=ses.createQuery("FROM com.ps.entity.InsurancePolicy");
			//set pagination value
			query.setFirstResult(2);	//page start position
			query.setMaxResults(2);		//page size
			//execute query
			List<InsurancePolicy> list=query.getResultList();
			//process the result			
			list.forEach(System.out::println);
			
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close sessionFactory
			HibernateUtil.closeSessionFactory();
		}
		
	}// main
}// class