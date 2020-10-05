package com.ps.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import com.ps.utility.HibernateUtil;

public class NamedNativeSQLSelectTest {

	public static void main(String[] args) {
		Session ses = null;

		// get session object
		ses = HibernateUtil.getSession();
		//========= Get Access to NamedNativeSQLQuery =========
		try {
			//begin Tx
			Transaction tx=ses.beginTransaction();	//dummy Tx
			//prepare query
			NativeQuery<Object[]> squery=ses.getNamedNativeQuery("GET_ALL_POLICIES_TYPE");
			//set query params
			squery.setParameter(1,"Life");
			
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