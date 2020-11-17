package com.ps.test;

import com.ps.dao.OneToManyDao;
import com.ps.dao.OneToManyDaoImpl;
import com.ps.utility.HibernateUtil;

public class OneToManyUniTest {

	public static void main(String[] args) {

		//get dao object
		OneToManyDao dao=new OneToManyDaoImpl();
		
		//use dao to invoke method
		//dao.saveDataUsingParent();
		//dao.loadDataUsingParent();
		//dao.addNewChildToExistingParent();
		//dao.deletingAParentAndItsChild();
		//dao.deleteAllParentAndItsChild();
		//dao.deleteAllParentAndItsChild1();
		//dao.deleteAllChildsOfAParent();
		//dao.deleteOneChildFromCollectionOfChildsOfAParent();
		//dao.transferOneChildOfOneParentToAnotherParent();
		
		
		//close sessionFactory
		HibernateUtil.closeSessionFactory();
	}//main
}//class
