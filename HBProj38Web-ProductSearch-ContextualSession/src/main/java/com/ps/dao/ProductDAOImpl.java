package com.ps.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.Product;
import com.ps.utility.HibernateUtil;

public class ProductDAOImpl implements ProductDAO {

	@Override
	public Product getProduct(int pid) {
		Session ses=null,ses1=null;
		Product prod=null;
		Transaction tx=null;
		// get session object
		ses=HibernateUtil.getSession();
		ses1=HibernateUtil.getSession();
		tx=ses.beginTransaction();
		//get object
		prod=ses.get(Product.class, pid);
		System.out.println(ses.hashCode()+"    "+ses1.hashCode());
		
		return prod;
	}

}
