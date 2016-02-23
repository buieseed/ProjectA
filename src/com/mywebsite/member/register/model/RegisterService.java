package com.mywebsite.member.register.model;

import com.mywebsite.member.register.Bean.User;
import com.mywebsite.member.register.dao.RegisterDAO;


public class RegisterService {
	private RegisterDAO dao;
	
	public RegisterService(RegisterDAO dao){
		this.dao = dao;		
	}
	
	//確認帳號是己否存在
	public boolean userIsExist(String username){
		System.out.println("service");
		return this.dao.userIsExist(username);
	}
	
	//使用者註冊帳號
	public void registerUser(User user) {
		this.dao.registerUser(user);		
	}
}
