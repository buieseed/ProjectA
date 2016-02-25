package com.mywebsite.member.login.model;

import com.mywebsite.member.login.dao.LoginDAO;

public class LoginService {
	private LoginDAO dao;

	public LoginService() {
		// TODO Auto-generated constructor stub
	}
	
	public LoginService(LoginDAO dao){
		this.dao = dao;
	}
	
	public boolean findUser(String username, String password){
		return dao.findUser(username, password);
	}
}
