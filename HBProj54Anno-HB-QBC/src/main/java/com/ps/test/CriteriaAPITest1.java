package com.ps.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import com.ps.entity.Project;
import com.ps.utility.HibernateUtil;

public class CriteriaAPITest1 {

	public static void main(String[] args) {

		Session ses=null;
		Transaction tx=null;
		Criteria criteria=null;
		
		//get Session obj
		ses=HibernateUtil.getSession();
		//================ Getting single column values by using HB-QBC Projection ====================
		/*try {
			//Locate/begin Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//set criteria
			criteria=ses.createCriteria(Project.class);
			//prepare Projection for single property
			Projection p1=Projections.property("company");
			//prepare Criterion object for condition
			//Criterion cond1=Restrictions.eq("location", "Hyd");
			Criterion cond1=Restrictions.like("company", "W___o");
			//set projection object to criteria object
			criteria.setProjection(p1);
			//add criterion object to criteria object
			criteria.add(cond1);
			//execute QBC logic
			List<String> list=criteria.list();
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
		
		//================ Getting specific multiple column values by using HB-QBC Projections ====================
		/*		try {
					//Locate/begin Tx
					if(!ses.getTransaction().isActive())
						tx=ses.beginTransaction();
					//set criteria
					criteria=ses.createCriteria(Project.class);
					//prepare Projection for single property
					Projection p1=Projections.property("company");
					Projection p2=Projections.property("projId");
					//add projection to projectionList object
					ProjectionList pList=Projections.projectionList();
					//add projection to projectionList
					pList.add(p1);pList.add(p2);
					//set projection object to criteria object
					criteria.setProjection(pList);					
					//execute QBC logic
					List<Object[]> list=criteria.list();
					//process the result
					list.forEach(row->{
						System.out.println(row[0]+"......."+row[1]);
					});
				}//try
				catch (HibernateException he) {
					he.printStackTrace();
				}
				finally {
					//close SessionFactory
					HibernateUtil.closeSessionFactory();
				}*/
		
		//================ Getting single aggregate result using HB-QBC Projections ====================
		try {
			//Locate/begin Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//set criteria
			criteria=ses.createCriteria(Project.class);
			//prepare Projection for single property
			Projection p1=Projections.max("cost");
			//set projection object to criteria object
			criteria.setProjection(p1);					
			//execute QBC logic
			List<?> list=criteria.list();
			//process the result
			System.out.println("Maximum Project cost :: "+list.get(0));
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//close SessionFactory
			HibernateUtil.closeSessionFactory();
		}
		
	}//main
}//class
