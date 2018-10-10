package jstl;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;

// 访问地址: http://localhost:8080/servlet_demo1/jstl
public class JSTLServlet  extends HttpServlet
{
  
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // 将集合放入application域中
        List<User> userList = Arrays.asList(new User(111, "yxq", "123","123456"),new User(111, "zs", "abc","1123456"));
        ServletContext application = request.getServletContext();
        application.setAttribute("userList", userList);
        
        // 将map放入application域中
        Map<String,String> map =  new HashMap<String,String>();
        map.put("username", "yxq");
        map.put("address", "南京");
        map.put("age", "25");
        application.setAttribute("map", map);
        
        // 
        int age = 25;
        application.setAttribute("age", age);
        
        boolean isLogin = true;
        application.setAttribute("isLogin", isLogin);
        // 重定向
        response.sendRedirect(request.getContextPath()+"/jstl/jstl.jsp");
    }
}
