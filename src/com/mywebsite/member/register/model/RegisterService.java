package com.mywebsite.member.register.model;

import com.mywebsite.member.register.dao.RegisterDAO;


public class RegisterService {
	private RegisterDAO dao;

	public RegisterService() {
		// TODO Auto-generated constructor stub
	}
	
	public RegisterService(RegisterDAO dao){
		this.dao = dao;		
	}
	
	//確認帳號是己否存在
	public boolean userIsExist(String username){
		return this.dao.userIsExist(username);
	}
}
