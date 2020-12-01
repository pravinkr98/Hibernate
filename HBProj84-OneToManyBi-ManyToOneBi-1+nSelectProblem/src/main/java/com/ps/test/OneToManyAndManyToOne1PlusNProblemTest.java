package com.ps.test;

import com.ps.dao.OneToManyDao;
import com.ps.dao.OneToManyDaoImpl;
import com.ps.utility.HibernateUtil;

public class OneToManyAndManyToOne1PlusNProblemTest {

	public static void main(String[] args) {

		//get dao object
		OneToManyDao dao=new OneToManyDaoImpl();
		
		//use dao to invoke method
		//dao.loadDataUsingParent();
		//dao.loadDataUsingParentThroughQBC();
		dao.loadDataUsingParentThroughJPQBC();	
		//dao.loadDataUsingParentThroughHQLJoin();
		//dao.loadDataUsingParentThroughHQLJoinScaler();
		
		//close sessionFactory
		HibernateUtil.closeSessionFactory();
	}//main
}//class
