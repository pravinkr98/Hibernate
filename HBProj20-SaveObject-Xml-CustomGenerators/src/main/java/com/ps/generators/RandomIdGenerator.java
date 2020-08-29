package com.ps.generators;

import java.io.Serializable;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class RandomIdGenerator implements IdentifierGenerator {

	public RandomIdGenerator() {
		System.out.println("CustomIdGenerator:: 0-param constructor");
	}

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		System.out.println("CustomIdGenerator.generate(-,-)");
		return (long)new Random().nextInt(100000);
	}
	
}
