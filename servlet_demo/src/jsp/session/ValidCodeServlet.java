package jsp.session;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import util.ValidateCodeUtil;

/**
 * http://localhost:8080/servlet_demo1/login.jsp
 * http://localhost:8080/servlet_demo1/createCode.code
 * http://localhost:8080/servlet_demo1/checkCode.code
 * @author zte
 *
 */
@WebServlet("*.code") // 注意在扩展名匹配中  不能写成  /*.code
public class ValidCodeServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String path = request.getServletPath();
        
       
        if (path.indexOf("/createCode.code") != -1)
        {
            // 生成验证码
            createCode(request, response);
        }else if(path.indexOf("/checkCode.code") != -1)
        {
            // 校验验证码
            checkCode(request, response);
        }
       
    }
    

    /**
     * 生成验证码
     * @throws IOException 
     */
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException 
    {
        // 生成验证码字符串和图片
        BufferedImage img = ValidateCodeUtil.paintImage(80, 30);
        String code = ValidateCodeUtil.getCode();

        HttpSession session = request.getSession();
        
        session.removeAttribute("code");
        
        // 把验证码存放到session对象中
        session.setAttribute("coding", code);
        
        System.out.println(session.getAttribute("code"));

        // 生成验证码
        ImageIO.write(img, "jpeg", response.getOutputStream());
    }
    

    /**
     * 校验验证码
     */
    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws IOException 
    {
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        
        // 获取用户输入的验证码
        String code = request.getParameter("code");
        
        // 获取session中的验证码
        String coding = (String)request.getSession().getAttribute("coding");
        
        String resultStr = "";
        // 不分区大小写比较验证码是否输入正确
        if (code.equalsIgnoreCase(coding))
        {
            resultStr = "验证码输入正确";
            
            // string ---> json
            // response.getWriter().print(JSON.toJSON(resultStr)); // error
            response.getWriter().print(JSON.toJSONString(resultStr));
        }else
        {
            resultStr =  "验证码输入错误";
            
            // string ---> json
            // response.getWriter().print(JSON.toJSON(resultStr)); // error
            response.getWriter().print(JSON.toJSONString(resultStr));
        }
    }



}
