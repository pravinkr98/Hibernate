package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import com.ps.utility.HibernateUtil;

public class NativeSQLNonSelectTest {

	public static void main(String[] args) {
		Session ses = null;
		Transaction tx=null;
		boolean inserted=false;
		// get session object
		ses = HibernateUtil.getSession();
		//========== insert record with direct value ================
		try {
			//begin Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//prepare query
			NativeQuery nquery=ses.createSQLQuery("INSERT INTO INSURANCEPOLICY VALUES(?,?,?,?,?)");
			//NativeQuery nquery=ses.createSQLQuery("INSERT INTO INSURANCEPOLICY VALUES(?,?,?,?)");
			//set query param
			nquery.setParameter(1, 234);
			nquery.setParameter(2, "Sampoorn Kawach");
			nquery.setParameter(3, "Life");
			nquery.setParameter(4, "TATA");
			nquery.setParameter(5, 25);
			
			/*nquery.setParameter(1, "Kawach");	// Generator not working, not supported
			nquery.setParameter(2, "Life");	
			nquery.setParameter(3, "TATA");
			nquery.setParameter(4, 21);*/
			
			//execute query
			int count=nquery.executeUpdate();
			inserted=true;
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//commit or rollback Tx
			if(inserted) {
				tx.commit();
				System.out.println("Record inserted/Saved");
			}
			else {
				tx.rollback();
				System.out.println("Record insertion failed");
			}
			//close SessionFactory
			HibernateUtil.closeSessionFactory();
		}
		
	}// main
}// class