package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.Membership;
import com.ps.utility.HibernateUtil;

public class MergeObjectTest2 {

	public static void main(String[] args) {
		Session ses=null;
		Membership member=null,member1=null,member2=null;
		Transaction tx=null;
		boolean flag=false;
		
		//Load session object from utility class
		ses=HibernateUtil.getSession();
		//create membership class obj
		member=ses.get(Membership.class, 9631437974L);
		System.out.println(member);
		System.out.println("--------------------------------");
		//member2=ses.get(Membership.class, 9631437974L); 	//for cache checking
		//System.out.println(member2);
		try {
			//begin Tx
			tx=ses.beginTransaction();
			//prepare object for merge
			member1=new Membership();
			member1.setMid(9631437974L);
			member1.setName("Sharma Sir");
			member1.setAddrs("Gaya");
			member1.setrewardsPoint(22L);
			//check save or update or delete or merge object
			//ses.update(member1);		//throws NonUniqueObjectException
			//ses.save(member1);		//throws NonUniqueObjectException
			//ses.saveOrUpdate(member1);	//throws NonUniqueObjectException
			//ses.delete(member1);		//throws EntityExitsException
			member2=(Membership) ses.merge(member1);
			System.out.println(member2);
			System.out.println("Member : "+member.hashCode()+"\nMember1 : "+member1.hashCode()+"\nMember2 : "+member2.hashCode());
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
