package com.ps.test;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.Project;
import com.ps.utility.HibernateUtil;

public class JPACriteriaScalarTest {

	public static void main(String[] args) {

		Session ses=null;
		Transaction tx=null;
		
		//get session object
		ses=HibernateUtil.getSession();
		//================== executing scalar query (Select projId,projName from Project where location='hyd') ====================
		/*try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//create CriteriaBuilder object
			CriteriaBuilder builder=ses.getCriteriaBuilder();
			//create CriteriaQuery object
			CriteriaQuery<Object[]> ctQuery=builder.createQuery(Object[].class);			
			//create Root object specifying entity class /table name from which records should be selected
			Root<Project> root=ctQuery.from(Project.class);
			//add root object to CriteriaBuilder
			ctQuery.multiselect(root.get("projId"),root.get("projName")).	//select projId,projName from Project
							where(builder.equal(root.get("location"), "Hyd"));	//where location='Hyd'
						
			//prepare query object having ctQuery object
			Query<Object[]> query=ses.createQuery(ctQuery);
			//execute JPA-QBC query
			List<Object[]> list=query.getResultList();
			//process result
			list.forEach(row->{
				for(Object obj:row) {
					System.out.print(obj+" ");
				}
				System.out.println();
			});			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close SessionFactory
			HibernateUtil.closeSessionFactory();
		}*/
		
		//================== executing scalar query (Select projName from Project where cost between(100000,200000) order by projName asc) ====================
		/*try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//create CriteriaBuilder object
			CriteriaBuilder builder=ses.getCriteriaBuilder();
			//create CriteriaQuery object
			CriteriaQuery<String> ctQuery=builder.createQuery(String.class);			
			//create Root object specifying entity class /table name from which records should be selected
			Root<Project> root=ctQuery.from(Project.class);
			//add select and where clause
			ctQuery.multiselect(root.get("projName")).
								where(builder.between(root.get("cost"), 100000, 200000)).
								orderBy(builder.asc(root.get("projName")));
			
			//prepare query object having ctQuery object
			Query<String> query=ses.createQuery(ctQuery);
			//execute JPA-QBC query
			List<String> list=query.getResultList();
			//process result
			list.forEach(System.out::println);
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close SessionFactory
			HibernateUtil.closeSessionFactory();
		}*/

		//================== executing scalar query (getting aggregate result) ====================
		//---------------------- (Select count(*) from Project) ------------------------
			try {
				//create or locate Tx
				if(!ses.getTransaction().isActive())
					tx=ses.beginTransaction();
				//create CriteriaBuilder object
				CriteriaBuilder builder=ses.getCriteriaBuilder();
				//create CriteriaQuery object
				CriteriaQuery<Long> ctQuery=builder.createQuery(Long.class);			
				//create Root object specifying entity class /table name from which records should be selected
				Root<Project> root=ctQuery.from(Project.class);
				ctQuery.multiselect(builder.count(root.get("projName")));
				//prepare query object having ctQuery object
				Query<Long> query=ses.createQuery(ctQuery);
				//execute JPA-QBC query
				
				//List<Long> list=query.getResultList();
				Long count=query.uniqueResult();
				//process result
				//System.out.println("Total records :: "+list.get(0));	
				System.out.println("Total records :: "+count);				
			}
			catch (HibernateException he) {
				he.printStackTrace();
			}
			finally {
				//close SessionFactory
				HibernateUtil.closeSessionFactory();
			}
	}//main
}//class