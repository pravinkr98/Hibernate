package com.ps.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.PoliticalLeader;
import com.ps.entity.PoliticalParty;
import com.ps.utility.HibernateUtil;

public class ManyToOneDAOImpl implements IManyToOneDAO {

	@Override
	public void saveUsingParent() {

		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		boolean saved=false;
		//prepare object to save
		PoliticalParty party1=new PoliticalParty("BJP","lotus","saffron-green");
		PoliticalLeader leader1=new PoliticalLeader("Modi", "PM", "Delhi");
		PoliticalLeader leader2=new PoliticalLeader("Yogi", "CM", "UP");
		leader1.setParty(party1);
		leader2.setParty(party1);
		//prepare another object
		PoliticalParty party2=new PoliticalParty("AAP","groom","white");
		PoliticalLeader leader3=new PoliticalLeader("Kejriwal", "CM", "Delhi");
		PoliticalLeader leader4=new PoliticalLeader("Prasant", "D-CM", "Delhi");
		leader3.setParty(party2);
		leader4.setParty(party2);
		try {
			//create or locate tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//save object
			ses.save(leader1);
			ses.save(leader2);
			ses.save(leader3);
			ses.save(leader4);
			saved=true;
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(saved) {
				tx.commit();
				System.out.println("Objects are saved");
			}
			else {
				tx.rollback();
				System.out.println("Objects are not saved");
			}
		}//finally		
	}//method

}//class
