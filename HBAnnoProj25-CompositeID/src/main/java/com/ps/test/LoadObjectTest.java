package com.ps.test;

import org.hibernate.Session;

import com.ps.entity.PrgmrProjId;
import com.ps.entity.ProgrammerProjectInfo;
import com.ps.utility.HibernateUtil;

public class LoadObjectTest {

	public static void main(String[] args) {
		Session ses=null;
		PrgmrProjId id=null;
		ProgrammerProjectInfo info=null;		
		
		//get session object
		ses=HibernateUtil.getSession();
		id=new PrgmrProjId();
		id.setPid(51);id.setProjId(5001);
		try {			
			//Load object
			info=ses.get(ProgrammerProjectInfo.class, id);
			if(info!=null)
				System.out.println(info);
			else
				System.out.println("Record not found");			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close session
			HibernateUtil.closeSession(ses);
			HibernateUtil.closeSessionFactory();			
		}			
			
	}//main
}//class
