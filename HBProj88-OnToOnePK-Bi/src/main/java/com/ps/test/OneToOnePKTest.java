package com.ps.test;

import com.ps.dao.IOneToOnePKDAO;
import com.ps.dao.OneToOnePKDAOImpl;
import com.ps.utility.HibernateUtil;

public class OneToOnePKTest {

	public static void main(String[] args) {

		//create dao object
		IOneToOnePKDAO dao=new OneToOnePKDAOImpl();
		//invoke method
		//dao.saveDataUsingParent();
		//dao.saveDataUsingChild();
		dao.loadDataUsingParent();
		
		//close SessionFactory object
		HibernateUtil.closeSessionFactory();
	}//main
}//class
