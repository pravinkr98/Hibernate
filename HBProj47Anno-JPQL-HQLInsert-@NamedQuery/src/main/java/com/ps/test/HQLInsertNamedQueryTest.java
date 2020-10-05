package com.ps.test;

import com.ps.dao.IInsurancePolicyDAO;
import com.ps.dao.InsurancePolicyDAOImpl;
import com.ps.utility.HibernateUtil;

public class HQLInsertNamedQueryTest {

	public static void main(String[] args) {
		
		IInsurancePolicyDAO dao;
		
		//create dao object
		dao=new InsurancePolicyDAOImpl();
		//invoke method for copy record
		System.out.println(dao.transferPremiumPolicies(25));
		
		//close SessionFactory
		HibernateUtil.closeSessionFactory();
		
	}// main
}// class