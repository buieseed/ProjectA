package com.mywebsite.member.register.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mywebsite.member.register.model.RegisterService;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		List<String> errors = new ArrayList<String>();
		if (username == null || username.length() == 0) {
			errors.add("帳號是必要欄位!");
		}
		if (password == null || password.length() == 0) {
			errors.add("密碼是必要欄位!");
		}
		if (email == null || email.length() == 0) {
			errors.add("電子郵件是必要欄位!");
		}
		if(errors.size() != 0){
			request.setAttribute("errors", errors);
			//Test watch error
			Iterator<String> it = errors.iterator();
			while(it.hasNext()){
				System.out.println(it.next());
			}
			request.getRequestDispatcher("Register.jsp").forward(request, response);
		}else{
			username = username.trim();
			password = password.trim();
			email = email.trim();
			
			RegisterService registerService = (RegisterService) getServletContext().getAttribute("registerService");
			if(!registerService.userIsExist(username)){
				System.out.println("yes");
				request.getRequestDispatcher("Register.jsp").forward(request, response);
			}else{
				System.out.println("no");
			}
			
		}
		
	}

}
