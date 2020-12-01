package com.ps.test;

import com.ps.dao.IManyToOneDAO;
import com.ps.dao.ManyToOneDAOImpl;
import com.ps.utility.HibernateUtil;

public class ManyToOneTest {

	public static void main(String[] args) {

		//create dao object
		IManyToOneDAO dao=new ManyToOneDAOImpl();
		//invoke method
		dao.saveUsingParent();
		
		//close sessionFactory
		HibernateUtil.closeSessionFactory();
	}//main
}//class
