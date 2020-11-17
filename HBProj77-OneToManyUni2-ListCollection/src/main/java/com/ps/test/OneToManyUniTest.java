package com.ps.test;

import com.ps.dao.OneToManyDao;
import com.ps.dao.OneToManyDaoImpl;
import com.ps.utility.HibernateUtil;

public class OneToManyUniTest {

	public static void main(String[] args) {

		//get dao object
		OneToManyDao dao=new OneToManyDaoImpl();
		
		//use dao to invoke method
		//dao.saveDataUsingParent();
		//dao.loadDataUsingParent();
		dao.deleteOneChildFromCollectionOfChildsOfAParent();
		
		
		//close sessionFactory
		HibernateUtil.closeSessionFactory();
	}//main
}//class
