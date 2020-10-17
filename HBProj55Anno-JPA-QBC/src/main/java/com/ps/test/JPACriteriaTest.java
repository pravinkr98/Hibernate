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

public class JPACriteriaTest {

	public static void main(String[] args) {

		Session ses=null;
		Transaction tx=null;
		
		//get session object
		ses=HibernateUtil.getSession();
		//================== executing entity query (Select * from Project) ====================
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
			ctQuery.select(root);
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
		
		//======== (Select * from Project where projId>=? and projId<=? order by projName desc) =========
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
			ctQuery.select(root);
			
			//create Parameters
			ParameterExpression<Integer> param1=builder.parameter(Integer.class);
			ParameterExpression<Integer> param2=builder.parameter(Integer.class);
			//create predicate object representing conditions and specifying parameters
			Predicate pdc1=builder.ge(root.get("projId"), param1);	//projId>=?
			Predicate pdc2=builder.le(root.get("projId"), param2);	//projId<=?
			Predicate finalCond=builder.and(pdc1,pdc2);
			//add predicate object to criteriaQuery object as Where clause condition
			ctQuery.where(finalCond);	//where projId>=? and projId<=?
			//create order object
			Order order=builder.desc(root.get("projName"));
			//add order object to ctQuery
			ctQuery.orderBy(order);	// order by projName desc
			
			//prepare query object having ctQuery object
			Query<Project> query=ses.createQuery(ctQuery);
			//set query param value
			query.setParameter(param1, 1);
			query.setParameter(param2, 6);
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
		
		//======== (Select * from Project where projId>=? and projId<=? order by projName desc) =========
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
			ctQuery.select(root).where(
									builder.and(
											builder.ge(root.get("projId"), 30),
											builder.le(root.get("projId"), 40)
											)
									  ).orderBy(
											  builder.desc(root.get("projName")));
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
		
		
		//======== (Select * from Project where location in("Hyd","Delhi","Pune") order by projName desc) =========
		/*		try {
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
					ctQuery.select(root).where(root.get("location").in("Hyd","Delhi","Pune"))
										.orderBy(builder.desc(root.get("projName")));
																
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
		
		//======== (Select * from Project where teamSize between(10,20) and projName like 'o%') =========
		try {
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
			ctQuery.select(root).where(builder.and(builder.between(root.get("teamSize"), 10, 20),
												   builder.like(root.get("projName"), "O%"))
										);
														
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
		}
	}//main
}//class