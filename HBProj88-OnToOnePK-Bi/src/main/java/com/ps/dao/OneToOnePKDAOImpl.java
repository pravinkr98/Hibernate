package com.ps.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.LibraryMembership;
import com.ps.entity.Student;
import com.ps.utility.HibernateUtil;

public class OneToOnePKDAOImpl implements IOneToOnePKDAO {

	Session ses=null;
	Transaction tx=null;
	
	@Override
	public void saveDataUsingParent() {

		//get session object
		ses=HibernateUtil.getSession();
		boolean saved=false;
		//prepare object to save
		Student st=new Student("Ravi", "Muz");
		LibraryMembership lb=new LibraryMembership("gold", LocalDate.of(2013, 11, 20), LocalDate.of(2023, 11, 20));
		lb.setStudentDetails(st);
		st.setLibraryDetails(lb);
		try {
			//create or locate tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//save object using parent
			ses.save(lb);
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
				System.out.println("Object saved");
			}
			else {
				tx.rollback();
				System.out.println("Object not saved");
			}
		}//finally
	}//method
	
	@Override
	public void saveDataUsingChild() {

		//get session object
		ses=HibernateUtil.getSession();
		boolean saved=false;
		//prepare object to save
		Student st=new Student("Himesh", "Hyd");
		LibraryMembership lb=new LibraryMembership("silver", LocalDate.of(2014, 11, 20), LocalDate.of(2024, 11, 20));
		lb.setStudentDetails(st);
		st.setLibraryDetails(lb);
		try {
			//create or locate tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//save object using child
			ses.save(st);
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
				System.out.println("Object saved");
			}
			else {
				tx.rollback();
				System.out.println("Object not saved");
			}
		}//finally		
	}//method
	
	@Override
	public void loadDataUsingParent() {

		//get session object
		ses=HibernateUtil.getSession();
		try {
			//create or locate tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//load object using parent
			Query query=ses.createQuery("from com.ps.entity.LibraryMembership");
			List<LibraryMembership> list=query.getResultList();
			list.forEach(libr->{
				System.out.println("Parent :: "+libr);
				Student std=libr.getStudentDetails();
				System.out.println("Child :: "+std);
			});			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}//method

}//class
