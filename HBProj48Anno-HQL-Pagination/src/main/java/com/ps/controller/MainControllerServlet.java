package com.ps.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;

import com.ps.dto.InsurancePolicyDTO;
import com.ps.service.IInsurancePolicyMgmtService;
import com.ps.service.InsurancePolicyMgmtServiceImpl;

@WebServlet(value = "/controller", loadOnStartup = 1)
public class MainControllerServlet extends HttpServlet {

	private IInsurancePolicyMgmtService service=null;
	
	@Override
	public void init() throws ServletException {
		service=new InsurancePolicyMgmtServiceImpl();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int pageNo=1;
		int pageSize=3;
		long pagesCount=0L;
		String spValue=null;
		HttpSession session=null;
		String target=null;
		List<InsurancePolicyDTO> listDTO=null;
		RequestDispatcher rd=null;
		
		//create session
		session=req.getSession(true);
		//get form submit data
		spValue=req.getParameter("s1");
		if(spValue.equalsIgnoreCase("GenerateReport")) {
			//get pageSize data from form
			pageSize=Integer.parseInt(req.getParameter("pageSize"));
			//keep pageSize in session scope to access over multiple request
			session.setAttribute("pageSize", pageSize);			
		}
		else if(spValue.equalsIgnoreCase("link")) {
			//get pageNo from form
			pageNo=Integer.parseInt(req.getParameter("pageNo"));
			//get pageSize from session scope
			if(session!=null) {
				pageSize=(int) session.getAttribute("pageSize");
			}			
		}
		try {
			//invoke service class methods
			pagesCount=service.fetchPagesCount(pageSize);
			listDTO=service.fetchPageData(pageSize, pageNo);
			//add data request scope
			req.setAttribute("pagesCount", pagesCount);
			req.setAttribute("pageNo", pageNo);
			req.setAttribute("listPolicies", listDTO);
			//send req to target
			target="/report.jsp";				
		}
		catch (HibernateException he) {
			target="/error.jsp";
			he.printStackTrace();
		}
		catch (Exception e) {
			target="/error.jsp";
			e.printStackTrace();
		}
		
		//send response to target
		rd=req.getRequestDispatcher(target);
		rd.forward(req, res);
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	@Override
	public void destroy() {
		service=null;
	}
}
