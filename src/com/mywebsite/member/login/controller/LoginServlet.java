package com.mywebsite.member.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mywebsite.member.login.model.LoginService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//檢查資料庫使用者的資料
		LoginService loginService = (LoginService) getServletContext().getAttribute("loginService");
		System.out.println(loginService.findUser(username, password));
		
	}

}
