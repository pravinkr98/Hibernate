package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.Employee;
import com.ps.entity.Person;
import com.ps.entity.Student;
import com.ps.utility.HibernateUtil;

public class InheritenceMappingInsertTest {

	public static void main(String[] args) {

		Session ses=null;
		Transaction tx=null;
		Person per=null;
		Student st=null;
		Employee emp=null;
		boolean saved=false;
		
		//get session obj
		ses=HibernateUtil.getSession();
		//prepare super class obj for save
		per=new Person();
		per.setName("Ramesh");per.setAddrs("hyd");
		
		//prepare object for sub class1..
		st=new Student();
		st.setName("Vikash");st.setAddrs("hyd");
		st.setCollege("IGNOU");st.setBranch("MCA");st.setAvg(70.0f);
		
		//prepare object for sub class2..
		emp=new Employee();
		emp.setName("Ganesh");emp.setAddrs("vizag");
		emp.setDesg("Developer");emp.setSalary(45000f);emp.setDeptNo(21);
		
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//save object
			ses.save(per);
			ses.save(st);
			ses.save(emp);
			saved=true;			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//perform TxMgmt
			if(saved) {
				tx.commit();
				System.out.println("Object is saved");
			}
			else {
				tx.rollback();
				System.out.println("Object is not saved");
			}
			//close sessionFactory
			HibernateUtil.closeSessionFactory();
		}//finally
		
	}//main
}//class