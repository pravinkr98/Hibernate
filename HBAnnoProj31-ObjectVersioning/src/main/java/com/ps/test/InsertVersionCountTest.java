package com.ps.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.MobileCustomer;
import com.ps.utility.HibernateUtil;

public class InsertVersionCountTest {

	public static void main(String[] args) {
		Session ses=null;
		MobileCustomer customer=null;
		Transaction tx=null;
		int idVal=0;
		boolean saved=false;
		
		//get session
		ses=HibernateUtil.getSession();
		//prepare person object
		customer=new MobileCustomer();
		customer.setCname("Vikash");
		customer.setMobileNo(7659376283L);
		customer.setCallerTune("Hum Musafir");
		
		try {
			//begin Tx
			tx=ses.beginTransaction();
			//save object
			idVal=(int) ses.save(customer);
			saved=true;
			System.out.println("Generated id: "+idVal);			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//commit or rollback Tx
			if(saved) {
				tx.commit();
				System.out.println("Object saved successfully.");
				System.out.println("Version Count :: "+customer.getVersionCount());
			}
			else {
				tx.rollback();
				System.out.println("Object not saved.");
			}
			//close session
			HibernateUtil.closeSession(ses);
			HibernateUtil.closeSessionFactory();
		}//finally		
	}//main
}//class