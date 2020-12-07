package com.ps.test;

import com.ps.dao.IManyToManyDAO;
import com.ps.dao.ManyToManyDAOImpl;
import com.ps.utility.HibernateUtil;

public class ManyToManyTest {

	public static void main(String[] args) {

		//create dao object
		IManyToManyDAO dao=new ManyToManyDAOImpl();
		//invoke method
		//dao.saveDataUsingParent();
		dao.saveDataUsingChild();
		
		//close SessionFactory
		HibernateUtil.closeSessionFactory();
	}//main
}//class
