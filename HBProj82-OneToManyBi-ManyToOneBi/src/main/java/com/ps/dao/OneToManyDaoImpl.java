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
	public void saveDataUsingParent() {
		
		boolean saved=false;
		//get session
		ses=HibernateUtil.getSession();
		//prepare phoneNumber(child) object
		PhoneNumber phone1=new PhoneNumber(9898989898L,"home","airtel");
		PhoneNumber phone2=new PhoneNumber(8787878787L,"officet","bsnl");
		//prepare UserInfo(parent) object
		UserInfo info=new UserInfo("ramesh","hyd");
		//set child to parent 
		info.setPhones(Set.of(phone1, phone2));
		//set parent to child for Bi-directional
		phone1.setUser(info);
		phone2.setUser(info);
		
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//save object using parent
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
				System.out.println("Object saved as Bi-Directional");
			}
			else {
				tx.rollback();
				System.out.println("Object is not saved");
			}
		}//finally
	}//method
	
	@Override
	public void saveDataUsingChild() {

		boolean saved=false;
		//get session
		ses=HibernateUtil.getSession();
		//prepare phoneNumber(child) object
		PhoneNumber phone1=new PhoneNumber(6868686868L,"personal","jio");
		PhoneNumber phone2=new PhoneNumber(5757575757L,"home","bsnl");
		PhoneNumber phone3=new PhoneNumber(4646464646L,"office","airtel");
		//prepare UserInfo(parent) object
		UserInfo info=new UserInfo("Rakesh","Patna");
		//set child to parent 
		info.setPhones(Set.of(phone1,phone2,phone3));
		//set parent to child for Bi-directional
		phone1.setUser(info);
		phone2.setUser(info);
		phone3.setUser(info);
		
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//save object using child
			ses.save(phone1);
			ses.save(phone2);
			ses.save(phone3);
			saved=true;			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(saved) {
				tx.commit();
				System.out.println("Object are saved as Bi-Directional using many Child");
			}
			else {
				tx.rollback();
				System.out.println("Objects are not saved");
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
				System.out.println("Parent :: "+user.getUserId()+" "+user.getUserName()+" "+user.getAddrs());
				//get associated child of a parent
				Set<PhoneNumber> child=user.getPhones();
				child.forEach(ph-> System.out.println("child :: "+ph));
				System.out.println(child.isEmpty());
				/*System.out.println(user.getPhones());*/
			});						
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}		
	}//method

	@Override
	public void loadDataUsingChild() {
	
		//get session
		ses=HibernateUtil.getSession();
		
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//load object
			Query<PhoneNumber> query=ses.createQuery("from com.ps.entity.PhoneNumber");
			List<PhoneNumber> list=query.getResultList();
			list.forEach(ph->{
				System.out.println("Child :: "+ph);
				//get associated child of a parent
				UserInfo user=ph.getUser();
				System.out.println("Parent :: "+user);
			});						
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}		
	}//method
	
	
}//class
