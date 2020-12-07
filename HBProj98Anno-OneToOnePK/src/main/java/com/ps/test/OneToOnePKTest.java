package com.ps.test;

import com.ps.dao.IOneToOneDAO;
import com.ps.dao.OneToOneDAOImpl;
import com.ps.utility.HibernateUtil;

public class OneToOnePKTest {

	public static void main(String[] args) {

		//create dao object
		IOneToOneDAO dao=new OneToOneDAOImpl();
		//invoke method
		dao.saveDataUsingParent();
		
		//close SessionFactory object
		HibernateUtil.closeSessionFactory();
	}//main
}//class
