package com.ps.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ps.entity.CarCompany;
import com.ps.entity.CarModel;
import com.ps.utility.HibernateUtil;

public class OneToManyDAOImpl implements IOneToManyDAO {

	Session ses=null;
	Transaction tx=null;
	
	@Override
	public void saveDataUsingParent() {

		//get session object
		ses=HibernateUtil.getSession();
		boolean saved=false;
		//prepare object to save
		CarModel model1=new CarModel("Baleno", "hatchback");
		CarModel model2=new CarModel("Brezza", "hatchback");
		CarCompany company=new CarCompany("Maruti-Suzuki", "Noida");
		company.setModels(Set.of(model1, model2));
		try {
			//create or locate tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//save object using parent
			ses.save(company);
			saved=true;
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(saved) {
				tx.commit();
				System.out.println("Object saved");
			}
			else {
				tx.rollback();
				System.out.println("Object not saved");
			}
		}//finally
	}//method
		
	@Override
	public void loadDataUsingParent() {

		//get session object
		ses=HibernateUtil.getSession();
		try {
			//create or locate tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//load object using parent
			Query query=ses.createQuery("from com.ps.entity.CarCompany");
			List<CarCompany> list=query.getResultList();
			list.forEach(comp->{
				System.out.println("Parent :: "+comp);
				Set<CarModel> model=comp.getModels();
				System.out.println(model.isEmpty());
				/*model.forEach(md->{
					System.out.println("Child :: "+md);
				});*/
			});			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}//method
	
	@Override
	public void loadDataUsingParentAndQBC() {

		//get session object
		ses=HibernateUtil.getSession();
		try {
			//create or locate tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//load object using parent QBC
			Criteria criteria=ses.createCriteria(CarCompany.class);
			List<CarCompany> list=criteria.list();
			list.forEach(comp->{
				System.out.println("Parent :: "+comp);
				Set<CarModel> model=comp.getModels();
				model.forEach(md->{
					System.out.println("Child :: "+md);
				});
			});			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}//method
	
	@Override
	public void deleteOneChildFromCollectionOfChildsBelongingToAParent() {

		var deleted=false;
		//get session object
		ses=HibernateUtil.getSession();
		try {
			//create or locate tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//load parent object
			CarCompany company=ses.get(CarCompany.class, 318);
			//get all its childs
			Set<CarModel> models=company.getModels();
			//Load Specific Child object
			CarModel model=ses.get(CarModel.class, 101);
			models.remove(model);
			deleted=true;		
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(deleted) {
				tx.commit();
				System.out.println("Specific child object is deleted");
			}
			else {
				tx.rollback();
				System.out.println("Problem in deleting specific child object");
			}
		}//fianlly
	}//method

}//class
