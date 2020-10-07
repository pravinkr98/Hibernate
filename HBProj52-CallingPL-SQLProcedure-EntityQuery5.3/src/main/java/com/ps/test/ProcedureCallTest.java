package com.ps.test;

import java.util.List;

import com.ps.dao.IInsurancePolicyDAO;
import com.ps.dao.InsurancePolicyDAOImpl;
import com.ps.entity.InsurancePolicy;
import com.ps.utility.HibernateUtil;

public class ProcedureCallTest {

	public static void main(String[] args) {

		IInsurancePolicyDAO dao=null;
		List<InsurancePolicy> list=null;
		
		//create dao class object
		dao=new InsurancePolicyDAOImpl();
		//invoke method for procedure call
		list=dao.getPoliciesByTenure(20, 30);
		//process the result
		list.forEach(System.out::println);
		
		//close SessionFactory
		HibernateUtil.closeSessionFactory();
	}//main
}//class