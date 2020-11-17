package com.ps.test;

import com.ps.dao.OneToManyDao;
import com.ps.dao.OneToManyDaoImpl;
import com.ps.utility.HibernateUtil;

public class OneToManyAndManyToOneBITest {

	public static void main(String[] args) {

		//get dao object
		OneToManyDao dao=new OneToManyDaoImpl();
		
		//use dao to invoke method
		//dao.saveDataUsingParent();
		//dao.saveDataUsingChild();		
		//dao.loadDataUsingParent();
		dao.loadDataUsingChild();
			
		//close sessionFactory
		HibernateUtil.closeSessionFactory();
	}//main
}//class
