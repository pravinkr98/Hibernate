package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.Project;
import com.ps.utility.HibernateUtil;

public class SaveObjectAnnoTest {

	public static void main(String[] args) {
		Session ses=null;
		Project proj=null;
		Transaction tx=null;
		Long idVal=0L;
		boolean flag=false;
		
		//get session object
		ses=HibernateUtil.getSession();
		//create object to save
		proj=new Project();
		proj.setProjId(1110L);
		/*proj.setProjName("OpenFx9");
		proj.setCompany("Wipro4");*/
		proj.setTeamSize(14);
		try {
			//begin Tx
			tx=ses.beginTransaction();
			//save object
			idVal=(Long) ses.save(proj);
			System.out.println("Generated id value : "+idVal);
			flag=true;
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(flag) {
				tx.commit();
				System.out.println("Object saved");
			}
			else {
				tx.rollback();
				System.out.println("Object not saved");
			}
			//close session
			HibernateUtil.closeSession(ses);
			HibernateUtil.closeSessionFactory();
			
		}//finally		
	}//main
}//class
