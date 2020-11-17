package com.ps.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.PhoneNumber;
import com.ps.entity.UserInfo;
import com.ps.utility.HibernateUtil;

public class OneToManyDaoImpl implements OneToManyDao {
	Session ses=null;
	Transaction tx=null;
	
		@Override
	public void loadDataUsingParentToChildHQLJoins() {
	
		//get session
		ses=HibernateUtil.getSession();
		
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//load object
			//Query query=ses.createQuery("Select u.userId,u.userName,u.addrs,ph.regNo,ph.phone,ph.type,ph.provider from UserInfo u inner join u.phones ph");
			//Query query=ses.createQuery("Select u.userId,u.userName,u.addrs,ph.regNo,ph.phone,ph.type,ph.provider from UserInfo u left join u.phones ph");
			//Query query=ses.createQuery("Select u.userId,u.userName,u.addrs,ph.regNo,ph.phone,ph.type,ph.provider from UserInfo u right join u.phones ph");
			Query query=ses.createQuery("Select u.userId,u.userName,u.addrs,ph.regNo,ph.phone,ph.type,ph.provider from UserInfo u full join u.phones ph");
			List<Object[]> list=query.getResultList();
			list.forEach(row->{
				for(Object val:row) {
					System.out.print(val+" ");
				}
				System.out.println();
			});						
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}		
	}//method

		@Override
		public void loadDataUsingChildToParentHQLJoins() {
		
			//get session
			ses=HibernateUtil.getSession();
			
			try {
				//create or locate Tx
				if(!ses.getTransaction().isActive())
					tx=ses.beginTransaction();
				//getting record using joins
				//Query query=ses.createQuery("Select ph.regNo,ph.phone,ph.type,ph.provider,u.userId,u.userName,u.addrs from PhoneNumber ph inner join ph.user u");
				//Query query=ses.createQuery("Select ph.regNo,ph.phone,ph.type,ph.provider,u.userId,u.userName,u.addrs from PhoneNumber ph left join ph.user u");
				//Query query=ses.createQuery("Select ph.regNo,ph.phone,ph.type,ph.provider,u.userId,u.userName,u.addrs from PhoneNumber ph right join ph.user u");
				Query query=ses.createQuery("Select ph.regNo,ph.phone,ph.type,ph.provider,u.userId,u.userName,u.addrs from PhoneNumber ph full join ph.user u");
				List<Object[]> list=query.getResultList();
				list.forEach(row->{
					for(Object val:row) {
						System.out.print(val+" ");
					}
					System.out.println();
				});						
			}
			catch (HibernateException he) {
				he.printStackTrace();
			}		
		}//method
		
}//class
