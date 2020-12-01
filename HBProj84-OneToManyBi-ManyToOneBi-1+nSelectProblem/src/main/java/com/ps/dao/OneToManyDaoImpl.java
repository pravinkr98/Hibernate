package com.ps.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.PhoneNumber;
import com.ps.entity.UserInfo;
import com.ps.utility.HibernateUtil;

public class OneToManyDaoImpl implements OneToManyDao {
	Session ses=null;
	Transaction tx=null;	
	
	@Override
	public void loadDataUsingParent() {
	
		//get session
		ses=HibernateUtil.getSession();		
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//prepare HQL query
			Query query=ses.createQuery("from com.ps.entity.UserInfo");
			List<UserInfo> list=query.getResultList();
			list.forEach(user->{
				System.out.println("Parent :: "+user);
				//get associated child of each parent
				Set<PhoneNumber> child=user.getPhones();
				child.forEach(ph-> System.out.println("child :: "+ph));
			});						
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}		
	}//method
	
	@Override
	public void loadDataUsingParentThroughQBC() {

		//get session
		ses=HibernateUtil.getSession();		
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//prepare QBC(criteria) object
			Criteria criteria=ses.createCriteria(UserInfo.class);
			criteria.setFetchMode("phones", FetchMode.DEFAULT);
			//get parent data
			List<UserInfo> list=criteria.list();
			//display data
			list.forEach(user->{
				System.out.println("Parent :: "+user);
				//get associated child of each parent
				Set<PhoneNumber> child=user.getPhones();
				child.forEach(ph-> System.out.println("child :: "+ph));
			});			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
	}//method
	
	@Override
	public void loadDataUsingParentThroughJPQBC() {

		//get session
		ses=HibernateUtil.getSession();		
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//prepare JPQBC object(CriteriaBuilder)
			CriteriaBuilder builder=ses.getCriteriaBuilder();
			//prepare CriteriaQuery object
			CriteriaQuery<UserInfo> ctQuery=builder.createQuery(UserInfo.class);
			//prepare root object
			Root<UserInfo> root=ctQuery.from(UserInfo.class);
			root.fetch("phones",JoinType.INNER);
			//add root object criteria query
			ctQuery.select(root);
			//prepare query object
			Query<UserInfo> query=ses.createQuery(ctQuery);
			//get data
			List<UserInfo> list=query.getResultList();
			list.forEach(user->{
				System.out.println("Parent:: "+user);
				Set<PhoneNumber> child=user.getPhones();
				child.forEach(ph->System.out.println("Child:: "+ph));
			});			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
	}//method
	
	@Override
	public void loadDataUsingParentThroughHQLJoin() {

		//get session
		ses=HibernateUtil.getSession();		
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//prepare HQL query
			Query query=ses.createQuery("Select u from UserInfo u inner join fetch u.phones ph");
			List<UserInfo> list=query.getResultList();
			list.forEach(user->{
				System.out.println("Parent :: "+user);
				//get associated child of each parent
				Set<PhoneNumber> child=user.getPhones();
				child.forEach(ph-> System.out.println("child :: "+ph));
			});						
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
	}//method
	
	@Override
	public void loadDataUsingParentThroughHQLJoinScaler() {

		//get session
		ses=HibernateUtil.getSession();		
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//prepare HQL query
			Query query=ses.createQuery("select u.userId,u.userName,u.addrs,ph.regNo,ph.phone,ph.type,ph.provider from UserInfo u inner join u.phones ph");
			List<Object[]> list=query.getResultList();
			list.forEach(row->{
				for(Object val:row) {
					System.out.print(val+" ");
				}
				System.out.println();
			});						
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
	}//method
	
}//class
