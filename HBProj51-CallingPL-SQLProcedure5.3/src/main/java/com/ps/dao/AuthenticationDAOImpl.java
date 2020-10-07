package com.ps.dao;

import javax.persistence.ParameterMode;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;

import com.ps.utility.HibernateUtil;

public class AuthenticationDAOImpl implements IAuthenticationDAO {

	@Override
	public String authenticate(String uname, String pwd) {
		Session ses=null;
		Transaction tx=null;
		ProcedureCall call=null;
		String result=null;
		
		//get session
		ses=HibernateUtil.getSession();
		//Locate/get Tx
		if(!ses.getTransaction().isActive())
			tx=ses.beginTransaction();
		//create procedure call object
		call=ses.createStoredProcedureCall("P_AUTH");
		//register IN,OUT params with Jdbc type and also IN param with values
		call.registerParameter(1, String.class, ParameterMode.IN).bindValue(uname);
		call.registerParameter(2, String.class, ParameterMode.IN).bindValue(pwd);
		call.registerParameter(3, String.class, ParameterMode.OUT);
		//call PL/SQL procedure
		result=(String) call.getOutputParameterValue(3);			
		//return result		
		return result;
	}//method
}//class
