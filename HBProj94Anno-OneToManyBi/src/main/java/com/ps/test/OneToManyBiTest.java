package com.ps.test;

import com.ps.dao.IManyToOneDAO;
import com.ps.dao.ManyToOneDAOImpl;
import com.ps.utility.HibernateUtil;

public class OneToManyBiTest {

	public static void main(String[] args) {

		//create dao object
		IManyToOneDAO dao=new ManyToOneDAOImpl();
		//invoke method
		//dao.saveDataUsingParent();
		dao.saveDataUsingChild();
		
		//close sessionFactory obj
		HibernateUtil.closeSessionFactory();
	}//main
}//class
