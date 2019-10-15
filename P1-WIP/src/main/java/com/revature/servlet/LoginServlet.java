package com.revature.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.dao.UserDaoFake;
import com.revature.pojo.User;
import com.revature.service.UserService;
import com.revature.service.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserService userService = new UserServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userService.loginUser(username, password);
		if (user != null) {
			response.getWriter().write("Welcome to your homepage" + user.getFullName());
		} else {
			response.getWriter().write("Invalid login credentials");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userService.loginUser(username, password);
		if (user != null) {
			request.getSession().setAttribute("user", user);
			if (user.getUsername().equals("user")) {
				response.sendRedirect("man");
			} else {
				response.sendRedirect("emp");
			}
			// response.getWriter().write("Welcome to your homepage" + user.getFullName());
			UserDaoFake udf = new UserDaoFake();
			if (udf.loginDao(user)) {
				// success
			} else {
				// invalid credentials
			}

		} else {
			response.getWriter().write("Invalid login credentials");
		}
	}

}
