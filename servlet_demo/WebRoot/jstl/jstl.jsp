<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!-- 
          要使用jstl
	导入标签库需要使用taglib指令!
	prefix="c"  是标签库的前缀，c名字是随意起的，但一般我们就用c
	
 -->
<%@ taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
	当  isELIgnored="false" el表达式才生效
	不写默认是false
	如果是true,当成字符串处理
	 -->
<%@ page isELIgnored="false"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>EL和JSTL表达式</title>
</head>


<body>
    <%
    	request.setAttribute("username", "yxq");
    	pageContext.setAttribute("id", 123);
     %>
     
	<%
		int a = 10;
		out.print(a);
	 %>
	 <!-- jsp表达式写法 -->
	 <%=a %>
	 
 	 <%=10/2 %> 
	 <!-- el 表达式-->
	 ${10/2}
	 ${10 == 10 }
	 ${empty "" }
	 
	 <%=request.getAttribute("username") %>
	 <!-- requestScope取request域中的值 -->
	 <!-- 
	 	
	 	当没有给出内置对象时，查找顺序是 pageScope-> requestScope-> sessionScope-> applicationScope  
	 	如果没找到，返回null
	 -->
	 ${username}
	 ${requestScope.username }
	 ${id }
	 <!-- pageContext上下文 -->
	 ${pageContext.request.contextPath}
	 
	 <!-- 
	 
	 JSTL是EL表达式的一个扩展：迭代，条件判断等功能
	  是第三方库，需要自己导包（myeclipse不需要导包）
	  如果是其它工具，需要导jstl-1.2.jar
	  
	 JSTL 一般在获取域中值的时候跟EL联合使用
	 value:不用EL表达式，就当字符串处理
	  -->
	 <br />
	 <c:out value="${id }" />
	 
	 <!-- 循环遍历1~10 -->
	 <!-- 
	 	begin :开始值
	 	end: 结束值
	 	step:步长
	 	var:给变量取一个别名
	  -->
	 <br />
	 <c:forEach begin="1" end="10" step="2"  var="i" varStatus="u">
	 	<c:out value="${i}" />
	 	<c:out value="${u.index}" />
	    <c:out value="${u.count}" />
	    <c:out value="${u.first}" />
	    <c:out value="${u.last}" />
	    <c:out value="${u.current}" />
	 	<br />
	 </c:forEach>
	 
	 <!-- 打印输出 
	 		9 * 9正方形的  *
	  -->
	 <c:forEach begin="1" end="9">
	 	<c:forEach begin="1" end="9">
	 		<c:out value="*" />
	 	</c:forEach>
	 	<br />
	 </c:forEach>
	 
	 <!-- 
	 
	 jstl遍历集合 
	 	items:指要遍历的集合
	 	var:取的别名
	 	varStatus:
	    index：取的迭代中索引值，遍历集合时从0 开始
	    count:迭代的次数，从1开始
	    first:是否是迭代的第一个数
	    last:判断是否是迭代的最后一个数
	    current:当前迭代的值
	 -->
	 <!-- 方式1：整体输出 
	 	前提：实体类要toString(),否则输出地址值
	 -->
	 <c:forEach items="${userList }"  var="user" varStatus="u">
	 	<c:out value="${user}" />
	 	<c:out value="${u.index }" />
	 	<c:out value="${u.count }" />
	 	<c:out value="${u.current }" />
	 	<br />
	 </c:forEach>
	 
	 <!-- 方式2：分别输出集合中对象属性值 -->
	 <c:forEach items="${userList }"  var="user">
	 	<c:out value="${user.id }" />
	 	<c:out value="${user.username }" />
	 	<c:out value="${user.password }" />
	 	<br />
	 	<c:out value="${user['id'] }" />	 
	 	<c:out value="${user['phone_no'] }" />
	 	<br />
	 	<!-- 当对象属性名中包含特殊字符时，必须用  ${user['phone_no'] }方式取值-->
	 	<%-- <c:out value="${user.phone_no' }" /> --%>
	 </c:forEach>
	 
	  <!-- jstl取map中值 -->
      <c:forEach items="${map }" var="map">
      	<c:out value="${map.key }" />
      	<c:out value="${map.value }" />
      	<br />
      </c:forEach>
      
     <%-- <c:choose> 与 <c:when>一起使用 --%>
      <c:choose>
      	<c:when test="${age < 18}">
      		<c:out value="少年" />
      	</c:when>
      	<c:when test="${age >= 18 && age <= 40}">
      		<c:out value="青年" />    		
      	</c:when>
        <c:when test="${age  > 40 && age  < 60 }">
      		<c:out value="中年" />
      	</c:when>
      </c:choose>  
      
    <!--   
    	jstl 条件判断 test
    	test里面值是boolean 型表达式或值
     -->
      <c:if test="${isLogin }">
      	<c:out value="已登录" />
      </c:if>  
      
      <c:if test="${!isLogin }">
      	<c:out value="已登录" />
      </c:if>  
             
</body>
</html>