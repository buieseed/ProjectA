package com.mywebsite.member.register.dao;

import com.mywebsite.member.register.Bean.User;

public interface RegisterDAO {

	public boolean userIsExist(String username);
	public void registerUser(User user);

}
