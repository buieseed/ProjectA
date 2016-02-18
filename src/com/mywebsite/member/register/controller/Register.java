package com.mywebsite.member.register.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mywebsite.member.register.Bean.User;
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
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		String email = request.getParameter("email").trim();
		String usernameError = null;
		String passwordError = null;
		String emailError = null;
		String userIsExist = null;
		String newusername = username.replace("\\s+", "");//用正規表示法把空白去掉
		String newEmail = email.replace("\\s+", "");
		
		//第一個字為空白時一樣可以輸入，找一下方法處理，應該是用正規表示法可以處理＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
		
		//使用LIST收集錯誤訊息
//		List<String> errors = new ArrayList<String>();
//		if (username == null || username.length() == 0) {
//			errors.add("帳號是必要欄位!");
//		}
//		if (password == null || password.length() == 0) {
//			errors.add("密碼是必要欄位!");
//		}
//		if (email == null || email.length() == 0) {
//			errors.add("電子郵件是必要欄位!");
//		}
//		if(errors.size() != 0){
//			//Test watch error
//			Iterator<String> it = errors.iterator();
//			while(it.hasNext()){
//				System.out.println(it.next());
//			}
//			request.setAttribute("errors", errors);
		//改為各別收集錯誤訊息
		if( newusername == null || newusername.length() == 0 ){
			usernameError = "帳號是必要欄位!<br>";
			request.setAttribute("usernameError", usernameError);
		}
		if( password == null || password.length() == 0 ){
			passwordError = "密碼是必要欄位<br>";
			request.setAttribute("passwordError", passwordError);
		}
		if( newEmail == null || newEmail.length() == 0 ){
			emailError = "電子郵件是必要欄位";
			request.setAttribute("emailError", emailError);
		}
		if(usernameError != null  || passwordError!= null || emailError != null){
			request.getRequestDispatcher("Register.jsp").forward(request, response);
		
		}else{
			username = username.trim();
			password = password.trim();
			email = email.trim();
			
			RegisterService registerService = (RegisterService) getServletContext().getAttribute("registerService");
			//使用者輸入的註冊帳號己有人使用了，加入錯誤訊息中，導回註冊頁面
			if(registerService.userIsExist(username)){
//				errors.add("此帳號己經有人使用了!");
				userIsExist = "此帳號己經有人使用了!";
				request.setAttribute("userIsExist", userIsExist);
//				request.setAttribute("errors", errors);
				request.getRequestDispatcher("Register.jsp").forward(request, response);
			}else{
				//通過所有註冊驗證，註冊此使用者的帳號
				System.out.println("此帳號可以用");
				username = username.trim();
				password = password.trim();
				email = email.trim();
				User user = new User(username, password, email);
				registerService.userRegister(user);
			}
			
		}
		
	}

}
