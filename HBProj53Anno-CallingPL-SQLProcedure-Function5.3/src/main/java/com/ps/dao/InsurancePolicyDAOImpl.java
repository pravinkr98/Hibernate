package com.ps.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.ReturningWork;
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

	@Override
	public String[] getPolicyByID(int id) {
		Session ses=null;
		Transaction tx=null;
		
		//get session object
		ses=HibernateUtil.getSession();
		//Locate/get Tx
		if(!ses.getTransaction().isActive())
			tx=ses.beginTransaction();
		//get
		String[] result=ses.doReturningWork(new ReturningWork<String[]>() {

			@Override
			public String[] execute(Connection con) throws SQLException {
				CallableStatement cs=null;
				String outputs[]=null;
				//create callable statement
				cs=con.prepareCall("{?=call FX_GET_POLICY_BY_ID(?,?,?)}");
				//register OUT,Return param with Jdbc type
				cs.registerOutParameter(1, Types.VARCHAR);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.registerOutParameter(4, Types.INTEGER);
				//set value to IN param
				cs.setInt(2, id);
				//execute PL/SQL function
				cs.execute();
				//gather result from out return,param
				outputs=new String[3];
				outputs[0]=cs.getString(1);	//company
				outputs[1]=cs.getString(3);	//name
				outputs[2]=cs.getString(4); //tenure
				return outputs;
			}			
		});		
		return result;
	}//method
	
}//class