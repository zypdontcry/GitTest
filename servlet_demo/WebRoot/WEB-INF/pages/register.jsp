<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>注册页面</title>
	</head>
	<body>
	     <form action="${pageContext.request.contextPath}/user/regist" method="post">
	                       用户名: <input type="text" name="username" id="username" />
	                       密&nbsp;&nbsp;&nbsp;码: <input type="password" name="password" id="password" />
	         <input type="submit" value="注册" /> <span style="color:red;">${errorMsg}</span>
	         
	         <%-- 
	         <%
	            request.getAttribute("errorMsg"); // 相当于  ${errorMsg}
	          %> 
	         --%>
	         
	         <!-- out.println(a); -->
	         
	         <%-- <%=a %>   --%>
	         
	         
	         
	     </form>
	</body>
</html>