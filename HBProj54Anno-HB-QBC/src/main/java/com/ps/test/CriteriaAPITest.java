package com.ps.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import com.ps.entity.Project;
import com.ps.utility.HibernateUtil;

public class CriteriaAPITest {

	public static void main(String[] args) {

		Session ses=null;
		Transaction tx=null;
		Criteria criteria=null;
		List<Project> list=null;
		
		//get Session obj
		ses=HibernateUtil.getSession();
		try {
			//Locate/begin Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//set criteria
			criteria=ses.createCriteria(Project.class);
			//execute QBC logic
			list=criteria.list();
			//process the result
			list.forEach(System.out::println);
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close SessionFactory
			HibernateUtil.closeSessionFactory();
		}
		
		//=================== Pagination =============================
		/*try {
			//Locate/begin Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//set criteria
			criteria=ses.createCriteria(Project.class);
			//Pagination setting
			criteria.setFirstResult(1);
			criteria.setMaxResults(3);
			//execute QBC logic
			list=criteria.list();
			//process the result
			list.forEach(System.out::println);
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close SessionFactory
			HibernateUtil.closeSessionFactory();
		}*/
		
		//==============================================================
		/*try {
			//Locate/begin Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//prepare criteria obj
			criteria=ses.createCriteria(Project.class);
			//prepare criterian object
			Criterion cond1=Restrictions.ge("teamSize", 12);
			Criterion cond2=Restrictions.le("teamSize", 16);
			//prepare order
			Order order=Order.desc("projName");
			Order order1=Order.asc("location");
			//add criterion condition to criteria obj
			criteria.add(cond1);criteria.add(cond2);
			criteria.addOrder(order);
			criteria.addOrder(order1);
			//execute QBC logic
			list=criteria.list();
			//process the result
			list.forEach(System.out::println);
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close SessionFactory
			HibernateUtil.closeSessionFactory();
		}*/
		
		//========Adding multiple conditions (Criterion objects) with OR and AND clauses to Criteria object ============
		/*		try {
					//create or Locate Tx
					if(!ses.getTransaction().isActive())
						tx=ses.beginTransaction();
					//prepare criteria obj
					criteria=ses.createCriteria(Project.class);
					//prepare criterian object
					Criterion cond1=Restrictions.between("teamSize", 10, 16);
					Criterion cond2=Restrictions.in("company", "HCL","WIPRO");
					//Criterion cond2=Restrictions.in("company", List.of("HCL","WIPRO"));
					Criterion cond3=Restrictions.ilike("projName", "o%");
					//create Criterion object having and clause b/w cond1,cond2
					Criterion finalCond=Restrictions.or(Restrictions.and(cond1,cond2),cond3);
					//add criterion condition to criteria obj
					criteria.add(finalCond);					
					//execute QBC logic
					list=criteria.list();
					//process the result
					list.forEach(System.out::println);
				}//try
				catch (HibernateException he) {
					he.printStackTrace();
				}
				finally {
					//close SessionFactory
					HibernateUtil.closeSessionFactory();
				}*/
		
		//========Adding multiple conditions (Criterion objects) with OR and AND clauses to Criteria object ============
		/*try {
			//create or Locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//prepare criteria obj
			criteria=ses.createCriteria(Project.class);
			//prepare criterian object
			Criterion cond=Restrictions.sqlRestriction("rownum>=? and rownum<=?", new Object[] {1,3}, new Type[] {StandardBasicTypes.INTEGER,StandardBasicTypes.INTEGER});
			//add criterion condition to criteria obj
			criteria.add(cond);					
			//execute QBC logic
			list=criteria.list();
			//process the result
			list.forEach(System.out::println);
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close SessionFactory
			HibernateUtil.closeSessionFactory();
		}*/
	}//main
}//class
