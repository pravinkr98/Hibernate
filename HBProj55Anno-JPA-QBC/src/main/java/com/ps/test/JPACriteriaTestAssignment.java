package com.ps.test;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.Project;
import com.ps.utility.HibernateUtil;

public class JPACriteriaTestAssignment {

	public static void main(String[] args) {

		Session ses=null;
		Transaction tx=null;
		
		//get session object
		ses=HibernateUtil.getSession();
		//======== (Select * from Project where projName like '___') =========
		/*try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//create CriteriaBuilder object
			CriteriaBuilder builder=ses.getCriteriaBuilder();
			//create CriteriaQuery object
			CriteriaQuery ctQuery=builder.createQuery(Project.class);
			//create Root object specifying entity class /table name from which records should be selected
			Root<Project> root=ctQuery.from(Project.class);
			//add root object to CriteriaBuilder
			ctQuery.select(root).where(builder.like(root.get("projName"), "___"));
														
			//prepare query object having ctQuery object
			Query<Project> query=ses.createQuery(ctQuery);
			//execute JPA-QBC query
			List<Project> list=query.getResultList();
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
		
		//======== (Select * from Project where teamSize=(Select max(teamSize) from Project) ) =========
				try {
					//create or locate Tx
					if(!ses.getTransaction().isActive())
						tx=ses.beginTransaction();
										
					//create CriteriaBuilder object
					CriteriaBuilder builder=ses.getCriteriaBuilder();
					//create CriteriaQuery object
					CriteriaQuery<Integer> ctQuery=builder.createQuery(Integer.class);	
					CriteriaQuery<Project> ctQuery1=builder.createQuery(Project.class);	
					
					
					//create Root object specifying entity class /table name from which records should be selected
					Root<Project> root=ctQuery.from(Project.class);
					Root<Project> root1=ctQuery1.from(Project.class);
					
					ParameterExpression<Integer> param1=builder.parameter(Integer.class);
					
					ctQuery.multiselect(builder.max(root.get("teamSize")));
					ctQuery1.select(root1).where(builder.equal(root1.get("teamSize"), param1));
					//prepare query object having ctQuery object
					Query<Integer> query=ses.createQuery(ctQuery);
					Query<Project> query1=ses.createQuery(ctQuery1);
					//execute JPA-QBC query
					
					Integer count=query.uniqueResult();
					//set param
					query1.setParameter(param1, count);
					List<Project> list=query1.getResultList();
					//process result
					System.out.println("Maximum Team size :: "+count);
					list.forEach(System.out::println);
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