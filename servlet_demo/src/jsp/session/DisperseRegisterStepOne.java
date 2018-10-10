package jsp.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * http://localhost:8080/servlet_demo1/session/registerStep1.jsp
 * 分布式注册第一步
 * 
 * 在分布式注册中用户信息一定是放在session作用域中的
 * 
 * @author zte
 *
 */
@WebServlet("/disperseRegisterStepOne")
public class DisperseRegisterStepOne extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        
        // 如果有session,取session  如果没有session,则创建一个session
        // HttpSession session = request.getSession(true);
        // HttpSession session = request.getSession();
        
        // 如果有session,取session  如果没有session,则不创建session(返回null)
        HttpSession session = request.getSession(false);
        
        session.setAttribute("name", username);
        session.setAttribute("pwd", password);
        
        request.getRequestDispatcher("/session/registerStep2.jsp").forward(request, response);
    }
    
    

}
