package com.ps.test;

import java.io.FileOutputStream;
import java.io.FileWriter;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.JobSeeker;
import com.ps.utility.HibernateUtil;

public class JobSeekerSelectTest {

	public static void main(String[] args) {

		Session ses=null;
		Transaction tx=null;
				
		//get session 
		ses=HibernateUtil.getSession();		
		
		try {
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//prepare object to save
			JobSeeker seeker=ses.get(JobSeeker.class, 236);
			//create destination image file having byte[]
			FileOutputStream fos=new FileOutputStream("store/mahesh.jpg");
			fos.write(seeker.getPhoto());
			System.out.println("Photo is reterived");
			FileWriter writer=new FileWriter("store/resume.txt");
			writer.write(seeker.getResume());
			fos.flush();
			writer.flush();
			System.out.println("Resume is reterived");
			System.out.println(seeker.getJsId()+" "+seeker.getJsName()+" "+seeker.getJsAddr()+" "+seeker.isActive());
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close SessionFactory
			HibernateUtil.closeSessionFactory();
		}//finally
		
	}//main
}//class