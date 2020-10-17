package com.ps.test;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.Project;
import com.ps.utility.HibernateUtil;

public class JPACriteriaNonSelectTest {

	public static void main(String[] args) {

		Session ses=null;
		Transaction tx=null;
		int count=0;
		boolean updated=false;
		
		//get session object
		ses=HibernateUtil.getSession();
		//---------------------- (Update Project set teamSize=?,location=? where cost>=?) ------------------------
		/*	try {
				//create or locate Tx
				if(!ses.getTransaction().isActive())
					tx=ses.beginTransaction();
				//create CriteriaBuilder object
				CriteriaBuilder builder=ses.getCriteriaBuilder();
				//create CriteriaQuery object
				CriteriaUpdate<Project> ctUpdate=builder.createCriteriaUpdate(Project.class);			
				//create Root object specifying entity class /table name from which records should be selected
				Root<Project> root=ctUpdate.from(Project.class);	//update project
				ctUpdate.set(root.get("teamSize"), 21).//set teamSize=21
						set(root.get("location"),"Hyd").	//set location='Hyd'
							where(builder.ge(root.get("cost"), 200000)); //where cost>=200000
				
				//prepare query object having ctQuery object
				Query<Long> query=ses.createQuery(ctUpdate);
				//execute JPA-QBC query				
				count=query.executeUpdate();
				updated=true;
			}
			catch (HibernateException he) {
				he.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				//commit or rollback Tx
				if(updated) {
					tx.commit();
					System.out.println("Total records affected :: "+count);				
				}
				else {
					tx.rollback();
					System.out.println("No record found for update");
				}					
				//close SessionFactory
				HibernateUtil.closeSessionFactory();
			}*/
		
		//---------------------- (Delete from Project where cost between(?,?) ------------------------
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//create CriteriaBuilder object
			CriteriaBuilder builder=ses.getCriteriaBuilder();
			//create CriteriaQuery object
			CriteriaDelete<Project> ctDelete=builder.createCriteriaDelete(Project.class);			
			//create Root object specifying entity class /table name from which records should be selected
			Root<Project> root=ctDelete.from(Project.class);	//Delete from Project
			ctDelete.where(builder.between(root.get("cost"),10000,100000));	//where cost between(10000,100000)
			
			//prepare query object having ctQuery object
			Query<Long> query=ses.createQuery(ctDelete);
			//execute JPA-QBC query				
			count=query.executeUpdate();
			updated=true;
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//commit or rollback Tx
			if(updated) {
				tx.commit();
				System.out.println("Total records deleted :: "+count);				
			}
			else {
				tx.rollback();
				System.out.println("No record found for delete");
			}					
			//close SessionFactory
			HibernateUtil.closeSessionFactory();
		}
		
	}//main
}//class