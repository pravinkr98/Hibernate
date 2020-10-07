package com.ps.dao;

import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;

import com.ps.entity.InsurancePolicy;
import com.ps.utility.HibernateUtil;

public class InsurancePolicyDAOImpl implements IInsurancePolicyDAO {

	@Override
	public List<InsurancePolicy> getPoliciesByTenure(int start, int end) {
		Session ses=null;
		Transaction tx=null;
		ProcedureCall call=null;
		Output output=null;
		ResultSetOutput rsOutput=null;
		List<InsurancePolicy> list=null;
		
		//get session object
		ses=HibernateUtil.getSession();
		//Locate/get Transaction
		if(!ses.getTransaction().isActive())
			tx=ses.beginTransaction();
		//prepare procedure call
		call=ses.createStoredProcedureCall("P_GET_POLICIES_BY_TENURE", InsurancePolicy.class);
		//set IN,OUT param of procedure call
		call.registerParameter(1, Integer.class, ParameterMode.IN).bindValue(start);
		call.registerParameter(2, Integer.class, ParameterMode.IN).bindValue(end);
		call.registerParameter(3, Class.class, ParameterMode.REF_CURSOR);
		//call PL/SQL procedure and get output
		output=call.getOutputs().getCurrent();
		//kee ouptup in resultset
		rsOutput=(ResultSetOutput) output;
		//keep resultSet output in list
		list=rsOutput.getResultList();
		return list;
	}//method
	
}//class