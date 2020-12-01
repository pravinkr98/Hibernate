package com.ps.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.Doctor;
import com.ps.entity.Patient;
import com.ps.utility.HibernateUtil;

public class ManyToManyDAOImpl implements IManyToManyDAO {
	
	Session ses=null;
	Transaction tx=null;

	@Override
	public void saveDataUsingParent() {
		
		boolean saved=false;
		//get session object
		ses=HibernateUtil.getSession();
		//prepare Doctor(parent) object
		Doctor d1=new Doctor("Mr. Patil", "Patil Hospital", "MBBS");
		Doctor d2=new Doctor("Mr. Sinha", "Sinha Hospital", "MBBS");
		//prepare Patient(Child) object
		Patient p1=new Patient("Himesh", "cancer");
		Patient p2=new Patient("Anita", "corona");
		Patient p3=new Patient("Patel", "fever");
		Patient p4=new Patient("Sohan", "faleria");
		//give patient to doctor
		d1.setPatients(List.of(p1,p2));
		d2.setPatients(List.of(p1,p2,p3,p4));
		//allote doctor to patient
		p1.setDoctors(Set.of(d1,d2));
		p2.setDoctors(Set.of(d1,d2));
		p3.setDoctors(Set.of(d2));
		p4.setDoctors(Set.of(d2));
		
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//save record using parent object
			ses.save(d1);
			ses.save(d2);
			saved=true;			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(saved) {
				tx.commit();
				System.out.println("Records are saved using Doctor(parent)");
			}
			else {
				tx.rollback();
				System.out.println("Records are not saved");
			}
		}//finally
	}//method
	
	
}//class
