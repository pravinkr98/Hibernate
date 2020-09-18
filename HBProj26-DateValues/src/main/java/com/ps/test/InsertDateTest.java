package com.ps.test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.PersonInfo;
import com.ps.utility.HibernateUtil;

public class InsertDateTest {

	public static void main(String[] args) {
		Session ses=null;
		PersonInfo info=null;
		Transaction tx=null;
		int idVal=0;
		boolean saved=false;
		
		//get session
		ses=HibernateUtil.getSession();
		//prepare person object
		info=new PersonInfo();
		info.setPname("Farhan");
		info.setPaddrs("Hyd");
		info.setDob(new java.util.Date(92,11,23,11,32,42));
		info.setDom(new java.util.Date(118,10,21));
		info.setDoj(new java.util.Date());
		
		/*info.setDob(new java.sql.Date(new java.util.Date(92,11,23,11,32,42).getTime()));
		info.setDom(new java.sql.Date(118,10,21));
		info.setDoj(new java.sql.Date(new java.util.Date().getTime()));*/
				
		try {
			//begin Tx
			tx=ses.beginTransaction();
			//save object
			idVal=(int) ses.save(info);
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