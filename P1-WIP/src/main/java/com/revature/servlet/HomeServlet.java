package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.pojo.User;

public class HomeServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String name = ((User)(req.getSession().getAttribute("user"))).getFullName();
		String message = getServletContext().getInitParameter("message");
		String role = getServletConfig().getInitParameter("role");
		resp.getWriter().write(message + " " + role + " " + name);
	}

}
