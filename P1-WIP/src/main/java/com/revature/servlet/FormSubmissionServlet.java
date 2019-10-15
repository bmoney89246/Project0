package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormSubmissionServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("Hello World!!!!!");
		PrintWriter pw = resp.getWriter();
		String position = req.getParameter("position");
		String name = req.getParameter("name");
		pw.write("Hello World" + name + position);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		System.out.println("Hello Post!!!!");
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("Servlet is servincing a request");
		// TODO Auto-generated method stub
		super.service(req, res);
	}

	@Override
	public void destroy() {
		System.out.println("Servlet being destroyed");
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		System.out.println("Servlet being initialized");
		// TODO Auto-generated method stub
		super.init();
	}
}
