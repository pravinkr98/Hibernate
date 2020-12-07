package com.ps.dao;

import java.time.LocalDate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.Passport;
import com.ps.entity.Person;
import com.ps.utility.HibernateUtil;

public class OneToOneDAOImpl implements IOneToOneDAO {

	@Override
	public void saveDataUsingParent() {

		//get session obj
		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		boolean saved=false;
		//prepare object 
		Person person1=new Person("Amit", "Patna");
		Passport pspt=new Passport("Domestic", "India", LocalDate.of(2030, 12, 3));
		pspt.setPersonDetails(person1);
		Person person2=new Person("Rakesh", "Hyd");
		try {
			//create or locate tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//save data using parent
			ses.save(pspt);
			ses.save(person2);
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
				System.out.println("Object is saved");
			}
			else {
				tx.rollback();
				System.out.println("Object is not saved");
			}
		}//finally
	}//method

}//class
