package com.ps.generators;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class RandomStringIdGenerator implements IdentifierGenerator {
	
	public RandomStringIdGenerator() {
		System.out.println("RandomStringIdGenerator:: 0-param constructor");
	}

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		System.out.println("RandomStringIdGenerator.generate(-,-)");
		String idval=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int id=0;
		try {
			con=session.connection();
			ps=con.prepareStatement("SELECT PID_SEQ.NEXTVAL FROM DUAL");
			rs=ps.executeQuery();
			if(rs.next()) {
				id=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(id<=9)
			idval="NIT000"+id;
		else if(id<=99)
			idval="NIT00"+id;
		else if(id<=999)
			idval="NIT0"+id;
		else
			idval="NIT"+id;
			
		return idval;
	}
	
}
