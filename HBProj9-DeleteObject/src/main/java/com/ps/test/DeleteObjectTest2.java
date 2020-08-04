package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.Product;
import com.ps.utility.HibernateUtil;

public class DeleteObjectTest2 {

	public static void main(String[] args) {
		Session ses=null;
		Transaction tx=null;
		Product prod=null;
		boolean flag=false;
		
		//get session object from HibernateUtil class
		ses=HibernateUtil.getSession();
		try {
			//begin Tx
			tx=ses.beginTransaction();
			prod=ses.get(Product.class, 360L);
			if(prod!=null) {
				//delete record
				ses.delete(prod);
				flag=true;
			}
			else {
				System.out.println("Record not found to delete");
				return;
			}			
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(flag==true) {
				tx.commit();
				System.out.println("Object deleted");
			}
			else {
				tx.rollback();
				System.out.println("Object not deleted");
			}
		}//finally
		
		//close session
		HibernateUtil.closeSession(ses);
		//close SessionFactory
		HibernateUtil.closeSessionFactory();

	}

}
