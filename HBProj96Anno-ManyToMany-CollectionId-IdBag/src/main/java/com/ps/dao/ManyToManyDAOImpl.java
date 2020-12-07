package com.ps.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.Subscriber;
import com.ps.entity.TVChannel;
import com.ps.utility.HibernateUtil;

public class ManyToManyDAOImpl implements IManyToManyDAO {

	@Override
	public void saveDataUsingParent() {

		//get sesion
		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		boolean saved=false;
		//prepare object
		TVChannel zee=new TVChannel("ZeeTV", "Zee Entertainment", "India");
		TVChannel star=new TVChannel("StarPlus", "Star India Pvt Ltd","India");
		Subscriber ramesh=new Subscriber("Ramesh", "gold", "mumbai");
		Subscriber ganesh=new Subscriber("Gamesh", "silver", "delhi");
		Subscriber venkatesh=new Subscriber("Venkatesh", "premium", "hyd");
		//set child to parent
		zee.setSubscribers(List.of(ramesh, ganesh, venkatesh));
		star.setSubscribers(List.of(ganesh, venkatesh));
		//set parent to child
		ramesh.setChannels(List.of(zee));
		ganesh.setChannels(List.of(zee,star));
		venkatesh.setChannels(List.of(zee,star));
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//save object using parent
			ses.save(zee);
			ses.save(star);
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

		//get sesion
		Session ses=HibernateUtil.getSession();
		Transaction tx=null;
		boolean saved=false;
		//prepare object
		TVChannel sony=new TVChannel("SonyMax", "Sony Entertainment", "India");
		TVChannel utv=new TVChannel("UTVMovies", "UTV India Pvt Ltd","India");
		Subscriber john=new Subscriber("John", "gold", "pune");
		Subscriber raja=new Subscriber("Raja", "silver", "hyd");
		Subscriber hari=new Subscriber("Hari", "premium", "delhi");
		//set child to parent
		sony.setSubscribers(List.of(hari, john, raja));
		utv.setSubscribers(List.of(hari, raja));
		//set parent to child
		john.setChannels(List.of(sony));
		hari.setChannels(List.of(sony,utv));
		raja.setChannels(List.of(sony,utv));
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//save object using child
			ses.save(john);
			ses.save(raja);
			ses.save(hari);
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
				System.out.println("Objects are saved using child");
			}
			else {
				tx.rollback();
				System.out.println("Objects are not saved");
			}
		}//finally
	}//method
	
}//class
