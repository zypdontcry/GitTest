<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPEhtmlPUBLIC"-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv=" Content-Type" content="text/html;charset=UTF-8">
		<title>用户注册第二步</title>
	</head>
	
	<body>
		<h1>用户注册</h1>
	
	    <hr>
		<form action="${pageContext.request.contextPath}/disperseRegisterStepTwo" method="post">
		     性別: <input type="radio" name="sex" value="1"/>男&nbsp;
		       <input type="radio" name="sex" value="2"/>女&nbsp;
		       <br/>
		     职位: <select name="job">
		               <option value="0">请选择</option>
		               <option value="1">讲师</option>
		               <option value="2">教授</option>
		      </select>
		      <br/>
		      <input type="submit"value="完成" /><br/>
		</form>
	</body>
</html>
