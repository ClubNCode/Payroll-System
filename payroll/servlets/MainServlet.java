package com.raj.payroll.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wipro.payroll.bean.PayslipBean;
import com.wipro.payroll.dao.PayslipDAO;
import com.wipro.payroll.service.Administrator;


public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		PrintWriter out = response.getWriter();
		RequestDispatcher rd = null;
		String path = "";
		
		String name = request.getParameter("operation");
		
		if(name.equalsIgnoreCase("generate")){
			
			String aname = generatePayslip(request);
			
			if(aname.equalsIgnoreCase("FAIL")||aname.equalsIgnoreCase("INVALID INPUT")){
				
                path = "./error.html";
				
				rd=request.getRequestDispatcher(path);
				
				rd.forward(request, response);
				
			}
			else{
				
                path = "./success.html";
				
				rd=request.getRequestDispatcher(path);
				
				rd.forward(request, response);
				
			}
			
		}
		
		if(name.equalsIgnoreCase("view")){
			
			PayslipBean bean = new PayslipBean();
			
			bean = viewPayslip(request);
			
			if(bean == null){
				
				
                path = "./displayPayslip.jsp";
				
				rd=request.getRequestDispatcher(path);
				
				rd.forward(request, response);
				
			}else{
				
				 out.println("No matching records exists! Please try again!");
				 request.setAttribute("obj1", bean);
				
				 path = "./displayPayslip.jsp";
					
			     rd=request.getRequestDispatcher(path);
					
				 rd.include(request, response);
				
			}
			
		}
		
	}
	
	public String generatePayslip(HttpServletRequest request){
		
		PayslipBean bean = new PayslipBean();
		
		Enumeration<String> e = request.getAttributeNames();
		String i = e.nextElement();
		bean.setEmpID(i);
		i = e.nextElement();
		bean.setMonth(i);
		i = e.nextElement();
		bean.setYear(i);
		i = e.nextElement();
		bean.setBasic(Double.parseDouble(i));
		i = e.nextElement();
		bean.setCommutation(Double.parseDouble(i));
		i = e.nextElement();
		bean.setHrAllowance(Double.parseDouble(i));
		
		return new Administrator().addPayslip(bean);
		
	}
	
	public PayslipBean viewPayslip(HttpServletRequest request){
		
        PayslipBean bean = new PayslipBean();
		
		Enumeration<String> e = request.getAttributeNames();
		String i = e.nextElement();
		bean.setEmpID(i);
		i = e.nextElement();
		bean.setMonth(i);
		i = e.nextElement();
		bean.setYear(i);
		i = e.nextElement();
		
		return new Administrator().viewPayslip(bean.getEmpID(), bean.getMonth(), bean.getYear());
		
	}
	
	

}
