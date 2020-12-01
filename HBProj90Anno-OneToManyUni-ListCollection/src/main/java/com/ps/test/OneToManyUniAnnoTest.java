package com.ps.test;

import com.ps.dao.IOneToManyDAO;
import com.ps.dao.OneToManyDAOImpl;
import com.ps.utility.HibernateUtil;

public class OneToManyUniAnnoTest {

	public static void main(String[] args) {

		//create dao object
		IOneToManyDAO dao=new OneToManyDAOImpl();
		//invoke method
		//dao.saveDataUsingParent();
		dao.loadDataUsingParent();
		
		//close sessionFactory
		HibernateUtil.closeSessionFactory();
	}//main
}//class
