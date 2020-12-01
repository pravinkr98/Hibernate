package com.ps.dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.Passport;
import com.ps.entity.Person;
import com.ps.utility.HibernateUtil;

public class OneToOneFKDAOImpl implements IOneToOneFKDAO {

	Session ses=null;
	Transaction tx=null;
	
	@Override
	public void saveData() {

		boolean saved=false;
		//get session object
		ses=HibernateUtil.getSession();
		//prepare person object having passport for save
		Person per=new Person("Ramesh", "Hyd");
		Passport pspt=new Passport("India", LocalDate.of(2023, 1, 26));
		pspt.setPersonDetails(per);
		//prepare another person without passport
		Person per1=new Person("Jani", "Patna");
		try {
			//create or locate tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//save
			ses.save(pspt);
			ses.save(per1);
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
			//load data
			Query query=ses.createQuery("from com.ps.entity.Passport");
			List<Passport> list=query.getResultList();
			list.forEach(pspt->{
				System.out.println(pspt);
				Person person=pspt.getPersonDetails();
				System.out.println(person);
			});			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}//method
	
	@Override
	public void deleteDataUsingParent() {

		boolean deleted=false;
		//get session object
		ses=HibernateUtil.getSession();
		try {
			//create or locate tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//load data to delete
			Passport pspt=ses.get(Passport.class, 317L);
			ses.remove(pspt);			
			deleted=true;
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(deleted) {
				tx.commit();
				System.out.println("Object deleted");
			}
			else {
				tx.rollback();
				System.out.println("Object not deleted");
			}
		}//finally	
	}

}//class
