package com.ps.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import com.ps.entity.InsurancePolicy;
import com.ps.utility.HibernateUtil;

public class NamedNativeSQLSelectTest {

	public static void main(String[] args) {
		Session ses = null;
		boolean deleted=false;
		Transaction tx=null;
		int del=0;

		// get session object
		ses = HibernateUtil.getSession();
		//========= Get Access to NamedNativeSQLQuery =========
		try {
			//begin Tx
			tx=ses.beginTransaction();	//dummy Tx
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
			
			//======================Get Access to NamedNativeSQLQuery mapped with entity class =================
			System.out.println();
			//prepare query
			NativeQuery<InsurancePolicy> squery1=ses.getNamedNativeQuery("GET_ALL_POLICIES");
						
			//execute query
			List<InsurancePolicy> list1=squery1.getResultList();
			//process the result
			list1.forEach(System.out::println);
			
			//================= Delete Query ======================
			System.out.println();
			//prepare query
			NativeQuery<InsurancePolicy> squery2=ses.getNamedNativeQuery("DELETE_POLICIES_BY_ID");
			//Set Query param
			squery2.setParameter(1,234);
			//execute query
			del=squery2.executeUpdate();
			deleted=true;
			
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(deleted) {
				tx.commit();
				System.out.println("No. of records deleted :: "+del);
			}
			else {
				tx.rollback();
				System.out.println("Record not found for deletion");
			}
			//close sessionFactory
			HibernateUtil.closeSessionFactory();
		}

	}// main
}// class