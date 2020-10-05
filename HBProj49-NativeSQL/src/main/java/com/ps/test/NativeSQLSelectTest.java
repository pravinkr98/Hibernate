package com.ps.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StandardBasicTypes;

import com.ps.entity.InsurancePolicy;
import com.ps.utility.HibernateUtil;

public class NativeSQLSelectTest {

	public static void main(String[] args) {
		Session ses = null;

		// get session object
		ses = HibernateUtil.getSession();
		//========== Native SQL entity query without mapping with entity class ================
		/*		try {
					//begin Tx
					Transaction tx=ses.beginTransaction();	//dummy Tx
					//prepare query
					//SQLQuery<Object[]> squery=ses.createSQLQuery("SELECT * FROM INSURANCEPOLICY WHERE TENURE>=? AND TENURE<=?");	//deprecated from 5.2
					NativeQuery squery=ses.createSQLQuery("SELECT * FROM INSURANCEPOLICY WHERE TENURE>=? AND TENURE<=?");
					//set query params
					squery.setParameter(1, 20);
					squery.setParameter(2, 30);
					//execute query
					List<Object[]> list=squery.getResultList();
					//process the result
					list.forEach(row->{
						for(Object obj:row) {
							System.out.print(obj+" ");
						}
						System.out.println();
					});
					
				}//try
				catch (HibernateException he) {
					he.printStackTrace();
				}
				finally {
					//close sessionFactory
					HibernateUtil.closeSessionFactory();
				}*/

		//=========== Native SQL entity query with mapping entity class =================
		/*try {
			//begin Tx
			Transaction tx=ses.beginTransaction();	//dummy Tx
			//prepare query
			NativeQuery<InsurancePolicy> squery=ses.createSQLQuery("SELECT * FROM INSURANCEPOLICY WHERE TENURE>=? AND TENURE<=?");
			//set query params
			squery.setParameter(1, 20);
			squery.setParameter(2, 30);
			//map result with entity class
			squery.addEntity(InsurancePolicy.class);
			//execute query
			List<InsurancePolicy> list=squery.getResultList();
			//process the result
			list.forEach(System.out::println);
			
			Iterator itr=squery.iterate();
			while(itr.hasNext()) {
				System.out.println(itr.next());		//not supported
			}			
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close sessionFactory
			HibernateUtil.closeSessionFactory();
		}*/
		
		//=========Executing Native SQL Scalar query selecting specific multiple column=========
		try {
			//begin Tx
			Transaction tx=ses.beginTransaction();	//dummy Tx
			//prepare query
			NativeQuery<Object[]> squery=ses.createSQLQuery("SELECT POLICYID,POLICYNAME,POLICYTYPE FROM INSURANCEPOLICY WHERE COMPANY IN(:comp1,:comp2)");
			//set query params
			squery.setParameter("comp1","LIC");
			squery.setParameter("comp2","BAJAJ");
			//map result with Hibernate datatypes
			squery.addScalar("POLICYID",StandardBasicTypes.INTEGER);
			squery.addScalar("POLICYNAME",StandardBasicTypes.STRING);
			squery.addScalar("POLICYTYPE",StandardBasicTypes.STRING);
			//execute query
			List<Object[]> list=squery.getResultList();
			//process the result
			list.forEach(row->{
				for(Object obj:row)
					System.out.print(obj+" ");
				System.out.println();
			});
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