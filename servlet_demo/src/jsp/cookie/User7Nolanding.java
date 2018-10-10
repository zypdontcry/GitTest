package jsp.cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.CookieUtil;

/**
 * 7天免登录功能
 * 
 * http://localhost:8080/servlet_demo1/login.jsp
 * @author zte
 *
 */
@WebServlet("/nolanding7")
public class User7Nolanding extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 获取用户在登录页面上输入的 用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        HttpSession session = request.getSession();
        session.setAttribute("name", username);
        
        // 假设我们的用户名和密码是  admin/123 能成功登录
        if ("admin".equals(username) && "123".equals(password))
        {
            // 如果用户勾选了7天免登录
            if (request.getParameter("rember") != null)
            {
                // 添加2个cookie  username  和  pwd 分别设置这2个cookie有效期为7天
                CookieUtil.addCookie("username", username, 7 * 24 * 60 * 60, response);
                CookieUtil.addCookie("pwd", password, 7 * 24 * 60 * 60, response);
            }
            
            // 用户登录成功 ---> 重定向
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
        
        // 用户登录失败 ---> 转发
        request.setAttribute("errMsg", "用户名或者密码错误");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    
    
}
