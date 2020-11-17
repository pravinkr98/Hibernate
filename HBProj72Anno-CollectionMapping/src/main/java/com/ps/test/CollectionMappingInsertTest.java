package com.ps.test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.Employee;
import com.ps.utility.HibernateUtil;

public class CollectionMappingInsertTest {

	public static void main(String[] args) {
		
		Session ses=null;
		Transaction tx=null;
		boolean saved=false;
		
		//get session obj
		ses=HibernateUtil.getSession();
		try {
			//begin tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			
			//prepare employee obj
			Employee emp=new Employee();
			emp.setEname("Ganesh");emp.setAddrs("hyd");
			emp.setFriends(List.of("ramesh", "john", "ramesh"));
			emp.setRelativeList(List.of("Tiwari", "Sharma", "Patel"));
			emp.setPhones(Set.of(76853972L, 873548452L, 65738649L, 846574773L));
			emp.setAccounts(Map.of("SBI", 43627648L, "HDFC", 875100034L, "ICICI", 1300028048L));
			//save emp obj
			ses.save(emp);
			saved=true;			
		}//try
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(saved) {
				tx.commit();
				System.out.println("Object is saved");
			}
			else {
				tx.rollback();
				System.out.println("Object is not saved");
			}
			//close SessionFactory
			HibernateUtil.closeSessionFactory();
		}//finally
		
	}//main
}//class