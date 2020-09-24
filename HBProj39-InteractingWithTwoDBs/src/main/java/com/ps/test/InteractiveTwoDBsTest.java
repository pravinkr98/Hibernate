package com.ps.test;

import com.ps.dao.TransferDataDAO;
import com.ps.dao.TransferDataDAOImpl;
import com.ps.utility.MySQLHibernateUtil;
import com.ps.utility.OracleHibernateUtil;

public class InteractiveTwoDBsTest {

	public static void main(String[] args) {

		TransferDataDAO dao=null;
		
		//create dao object
		dao=new TransferDataDAOImpl();
		//invoke method to copy record
		System.out.println(dao.transferProductById(1));
		
		//close SessionFactory
		OracleHibernateUtil.closeSessionFactory();
		MySQLHibernateUtil.closeSessionFactory();		
	}//main
}//class
