package com.mywebsite.member.register.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mywebsite.member.register.model.RegisterService;

/**
 * Servlet implementation class AjaxValidate
 */
@WebServlet("/AjaxValidate")
public class AjaxValidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxValidate() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("html/text;charset=UTF-8");
		String username = request.getParameter("username");
		RegisterService registerService = (RegisterService) getServletContext().getAttribute("registerService");
		PrintWriter out = response.getWriter();
		if(registerService.userIsExist(username)){
			out.println("yes");
		}else{
			out.println("no");
		}
		out.close();
		
	}

}
