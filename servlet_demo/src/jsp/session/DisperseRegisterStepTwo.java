package jsp.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 分布式注册第二步
 * @author zte
 *
 */
@WebServlet("/disperseRegisterStepTwo")
public class DisperseRegisterStepTwo extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        // 后台取到的值 1 or 2 页面上显示的要是男 or 女
        String sex = request.getParameter("sex");
        String job = request.getParameter("job");
        
        Object name = (String)request.getSession().getAttribute("name");
        Object pwd = (String)request.getSession().getAttribute("pwd");
        
        
        request.getSession().setAttribute("sex", sex);
        request.getSession().setAttribute("job", job);
        request.getSession().setAttribute("name", name);
        request.getSession().setAttribute("pwd", pwd);
        
        
        response.sendRedirect(request.getContextPath() + "/session/success.jsp");
    }
    
    

}
