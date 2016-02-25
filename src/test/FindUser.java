package test;

import com.mywebsite.member.login.dao.LoginDAO;
import com.mywebsite.member.login.dao.LoginDAOImpl;

public class FindUser {
public static void main(String[] args) {
	LoginDAOImpl di = new LoginDAOImpl();
	System.out.println(di.findUser("dick", "0112"));
}
	public FindUser() {
		// TODO Auto-generated constructor stub
	}

}
