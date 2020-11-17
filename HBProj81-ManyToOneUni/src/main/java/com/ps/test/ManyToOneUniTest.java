package com.ps.test;

import com.ps.dao.ManyToOneDAO;
import com.ps.dao.ManyToOneDAOImpl;
import com.ps.utility.HibernateUtil;

public class ManyToOneUniTest {

	public static void main(String[] args) {

		//get dao object
		ManyToOneDAO dao=new ManyToOneDAOImpl();
		
		//use dao
		//dao.saveDataUsingChild();
		dao.loadDataUsingChild();
		//dao.deleteAllChildsAndItsParent();
		//dao.deleteASpecificChild();			//to delete a child use cascade="none" not applicable in real time
		//dao.deleteOneChildFromParent();
		
		//close SessionFactory
		HibernateUtil.closeSessionFactory();
	}//main
}//class
