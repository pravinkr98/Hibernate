package com.ps.test;

import java.util.List;

import org.hibernate.Cache;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.ps.entity.InsurancePolicy;
import com.ps.utility.HibernateUtil;

public class QueryCacheTest {

	public static void main(String[] args) {

		SessionFactory factory=HibernateUtil.getSessionFactory();
		Session ses=factory.openSession();
		try {
			Query query=ses.createQuery("from InsurancePolicy");
			query.setCacheable(true);
			query.setCacheRegion("reg1");
			List<InsurancePolicy> list=query.getResultList();	//collects from DB and keeps in query cache of L2 cache
			list.forEach(System.out::println);
			
			System.out.println("------------------------------------");
			list=query.getResultList();		//collects from query cache
			list.forEach(System.out::println);
			
			System.out.println("-------------------------------------");
			Cache cache=factory.getCache();
			cache.evictQueryRegion("reg1");		//removes the entities from query cache region "reg1"
			list=query.getResultList();
			list.forEach(System.out::println);
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			HibernateUtil.closeSessionFactory();
		}
	}//main
}//class
