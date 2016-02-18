<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


	<form action="register.do" method="post">
		Your name:<br> <input type="text" name="username" /><br>
		<%
			if (request.getAttribute("usernameError") != null) {
				out.println(request.getAttribute("usernameError"));
			}
		%>

		Password:<br> <input type="password" name="password" /><br>
		<%
			if (request.getAttribute("passwordError") != null) {
				out.println(request.getAttribute("passwordError"));
			}
		%>
		Email Address:<br> <input type="text" name="email" /><br>
		<%
			if (request.getAttribute("emailError") != null) {
		%>
		<div style="color: red;"><%=request.getAttribute("emailError")%></div>
		<%
			}
		%>
		<input type="submit" value="Create Account" />
		<%
			if (request.getAttribute("userIsExist") != null) {
		%>
		<div style="color: red;"><%=request.getAttribute("userIsExist")%></div>
		<%
			}
		%>

	</form>
</body>
</html>