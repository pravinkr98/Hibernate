package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ps.entity.Membership;
import com.ps.utility.HibernateUtil;

public class FlushObjectTest {

	public static void main(String[] args) {
		Session ses=null;
		Membership member=null;
		long idVal=0L;
		
		//Load session object from utility class
		ses=HibernateUtil.getSession();
		//create membership class obj
		member=new Membership();
		//prepare object for saveOrUpdate
		member.setMid(9631437977L);
		member.setName("Shiwangi");
		member.setAddrs("Chapra");
		member.setrewardsPoint(18L);
		try {
			//save or update object
			idVal=(long) ses.save(member);
			System.out.println("Generated id value : "+idVal);
			ses.flush();
		}
		catch(HibernateException he) {
			//he.printStackTrace();
		}
		finally {
			HibernateUtil.closeSession(ses);
			HibernateUtil.closeSessionFactory();				
		}//finally		
	}//main
}//class
