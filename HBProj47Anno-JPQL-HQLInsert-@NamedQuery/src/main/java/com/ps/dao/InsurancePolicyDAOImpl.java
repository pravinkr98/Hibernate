package com.ps.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.utility.HibernateUtil;

public class InsurancePolicyDAOImpl implements IInsurancePolicyDAO {
	//private static final String HQL_PREMIUM_POLICY_TRNASFER_QUERY="INSERT INTO com.ps.entity.PremiumInsurancePolicy(pid,policyName,policyType,company,tenure) SELECT i.policyId,i.policyName,i.policyType,i.company,i.tenure FROM com.ps.entity.InsurancePolicy AS i WHERE tenure>=:min";

	@Override
	public String transferPremiumPolicies(int minTenure) {
		Session ses=null;
		Transaction tx=null;
		Query query=null;
		int count=0;
		boolean coppied=false;
		String msg=null;
		
		//get session
		ses=HibernateUtil.getSession();
		try {
			//begin Tx
			tx=ses.beginTransaction();
			//get Query object having access to NamedQuery
			query=ses.getNamedQuery("HQL_PREMIUM_POLICY_TRNASFER_QUERY");
			//set query param
			query.setParameter("min", minTenure);
			//execute query
			count=query.executeUpdate();
			coppied=true;
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//commit or rollback tx
			if(coppied) {
				tx.commit();
				msg="Total no. of coppied record is : "+count;
			}
			else {
				tx.rollback();
				msg="Record not Coppied/Inserted";
			}
		}//finally		
		return msg;
		
	}//method
}//class