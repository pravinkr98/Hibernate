package com.ps.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ps.entity.Product;
import com.ps.utility.MySQLHibernateUtil;
import com.ps.utility.OracleHibernateUtil;

public class TransferDataDAOImpl implements TransferDataDAO {

	@Override
	public String transferProductById(int pid) {
		Session oraSes=null,mysqlSes=null;
		Transaction oraTx=null,mysqlTx=null;
		Product prod=null;
		var idVal=0;		//Java 10 local variable type inference(Compiler desides datatype based on initial data) 
		var saved=false;
		
		//get Session for Oracle DB
		oraSes=OracleHibernateUtil.getSession();
		//begin dummy Tx for Oracle DB
		oraTx=oraSes.beginTransaction();
		//get product  object
		prod=oraSes.get(Product.class, pid);
		if(prod==null) {
			return "Product record not found";
		}
		else {
			try {
				//get Session for MySQL DB
				mysqlSes=MySQLHibernateUtil.getSession();
				//begin Tx
				mysqlTx=mysqlSes.beginTransaction();
				//save object
				idVal=(int) mysqlSes.save(prod);
				saved=true;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				//Commit or rollback Tx
				if(saved) {
					mysqlTx.commit();
					return "Product record saved in MySQL DB having id ::"+idVal;
				}
				else {
					mysqlTx.rollback();
					return "Product not saved in MySQL DB";
				}
			}//finally
		}//else	
	}//method
}//class
