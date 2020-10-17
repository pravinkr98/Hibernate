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

public class CriteriaAPITestAssignment {

	public static void main(String[] args) {

		Session ses=null;
		Transaction tx=null;
		Criteria criteria=null;
		
		//get Session obj
		ses=HibernateUtil.getSession();		
		//================ Getting specific multiple column values by applying Criterion condition using HB-QBC Projections ====================
				/*try {
					//Locate/begin Tx
					if(!ses.getTransaction().isActive())
						tx=ses.beginTransaction();
					//set criteria
					criteria=ses.createCriteria(Project.class);
					
					//prepare Projection for specific property
					Projection p1=Projections.property("projName");
					Projection p2=Projections.property("company");
					Projection p3=Projections.property("cost");
					//add projection to projectionList object
					ProjectionList pList=Projections.projectionList();
					//add projection to projectionList
					pList.add(p1);pList.add(p2);pList.add(p3);
					//set projection object to criteria object
					criteria.setProjection(pList);
					
					//prepare Criterion boject
					Criterion cond1=Restrictions.in("company", List.of("HCL","Infosys"));
					Criterion cond2=Restrictions.between("cost", 100000.0f, 200000.0f);
					//add criterion object to criteria object
					criteria.add(cond1);criteria.add(cond2);
					
					//prepare Order object
					Order order=Order.asc("company");
					//set order to criteria object
					criteria.addOrder(order);
					
					//execute QBC logic
					List<Object[]> list=criteria.list();
					//process the result
					list.forEach(row->{
						System.out.println(row[0]+"......."+row[1]+"........"+row[2]);
					});
				}//try
				catch (HibernateException he) {
					he.printStackTrace();
				}
				finally {
					//close SessionFactory
					HibernateUtil.closeSessionFactory();
				}*/
		
		//================ Getting multiple aggregate result using HB-QBC Projections ====================
		try {
			//Locate/begin Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//set criteria
			criteria=ses.createCriteria(Project.class);
			//prepare Projection for single property
			Projection p1=Projections.min("cost");
			Projection p2=Projections.max("cost");
			Projection p3=Projections.count("projName");
			Projection p4=Projections.sum("cost");
			//add projection to projectionList object
			ProjectionList pList=Projections.projectionList();
			//add projection to projectionList
			pList.add(p1);pList.add(p2);pList.add(p3);pList.add(p4);
			//set projection object to criteria object
			criteria.setProjection(pList);					
			//execute QBC logic
			List<Object[]> list=criteria.list();
			//process the result
			list.forEach(row->{
				System.out.println("Minimum Project cost :: "+row[0]);
				System.out.println("Maximum Project cost :: "+row[1]);
				System.out.println("No. of Projects :: "+row[2]);
				System.out.println("Total Project cost :: "+row[3]);
			});
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
