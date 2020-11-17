package com.ps.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.JobSeeker;
import com.ps.utility.HibernateUtil;

public class JobSeekerInsertTest {

	public static void main(String[] args) {

		Session ses=null;
		Transaction tx=null;
		byte[] photoContent=null;
		char[] textContent=null;
		boolean saved=false;
		
		//get session 
		ses=HibernateUtil.getSession();
		
		try(FileInputStream fis=new FileInputStream("Mahesh.jpg")) 
		{
			photoContent=new byte[fis.available()];
			fis.read(photoContent);
			
			File file=new File("resume.txt"); 
			try(FileReader reader=new FileReader(file))
			{
				textContent=new char[(int) file.length()];
				reader.read(textContent);
			}	
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			//prepare object to save
			JobSeeker seeker=new JobSeeker();
			seeker.setJsName("Himesh");
			seeker.setJsAddr("hyd");
			seeker.setPhoto(photoContent);
			seeker.setResume(textContent);
			seeker.setActive(true);
			//save object
			int idVal=(int) ses.save(seeker);
			System.out.println("Generated id value :: "+idVal);
			saved=true;
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(saved) {
				tx.commit();
				System.out.println("Object is saved");
			}
			else {
				tx.rollback();
				System.out.println("Object is not saved");
			}
			//close SessionFactory
			HibernateUtil.closeSessionFactory();
		}//finally
		
	}//main
}//class