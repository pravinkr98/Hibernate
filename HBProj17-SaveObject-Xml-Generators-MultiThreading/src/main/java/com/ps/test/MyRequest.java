package com.ps.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.Product;

public class MyRequest implements Runnable {
	private Session ses;
	private Product p;
	
	public MyRequest(Session ses,Product p) {
		this.ses=ses;
		this.p=p;
	}

	@Override
	public void run() {
		System.out.println("MyRequest.run()--> name :: "+Thread.currentThread().getName());
		Transaction tx=null;
		Integer idVal=0;
		boolean saved=false;
		
		try {
			//begin Tx
			tx=ses.beginTransaction();
			//save object
			idVal= (Integer) ses.save(p);
			saved=true;
			System.out.println("Generated id value is : "+idVal);
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(saved) {
				tx.commit();
				System.out.println("Product object is saved");
			}
			else {
				tx.rollback();
				System.out.println("Object not saved");
			}				
		}//finally
	}//run

}//class
