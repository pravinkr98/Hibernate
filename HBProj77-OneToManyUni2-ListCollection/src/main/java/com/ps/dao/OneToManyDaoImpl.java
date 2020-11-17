package com.ps.dao;

import java.util.List;

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
	public void saveDataUsingParent() {
		
		boolean saved=false;
		//get session
		ses=HibernateUtil.getSession();
		//prepare phoneNumber object
		PhoneNumber phones=new PhoneNumber(1198989898L,"home","airtel");
		PhoneNumber phones1=new PhoneNumber(1187878787L,"officet","bsnl");
		//prepare UserInfo object
		UserInfo info=new UserInfo("ramesh2","hyd");
		info.setPhones(List.of(phones, phones1));
		
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//save object
			ses.save(info);
			saved=true;			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(saved) {
				tx.commit();
				System.out.println("Object saved");
			}
			else {
				tx.rollback();
				System.out.println("Object is not saved");
			}
		}//finally
	}//method

	@Override
	public void loadDataUsingParent() {
	
		//get session
		ses=HibernateUtil.getSession();
		
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//load object
			Query<UserInfo> query=ses.createQuery("from com.ps.entity.UserInfo");
			List<UserInfo> list=query.getResultList();
			list.forEach(user->{
				System.out.println(user.getUserId()+" "+user.getUserName()+" "+user.getAddrs());
				//get associated child of a parent
				List<PhoneNumber> child=user.getPhones();
				child.forEach(ph-> System.out.println("child phone :: "+ph));
				System.out.println(child.isEmpty());
				/*System.out.println(user.getPhones());*/
			});						
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}		
	}//method

		@Override
	public void deleteOneChildFromCollectionOfChildsOfAParent() {

		boolean deleteOne=false;
		//get session
		ses=HibernateUtil.getSession();
		
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//load parent to delete its one child 
			UserInfo user=ses.get(UserInfo.class, 1);
			//get all child from its parent
			List<PhoneNumber> childs=user.getPhones();
			//delete specific child element through index from the list collection
			childs.remove(0);
			
			deleteOne=true;			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(deleteOne) {
				tx.commit();
				System.out.println("Specific child records are deleted");
			}
			else {
				tx.rollback();
				System.out.println("Problem in deleting specific child");
			}			
		}//finally		
	}//method
	
}//class
