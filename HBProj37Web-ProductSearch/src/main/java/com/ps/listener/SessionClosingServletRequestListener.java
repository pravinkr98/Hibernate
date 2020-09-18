package com.ps.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import com.ps.utility.HibernateUtil;

@WebListener
public class SessionClosingServletRequestListener implements ServletRequestListener {
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("SessionClosingServletRequestListener.requestDestroyed(-)");
		HibernateUtil.closeSession();
	}

}
