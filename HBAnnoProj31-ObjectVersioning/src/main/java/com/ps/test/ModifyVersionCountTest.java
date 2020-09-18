package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.MobileCustomer;
import com.ps.utility.HibernateUtil;

public class ModifyVersionCountTest {

	public static void main(String[] args) {
		Session ses=null;
		MobileCustomer customer=null;
		Transaction tx=null;
		int idVal=0;
		boolean modified=false;
		
		//get session
		ses=HibernateUtil.getSession();
				
		try {
			//begin Tx
			tx=ses.beginTransaction();
			//get person object
			customer=ses.get(MobileCustomer.class, 120);
			//modify customer object
			if(customer!=null) {
				customer.setCallerTune("Musafir phir v");
				modified=true;
			}
			else {
				System.out.println("Record not found");
			}
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//commit or rollback Tx
			if(modified) {
				tx.commit();
				System.out.println("Object modified successfully.");
				System.out.println("Version Count :: "+customer.getVersionCount());
			}
			else {
				tx.rollback();
				System.out.println("Object not modify.");
			}
			//close session
			HibernateUtil.closeSession(ses);
			HibernateUtil.closeSessionFactory();
		}//finally		
	}//main
}//class