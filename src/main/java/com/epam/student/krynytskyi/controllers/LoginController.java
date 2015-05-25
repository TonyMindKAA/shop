package com.epam.student.krynytskyi.controllers;

import com.epam.student.krynytskyi.beans.login.LoginFormBean;
import com.epam.student.krynytskyi.entity.User;
import com.epam.student.krynytskyi.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final Logger log = Logger.getLogger(RegistrationPageController.class);
	private static final String LOGIN_PAGE = "/WEB-INF/pages/login.jsp";
	private static final long serialVersionUID = 1L;
	private static final String USER_SERVICE = "userService";
    private UserService userService;
    @Override
    public void init() throws ServletException {
		userService = (UserService)getServletContext().getAttribute(USER_SERVICE);
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginFormBean loginFormBean = createLoginFormBean(request);
		try {
			if(userService.authenticate(loginFormBean.getEmail(), loginFormBean.getPassword(),request)){
				User userByEmail = userService.getUserByEmail(loginFormBean.getEmail());
				request.getSession().setAttribute("user", userByEmail);
				response.sendRedirect("products");
			}else{
				request.setAttribute("loginFormDate", loginFormBean);
				request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
			}
		} catch (Exception e) {
			log.error("Authentificate was filed!");
		}
		
	}

	private LoginFormBean createLoginFormBean(HttpServletRequest request) {
		LoginFormBean bean = new LoginFormBean();
		bean.setEmail(request.getParameter("email"));
		bean.setPassword(request.getParameter("password"));
		return bean;
	}

}
