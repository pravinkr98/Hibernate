package com.ps.dao;

import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.Company;
import com.ps.entity.Project;
import com.ps.utility.HibernateUtil;

public class ManyToOneDAOImpl implements IManyToOneDAO {

	@Override
	public void saveDataUsingParent() {

		//get session object
		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		boolean saved=false;
		
		//prepare object
		Company comp=new Company("HCL", "hyd", "IT");
		Project proj1=new Project("FedEx", "financial", 10);
		Project proj2=new Project("Aadhar", "portfolio", 15);
		//set child to parent
		comp.setProjects(Set.of(proj1, proj2));
		//set parent to child
		proj1.setCompany(comp);
		proj2.setCompany(comp);
		try {
			//create or locate tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//save object using parent
			ses.save(comp);
			saved=true;
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//perform Tx Mgmt
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
	
	@Override
	public void saveDataUsingChild() {
		
		//get session object
		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		boolean saved=false;
		
		//prepare object
		Company comp=new Company("Wipro", "Chennai", "IT");
		Project proj1=new Project("CitiBank", "financial", 12);
		Project proj2=new Project("Census", "govt", 10);
		//set child to parent
		comp.setProjects(Set.of(proj1, proj2));
		//set parent to child
		proj1.setCompany(comp);
		proj2.setCompany(comp);
		try {
			//create or locate tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//save object using child
			ses.save(proj1);
			ses.save(proj2);
			saved=true;
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//perform Tx Mgmt
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
