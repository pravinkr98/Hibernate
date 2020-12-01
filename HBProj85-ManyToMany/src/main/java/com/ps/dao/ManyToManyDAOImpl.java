package com.ps.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
		d1.setPatients(Set.of(p1,p2));
		d2.setPatients(Set.of(p1,p2,p3,p4));
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
	
	@Override
	public void saveDataUsingChild() {
		
		boolean saved=false;
		//get session object
		ses=HibernateUtil.getSession();
		//prepare Doctor(parent) object
		Doctor d1=new Doctor("Mr. Natrajan", "NIT Hospital", "MBBS");
		Doctor d2=new Doctor("Mr. Durga", "Durga Hospital", "MBBS");
		//prepare Patient(Child) object
		Patient p1=new Patient("Ravi", "corona");
		Patient p2=new Patient("Anjali", "heart");
		Patient p3=new Patient("Vivek", "diabatese");
		Patient p4=new Patient("Nutan", "throat");
		//give patient to doctor
		d1.setPatients(Set.of(p1,p2));
		d2.setPatients(Set.of(p1,p2,p3,p4));
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
			ses.save(p1);
			ses.save(p2);
			ses.save(p3);
			ses.save(p4);
			saved=true;			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(saved) {
				tx.commit();
				System.out.println("Records are saved using Patient(child)");
			}
			else {
				tx.rollback();
				System.out.println("Records are not saved");
			}
		}//finally		
	}//method
	
	@Override
	public void loadDataUsingParent() {

		//get session obj
		ses=HibernateUtil.getSession();
		try {
			//create or locate tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//prepare JPQBC object(CriteriaBuilder)
			CriteriaBuilder builder=ses.getCriteriaBuilder();
			//prepare CriteriaQuery object
			CriteriaQuery<Doctor> ctQuery=builder.createQuery(Doctor.class);
			//prepare root object
			Root<Doctor> root=ctQuery.from(Doctor.class);
			root.fetch("patients",JoinType.INNER);
			//add root object criteria query
			ctQuery.select(root);
			//prepare query object
			Query<Doctor> query=ses.createQuery(ctQuery);
			//get data
			List<Doctor> list=query.getResultList();
			list.forEach(doctor->{
				System.out.println("Parent:: "+doctor);
				Set<Patient> child=doctor.getPatients();
				child.forEach(ph->System.out.println("Child:: "+ph));
			});
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
	}//method
	
	@Override
	public void deleteAllChildsOfAParent() {

		boolean deleted=false;
		//get session obj
		ses=HibernateUtil.getSession();
		try {
			//create or locate tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			Doctor doctor=ses.get(Doctor.class, 1);
			Set<Patient> childs=doctor.getPatients();
			childs.removeAll(childs);			
			deleted=true;
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(deleted) {
				tx.commit();
				System.out.println("All childs are deleted");
			}
			else {
				tx.rollback();
				System.out.println("Problem in deleting all child");
			}
		}//finally
	}//method
	
	@Override
	public void deleteSpecificChildFromSpecificAParent() {

		boolean deleted=false;
		//get session obj
		ses=HibernateUtil.getSession();
		try {
			//create or locate tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			Doctor doctor=ses.get(Doctor.class, 1);
			Set<Patient> childs=doctor.getPatients();
			Patient pat=ses.get(Patient.class, 1001);
			childs.remove(pat);		
			deleted=true;
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(deleted) {
				tx.commit();
				System.out.println("Specific child is deleted");
			}
			else {
				tx.rollback();
				System.out.println("Problem in deleting specific child");
			}
		}//finally
	}

}//class
