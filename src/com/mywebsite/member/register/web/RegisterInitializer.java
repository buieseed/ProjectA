package com.mywebsite.member.register.web;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import com.mywebsite.member.register.dao.RegisterDAOImpl;
import com.mywebsite.member.register.model.RegisterService;

public class RegisterInitializer implements ServletContextListener {

	public RegisterInitializer() {
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
		RegisterService registerService = new RegisterService(new RegisterDAOImpl(dataSource));
		sce.getServletContext().setAttribute("registerService", registerService);
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}


}
