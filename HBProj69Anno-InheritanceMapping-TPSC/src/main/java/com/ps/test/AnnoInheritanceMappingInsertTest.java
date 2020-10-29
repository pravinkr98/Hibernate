package com.ps.test;

import java.time.LocalDate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.CardPayment;
import com.ps.entity.ChequePayment;
import com.ps.utility.HibernateUtil;

public class AnnoInheritanceMappingInsertTest {

	public static void main(String[] args) {

		Session ses=null;
		Transaction tx=null;
		boolean saved=false;
		
		//get session
		ses=HibernateUtil.getSession();
		try {
			//create or locate Tx
			if(!ses.getTransaction().isActive())
				tx=ses.beginTransaction();
			
			CardPayment payment1=new CardPayment();
			//prepare object
			payment1.setAmount(9000f);payment1.setCardNo(87634492L);
			payment1.setCardType("debit");payment1.setCardGateway("Master");
			
			ChequePayment payment2=new ChequePayment();
			//prepare object
			payment2.setAmount(9000f);payment2.setChequeNo(34563974L);
			payment2.setChequeType("A/c pay");payment2.setExpiryDate(LocalDate.of(2021, 01, 28));
			
			ses.save(payment1);
			ses.save(payment2);
			saved=true;
		}
		catch (HibernateException he) {
			he.printStackTrace();
		}
		finally {
			//Tx Mgmt
			if(saved) {
				tx.commit();
				System.out.println("Objects are saved");
			}
			else {
				tx.rollback();
				System.out.println("Objcts are not saved");
			}
			//close sessionFactory
			HibernateUtil.closeSessionFactory();
		}//finally
		
	}//main
}//class
