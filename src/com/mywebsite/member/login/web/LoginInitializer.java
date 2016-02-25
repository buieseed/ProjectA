package com.mywebsite.member.login.web;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import com.mywebsite.member.login.dao.LoginDAOImpl;
import com.mywebsite.member.login.model.LoginService;

public class LoginInitializer implements ServletContextListener{

	public LoginInitializer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		DataSource dataSource = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/MariaDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		LoginService loginService = new LoginService(new LoginDAOImpl(dataSource));
		sce.getServletContext().setAttribute("loginService", loginService);
		
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
