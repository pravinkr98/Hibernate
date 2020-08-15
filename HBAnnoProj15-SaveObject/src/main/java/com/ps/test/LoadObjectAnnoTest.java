package com.ps.test;

import java.util.Arrays;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.ps.entity.IProject;
import com.ps.utility.HibernateUtil;

public class LoadObjectAnnoTest {

	public static void main(String[] args) {
		Session ses=null;
		IProject proj=null;
			
		//get session object
		ses=HibernateUtil.getSession();
		
		try {
			//create object to save
			proj=ses.load(IProject.class, 1101L);
			System.out.println(proj.getClass()+"   "+proj.getClass().getSuperclass()+"   "+Arrays.deepToString(proj.getClass().getInterfaces()));
			System.out.println(proj);
		}
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close session
			HibernateUtil.closeSession(ses);
			HibernateUtil.closeSessionFactory();
			
		}//finally		
	}//main
}//class
