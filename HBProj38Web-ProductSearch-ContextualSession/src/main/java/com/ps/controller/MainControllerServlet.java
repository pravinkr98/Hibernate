package com.ps.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ps.dto.ProductDTO;
import com.ps.service.ProductMgmtService;
import com.ps.service.ProductMgmtServiceImpl;

@WebServlet("/controller")
public class MainControllerServlet extends HttpServlet {

	private ProductMgmtService service;
	
	@Override
	public void init() throws ServletException {
		service=new ProductMgmtServiceImpl();
	}//init()

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int id=0;
		ProductDTO dto=null;
		RequestDispatcher rd=null;
		//read form data
		id=Integer.parseInt(req.getParameter("pid"));
		try {
			//use service
			dto=service.fetchProductDetails(id);
			//set dto object in response object
			req.setAttribute("pDTO", dto);
			//forward to result.jsp page
			rd=req.getRequestDispatcher("/result.jsp");
			rd.forward(req, res);
		}
		catch (Exception e) {
			e.printStackTrace();
			//forward to error.jsp page
			rd=req.getRequestDispatcher("/error.jsp");
			rd.forward(req, res);
		}	
	}//doGet(-,-)

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}//doPost(-,-)

	@Override
	public void destroy() {
		service=null;
	}//destroy()
}
