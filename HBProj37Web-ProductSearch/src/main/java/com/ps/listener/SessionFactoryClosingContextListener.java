package com.ps.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.ps.utility.HibernateUtil;

@WebListener
public class SessionFactoryClosingContextListener implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("SessionFactoryClosingContextListener.contextDestroyed(-)");
		HibernateUtil.closeSessionFactory();
	}

}
