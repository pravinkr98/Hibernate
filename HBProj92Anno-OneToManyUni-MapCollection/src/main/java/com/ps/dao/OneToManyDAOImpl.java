package com.ps.dao;

import java.util.List;
import java.util.Map;

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
		company.setModels(Map.of("first",model1,"second",model2));
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
				Map<String,CarModel> model=comp.getModels();
				//System.out.println(model.isEmpty());
				for(Map.Entry<String, CarModel> entry : model.entrySet()) {
					System.out.println(entry.getValue());
				}				
			});			
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}//method
	
}//class
