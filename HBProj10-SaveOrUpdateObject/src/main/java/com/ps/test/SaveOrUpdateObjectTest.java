package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.Membership;
import com.ps.utility.HibernateUtil;

public class SaveOrUpdateObjectTest {

	public static void main(String[] args) {
		Session ses=null;
		Membership member=null;
		Transaction tx=null;
		boolean flag=false;
		
		//Load session object from utility class
		ses=HibernateUtil.getSession();
		//create membership class obj
		member=new Membership();
		//prepare object for saveOrUpdate
		member.setMid(9631437974L);
		member.setName("Sharma Sir");
		member.setAddrs("Gaya");
		member.setrewardsPoint(10L);
		try {
			//begin Tx
			tx=ses.beginTransaction();
			//save or update object
			ses.saveOrUpdate(member);
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
				System.out.println("Object is saved or updated");
			}
			else {
				tx.rollback();
				System.out.println("Object is not saved or updated");
			}				
		}//finally
		
	}//main

}//class
