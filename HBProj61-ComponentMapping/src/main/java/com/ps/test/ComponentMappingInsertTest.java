package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.Address;
import com.ps.entity.StudentInfo;
import com.ps.utility.HibernateUtil;

public class ComponentMappingInsertTest {

	public static void main(String[] args) {

		Session ses=null;
		Address addr1=null,addr2=null;
		StudentInfo info1=null,info2=null;
		Transaction tx=null;
		boolean saved=false;
		
		//get session obj
		ses=HibernateUtil.getSession();
		//prepare obj
		addr1=new Address();
		addr1.setDoorNo("225/156B");
		addr1.setStreetName("Kalinagar");
		addr1.setAreaName("Chandwara");
		addr1.setDistrict("Muzaffarpur");
		addr1.setState("Bihar");
		addr1.setCountry("India");
		addr1.setPincode(842001);
		
		addr2=new Address();
		addr2.setDoorNo("435/615C");
		addr2.setStreetName("Kachchi Saray");
		addr2.setAreaName("Maharaji Polhar");
		addr2.setDistrict("Muzaffarpur");
		addr2.setState("Bihar");
		addr2.setCountry("India");
		addr2.setPincode(842002);
		
		//prepare obj
		info1=new StudentInfo();
		info1.setSname("Ramesh");
		info1.setAvg(79.55f);
		info1.setAddrs(addr1);
		
		info2=new StudentInfo();
		info2.setSname("Vikash");
		info2.setAvg(82.66f);
		info2.setAddrs(addr2);
		
		try {
			//create or locate tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//save obj
			ses.save(info1);
			ses.save(info2);
			saved=true;			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//commit or rollback tx
			if(saved) {
				tx.commit();
				System.out.println("Record inserted");
			}
			else {
				tx.rollback();
				System.out.println("Record insertion failed");
			}
			//close sessionFactory
			HibernateUtil.closeSessionFactory();				
		}
		
	}//main
}//class