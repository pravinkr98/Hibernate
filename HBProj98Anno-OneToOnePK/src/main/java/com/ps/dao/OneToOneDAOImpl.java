package com.ps.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.CanteenCard;
import com.ps.entity.Employee;
import com.ps.utility.HibernateUtil;

public class OneToOneDAOImpl implements IOneToOneDAO {

	@Override
	public void saveDataUsingParent() {

		//get session obj
		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		boolean saved=false;
		//prepare object 
		Employee emp1=new Employee("Ramesh", "Pune");
		CanteenCard card=new CanteenCard("Militry", "gold", "Hyd");
		card.setEmpDetails(emp1);
		emp1.setCardDetails(card);
		try {
			//create or locate tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//save data using parent
			ses.save(emp1);
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
