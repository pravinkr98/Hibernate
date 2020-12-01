package com.ps.test;

import com.ps.dao.IManyToManyDAO;
import com.ps.dao.ManyToManyDAOImpl;
import com.ps.utility.HibernateUtil;

public class ManyToManyIdBagTagTest {

	public static void main(String[] args) {

		//get dao object
		IManyToManyDAO dao=new ManyToManyDAOImpl(); 
		//invoke method
		dao.saveDataUsingParent();
		
		
		//close SessionFactory
		HibernateUtil.closeSessionFactory();
	}//main
}//class
