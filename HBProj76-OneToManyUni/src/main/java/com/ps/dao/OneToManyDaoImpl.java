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
		//prepare phoneNumber object
		PhoneNumber phones=new PhoneNumber(9898989898L,"home","airtel");
		PhoneNumber phones1=new PhoneNumber(8787878787L,"officet","bsnl");
		//prepare UserInfo object
		UserInfo info=new UserInfo("ramesh","hyd");
		info.setPhones(Set.of(phones, phones1));
		
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
				Set<PhoneNumber> child=user.getPhones();
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
	public void addNewChildToExistingParent() {
		boolean added=false;
		//get session
		ses=HibernateUtil.getSession();
		
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//load object
			UserInfo user=ses.get(UserInfo.class, 1);
			//get all existing child of a parent
			Set<PhoneNumber> phones=user.getPhones();
			//prepare new phone object to add
			PhoneNumber ph=new PhoneNumber(645284964L, "personal", "jio");
			//add phone into parent
			phones.add(ph);
			added=true;			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(added) {
				tx.commit();
				System.out.println("new child saved successfully");
			}
			else {
				tx.rollback();
				System.out.println("new child is not added");
			}
		}//finally
	}//method

	@Override
	public void deletingAParentAndItsChild() {
		
		boolean deleted=false;
		//get session
		ses=HibernateUtil.getSession();
		
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//load parent
			UserInfo user=ses.get(UserInfo.class, 4);
			//delete parent
			ses.delete(user);			
			deleted=true;			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(deleted) {
				tx.commit();
				System.out.println("Parent record is deleted");
			}
			else {
				tx.rollback();
				System.out.println("Parent record is not deleted");
			}
		}//finally
	}//method

	@Override
	public void deleteAllParentAndItsChild() {
		
		boolean allDeleted=false;
		//get session
		ses=HibernateUtil.getSession();
		
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//load all parents record to delete
			//associated child object of those parent is loaded automatically
			Query<UserInfo> query=ses.createQuery("from com.ps.entity.UserInfo");
			List<UserInfo> list=query.getResultList();
			/*list.clear();*/		//this is not working
			list.forEach(user->{
				ses.delete(user);
			});
			allDeleted=true;			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(allDeleted) {
				tx.commit();
				System.out.println("All Parent and its Child records are deleted");
			}
			else {
				tx.rollback();
				System.out.println("All Parent and its Child records are not deleted");
			}
		}//finally		
	}//method

	@Override
	public void deleteAllParentAndItsChild1() {
		
		boolean allDeleted=false;
		int count=0;
		//get session
		ses=HibernateUtil.getSession();
		
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//load all parents record to delete
			//direct parent record deletion --> that throw exception
			Query query=ses.createQuery("delete from com.ps.entity.UserInfo on delete cascade");
			count=query.executeUpdate();
			allDeleted=true;			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(allDeleted) {
				tx.commit();
				System.out.println("All Parent and its Child records are deleted --> "+count);
			}
			else {
				tx.rollback();
				System.out.println("Problem in deleting all parent and its child");
			}
		}//finally	
	}

	@Override
	public void deleteAllChildsOfAParent() {

		boolean allDeleted=false;
		//get session
		ses=HibernateUtil.getSession();
		
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//load parent to delete all its child
			UserInfo user=ses.get(UserInfo.class, 2);
			//get all its child
			Set<PhoneNumber> phChild=user.getPhones();
			phChild.removeAll(phChild);			
			allDeleted=true;			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(allDeleted) {
				tx.commit();
				System.out.println("All Child records are deleted");
			}
			else {
				tx.rollback();
				System.out.println("Problem in deleting all its child");
			}			
		}//finally	
	}

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
			UserInfo user=ses.get(UserInfo.class, 3);
			//get all child from its parent
			Set<PhoneNumber> childs=user.getPhones();
			//load one child that is to be deleted
			PhoneNumber ph=ses.get(PhoneNumber.class, 275);
			childs.remove(ph);			
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

	@Override
	public void transferOneChildOfOneParentToAnotherParent() {

		boolean transferedOne=false;
		//get session
		ses=HibernateUtil.getSession();
		
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//get source parent object 
			UserInfo user1=ses.get(UserInfo.class, 4);
			//get all child from source parent
			Set<PhoneNumber> child1User=user1.getPhones();
			//load one child that is to be deleted
			PhoneNumber ph1=ses.get(PhoneNumber.class, 277);
			
			//load destination parent object
			UserInfo user2=ses.get(UserInfo.class, 5);
			//get all child from destination parent
			Set<PhoneNumber> child2User=user2.getPhones();
			//add parent user1 child to user2 parent
			child2User.add(ph1);	//no need of calling child1User.remove(ph1);		
			ses.flush();
			transferedOne=true;			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(transferedOne) {
				tx.commit();
				System.out.println("Specific child records are transferred");
			}
			else {
				tx.rollback();
				System.out.println("Problem in transferred specific child");
			}			
		}//finally
	}//method

}//class
