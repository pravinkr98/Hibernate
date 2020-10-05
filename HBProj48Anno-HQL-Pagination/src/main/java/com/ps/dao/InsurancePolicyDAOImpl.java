package com.ps.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.InsurancePolicy;
import com.ps.utility.HibernateUtil;

public class InsurancePolicyDAOImpl implements IInsurancePolicyDAO {

	@Override
	public List<InsurancePolicy> getPageData(int pageSize, int startPos) {
		Session ses=null;
		Transaction tx=null;
		List<InsurancePolicy> list=null;
		
		//get session
		ses=HibernateUtil.getSession();
		try {
			//begin dummy tx
			if(!ses.getTransaction().isActive()) {
				tx=ses.beginTransaction();				
			}
			//prepare query by accessing NamedQuery
			Query query=ses.getNamedQuery("GET_ALL_POLICIES");
			//set pagination 
			query.setFirstResult(startPos);
			query.setMaxResults(pageSize);
			//execute query
			list=query.getResultList();
						
		}
		catch (HibernateException he) {
			he.printStackTrace();
			throw he;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	@Override
	public long getTotalRecordsCount() {
		Session ses=null;
		Transaction tx=null;
		List<Long> list=null;
		long count=0L;
		
		//get session
		ses=HibernateUtil.getSession();
		try {
			//begin dummy tx
			if(!ses.getTransaction().isActive()) {
				tx=ses.beginTransaction();				
			}
			//prepare query by accessing NamedQuery
			Query query=ses.getNamedQuery("GET_POLICY_COUNT");
			//execute query
			list=query.getResultList();
			//process result and send to the caller
			if(!list.isEmpty()) {
				count=list.get(0);
			}			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
		return count;		
	}//method
	
}//class
