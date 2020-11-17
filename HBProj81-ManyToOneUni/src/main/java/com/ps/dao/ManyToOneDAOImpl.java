package com.ps.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.Department;
import com.ps.entity.EmpDetails;
import com.ps.utility.HibernateUtil;

public class ManyToOneDAOImpl implements ManyToOneDAO {

	@Override
	public void saveDataUsingChild() {

		Session ses=null;
		Transaction tx=null;
		boolean saved=false;
				
		//get session obj
		ses=HibernateUtil.getSession();
		//prepare parent object
		Department depart=new Department("IT", "Hyd", 5);
		//prepare child obj
		EmpDetails emp1=new EmpDetails("Ganesh", "Delhi", 45000.0f);
		EmpDetails emp2=new EmpDetails("Vikash", "Mumbai", 55000.0f);
		EmpDetails emp3=new EmpDetails("Raja", "Hyd", 51000.0f);
		emp1.setDept(depart);emp2.setDept(depart);emp3.setDept(depart);
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//save obj
			ses.save(emp1);
			ses.save(emp2);
			ses.save(emp3);
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
				System.out.println("Object not saved");
			}			
		}//finally
	}//method

	@Override
	public void loadDataUsingChild() {

		Session ses=null;
		Transaction tx=null;
				
		//get session obj
		ses=HibernateUtil.getSession();
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//load obj
			Query query=ses.createQuery("from com.ps.entity.EmpDetails");
			List<EmpDetails> list=query.getResultList();
			list.forEach(emp->{
				System.out.println(emp.getEno()+" "+emp.getEname()+" "+emp.getEadd()+" "+emp.getSalary());
				Department dept=emp.getDept();
				System.out.println(dept.getDno()+" "+dept.getDname()+" "+dept.getLocation()+" "+dept.getDsize());
			});
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}		
	}//method

	@Override
	public void deleteAllChildsAndItsParent() {

		Session ses=null;
		Transaction tx=null;
		boolean deleted=false;
				
		//get session obj
		ses=HibernateUtil.getSession();
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//load obj
			Query query=ses.createQuery("from com.ps.entity.EmpDetails");
			List<EmpDetails> list=query.getResultList();
			/*list.forEach(emp->{
				ses.delete(emp);
			});*/
			for(EmpDetails emp: list) {
				ses.delete(emp);
			}
			
			deleted=true;
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(deleted) {
				tx.commit();
				System.out.println("All chids and its parents are deleted");
			}
			else {
				tx.rollback();
				System.out.println("Problem in deleting all childs and its parent");
			}			
		}//finally		
	}//method

	@Override
	public void deleteASpecificChild() {

		Session ses=null;
		Transaction tx=null;
		boolean deleted=false;
				
		//get session obj
		ses=HibernateUtil.getSession();
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//load obj to delete
			EmpDetails emp=ses.get(EmpDetails.class, 1);
			ses.delete(emp);						
			deleted=true;
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(deleted) {
				tx.commit();
				System.out.println("Childs is deleted");
			}
			else {
				tx.rollback();
				System.out.println("Problem in deleting a Child");
			}			
		}//finally		
	}//method

	@Override
	public void deleteOneChildFromParent() {

		Session ses=null;
		Transaction tx=null;
		boolean deleted=false;
		int count=0;
				
		//get session obj
		ses=HibernateUtil.getSession();
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//load obj to delete
			Query query=ses.createQuery("DELETE FROM EmpDetails WHERE eno=:no");
			//set query param
			query.setParameter("no", 2);
			count=query.executeUpdate();			
			deleted=true;
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(deleted) {
				tx.commit();
				System.out.println(count+" Child is deleted");
			}
			else {
				tx.rollback();
				System.out.println("Problem in deleting a Child");
			}			
		}//finally
	}

}
