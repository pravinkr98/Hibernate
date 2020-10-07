package com.ps.test;

import com.ps.dao.AuthenticationDAOImpl;
import com.ps.dao.IAuthenticationDAO;
import com.ps.utility.HibernateUtil;

public class ProcedureCallTest {

	public static void main(String[] args) {
		IAuthenticationDAO dao=null;
		
		//get dao object
		dao=new AuthenticationDAOImpl();
		//invoke method to verify user
		System.out.println(dao.authenticate("KING", "KINGDOM"));

		//close SessionFactory
		HibernateUtil.closeSessionFactory();
		
	}//main
}//class
