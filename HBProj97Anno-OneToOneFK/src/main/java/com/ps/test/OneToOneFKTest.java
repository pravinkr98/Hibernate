package com.ps.test;

import com.ps.dao.IOneToOneDAO;
import com.ps.dao.OneToOneDAOImpl;
import com.ps.utility.HibernateUtil;

public class OneToOneFKTest {

	public static void main(String[] args) {

		//create dao object
		IOneToOneDAO dao=new OneToOneDAOImpl();
		//invoke method
		dao.saveDataUsingParent();
		
		//close SessionFactory
		HibernateUtil.closeSessionFactory();
	}//main
}//class
