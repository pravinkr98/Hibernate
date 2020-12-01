package com.ps.test;

import com.ps.dao.IOneToOneFKDAO;
import com.ps.dao.OneToOneFKDAOImpl;
import com.ps.utility.HibernateUtil;

public class OneToOneFKTest {

	public static void main(String[] args) {

		IOneToOneFKDAO dao=null;
		//create dao
		dao=new OneToOneFKDAOImpl();
		//invoke method
		//dao.saveData();
		//dao.loadDataUsingParent();
		dao.deleteDataUsingParent();
		
		//close sessionFactory
		HibernateUtil.closeSessionFactory();
	}//main
}//class
