package com.mywebsite.member.register.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mywebsite.member.register.Bean.User;
import com.mywebsite.member.register.model.RegisterService;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		//
	}

	// 取得servlet寫在web.xml裡初始參數
	@Override
	public void init() throws ServletException {
		super.init();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("html/text;charset=UTF-8");

		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		String email = request.getParameter("email").trim();
		// 改用Gson

		RegisterService registerService = null;
		HashMap<String, String> errorsMap = new HashMap<String, String>();// 給gsom設定鍵與值
		// 開始檢查使用者輸入的帳號內容
		if (username == null || "".equals("username") || username.length() == 0) {
			errorsMap.put("nameIsEmpty", "帳號是必要欄位。");
		}
		// else if(){} 帳號不允許使用數字和特殊字元。
		else if (username.length() < 4) {
			errorsMap.put("nameTooShort", "帳號必須至少包含4個字元。");
		}
		// 在輸入帳號的element裡設定輸入的長度不能超過30個字元

		// 通過username驗證後，檢查username是否己經有人使用了
		// 從ServletContexts屬性中取得RegisterService物件，準備對資料庫執行動作
		else {
			registerService = (RegisterService) getServletContext().getAttribute("registerService");
			if (registerService.userIsExist(username)) {
				errorsMap.put("userIsExist", "此帳號己經有人使用了。");
			}
		}

		// 開始檢查使用者輸入的密碼內容
		if ("".equals("password") || password.length() == 0) {
			errorsMap.put("passwordIsEmpty", "密碼是必要欄位。");
		}
		// 密碼不能輸入中文，或許可以設定在輸入密碼時不能使用別的輸入法，可參考Yahoo
		// 檢查密碼長度，不能小於8個字元
		// 在輸入密碼的element裡設定輸入的長度不能超過30個字元

		// 開始檢查使用者輸入的Email內容
		if ("".equals("email") || email.length() == 0) {
			errorsMap.put("emailIsEmpty", "電子郵件是必要欄位。");
		}
		// 檢查Email其它的輸入規則....

		// 檢查是否有錯誤訊息,map還有另一種檢查為if(errorsMap == null || errorsMap.size() == 0)
		if (errorsMap.size() != 0) {
			// 有錯誤訊息，將資料轉為JSON格式輸出,傳給ajax用
			PrintWriter out = response.getWriter();// 輸出Gson用
			Gson gson = new Gson();
			String errorMessages = gson.toJson(errorsMap);
			request.setAttribute("errorsMap", errorsMap);

			// 將資料輸出
			out.println(errorMessages);

			out.close();
			// 將使用者導回註冊頁面
			// RequestDispatcher requestDispatcher =
			// request.getRequestDispatcher(this.getInitParameter("ERROR"));
			// try {
			// requestDispatcher.forward(request, response);
			// return;
			// }catch(Exception e){
			// System.out.println(e);
			// }
		} else {
			// 沒有錯誤訊息,準備寫入資料庫
			// 從ServletContexts屬性中取得RegisterService物件，準備對資料庫值行動作
			registerService = (RegisterService) getServletContext().getAttribute("registerServlet");
			// 建立使用者資料物件
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			// 委託registerServlet把資料寫入資料庫
			registerService.registerUser(user);
			request.getRequestDispatcher("/Welcome.jsp").forward(request, response);
			writer(response, errorsMap);
		}
	}

	private void writer(HttpServletResponse response, Map<String, String> errorsMap) {
		// TODO Auto-generated method stub

	}
}

// ================================================

// String usernameError = null;
// String passwordError = null;
// String emailError = null;
// String userIsExist = null;
// String newusername = username.replace("\\s+",
// "");//使用者在輸入空白後再開始輸入字串，用正規表示法把所有空白去掉，才不會在輸入資料時出現空白
// String newEmail = email.replace("\\s+", "");

// 第一個字為空白時一樣可以輸入，找一下方法處理，應該是用正規表示法可以處理＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊

// 使用LIST收集錯誤訊息
// List<String> errors = new ArrayList<String>();
// if (username == null || username.length() == 0) {
// errors.add("帳號是必要欄位!");
// }
// if (password == null || password.length() == 0) {
// errors.add("密碼是必要欄位!");
// }
// if (email == null || email.length() == 0) {
// errors.add("電子郵件是必要欄位!");
// }
// if(errors.size() != 0){
// //Test watch error
// Iterator<String> it = errors.iterator();
// while(it.hasNext()){
// System.out.println(it.next());
// }
// request.setAttribute("errors", errors);
// //改為各別收集錯誤訊息
// if( newusername == null || newusername.length() == 0 ){
// usernameError = "帳號是必要欄位!<br>";
// request.setAttribute("usernameError", usernameError);
// }
// if( password == null || password.length() == 0 ){
// passwordError = "密碼是必要欄位<br>";
// request.setAttribute("passwordError", passwordError);
// }
// if( newEmail == null || newEmail.length() == 0 ){
// emailError = "電子郵件是必要欄位";
// request.setAttribute("emailError", emailError);
// }
// if(usernameError != null || passwordError!= null || emailError != null){
// request.getRequestDispatcher("Register.jsp").forward(request, response);
//
// }else{
// newusername = newusername.trim();
// password = password.trim();
// newEmail = newEmail.trim();
//
//
//
// RegisterService registerService = (RegisterService)
// getServletContext().getAttribute("registerService");
// //使用者輸入的註冊帳號己有人使用了，加入錯誤訊息中，導回註冊頁面
// if(registerService.userIsExist(username)){
// errors.add("此帳號己經有人使用了!");
// userIsExist = "此帳號己經有人使用了!";
// request.setAttribute("userIsExist", userIsExist);
// request.setAttribute("errors", errors);
// request.getRequestDispatcher("Register.jsp").forward(request, response);
// }else{
// //通過所有註冊驗證，註冊此使用者的帳號
// System.out.println("此帳號可以用");
// newusername = newusername.trim();
// password = password.trim();
// newEmail = newEmail.trim();
// User user = new User(newusername, password, newEmail);
// registerService.userRegister(user);
// }
//
// }
//
// }
//
// }
