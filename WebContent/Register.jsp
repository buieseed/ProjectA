<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mywebsite.member.register.Bean.User, java.util.HashMap, java.util.Map, com.google.gson.Gson"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="JS/GetXMLHttpRequest.js"></script>
<script type="text/javascript">
	var myXhr = createXMLHttpRequestOpject();
	function validName() {
		if (myXhr) {
			var username = $("username");
			var url = "AjaxValidate";//導向ajax專用的servlet
			myXhr.open("POST", url, true);
			var data = "username=" + username.value;
			myXhr.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			myXhr.onreadystatechange = handleStateChange;
			myXhr.send(data);

		}
	}

	function handleStateChange() {
		if (myXhr.readyState == 4 && myXhr.status == 200) {
			if ("yes" == myXhr.responseText) {
				$("nameHelp").innerHTML = "此帳號己經有人使用了。";
			} else {
				$("nameHelp").innerHTML = "";
			}
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Map<String, String> errorsMap = (Map<String, String>) request.getAttribute("errorsMap");
		if (errorsMap != null) {
	%>
	${errorsMap.nameIsEmpty } ${errorsMap.passwordIsEmpty } ${errorsMap.emailIsEmpty}
	<%
		}
	%>

	<form action="register.do" method="post">
		Your name:
		<input type="text" name="username" id="username" onblur="validName()" />
		<br>
		<span id="nameHelp"></span>


		Password:
		<input type="password" name="password" id="password" />
		<br>

		Email Address:
		<input type="text" name="email" id="email" />
		<br>

		<input type="button" id="btn" value="Create Account" />
		<input type="submit" value="submit" />

	</form>




	<!--  <div style="color: red;"><%=request.getAttribute("userIsExist")%></div> -->

</body>
</html>