package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.PrgmrProjId;
import com.ps.entity.ProgrammerProjectInfo;
import com.ps.utility.HibernateUtil;

public class SaveObjectTest {

	public static void main(String[] args) {
		Session ses=null;
		PrgmrProjId id=null;
		ProgrammerProjectInfo info=null;
		Transaction tx=null;	
		boolean saved=false;
		
		//get session object
		ses=HibernateUtil.getSession();
		id=new PrgmrProjId();
		id.setPid(51);id.setProjId(5001);
		info=new ProgrammerProjectInfo();
		info.setId(id);
		info.setPname("Ramesh");
		info.setDeptNo(21);
		info.setProjName("OpenFx");
		try {
			//begin Tx
			tx=ses.beginTransaction();
			//save object
			id=(PrgmrProjId) ses.save(info);
			System.out.println("Id value is : "+id);
			saved=true;
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
		}		
	}//main
}//class
