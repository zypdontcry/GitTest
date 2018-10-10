package jsp.cookie;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 服务端对cookie的CRUD
 * 
 * 增: Create    ---> 创建Cookie
 * 查: Read      ---> 查询Cookie
 * 改: Update    ---> 修改Cookie
 * 删 : Delete    ---> 删除Cookie
 * 
 * http://localhost:8080/servlet_demo1/cookie/create
 * http://localhost:8080/servlet_demo1/cookie/update
 * http://localhost:8080/servlet_demo1/cookie/read
 * http://localhost:8080/servlet_demo1/cookie/delete
 * 
 * 
 * http://localhost:8080/servlet_demo1/create.cookie
 * http://localhost:8080/servlet_demo1/update.cookie
 * http://localhost:8080/servlet_demo1/read.cookie
 * http://localhost:8080/servlet_demo1/delete.cookie
 * 
 * cookie保存中文问题
 *    URLEncoder.encode("中文cookie的值", "utf-8");  ---> 添加cookie
 *    URLDecoder.decode("中文cookie的值", "utf-8");  ---> 查询指定cookie
 * 
 * 
 * @author zte
 *
 */
@WebServlet("/cookie/*")
// @WebServlet("*.cookie")  // 扩展匹配    只要以.cookie结尾 的请求 都能进入到ServerCookieCRUD这个servlet
public class ServerCookieCRUD extends HttpServlet
{

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // C 增   http://localhost:8080/servlet_demo1/cookie/create
        String requestURI = request.getRequestURI();
        // /servlet_demo1/cookie/create
        System.out.println(requestURI);
        
        if (requestURI.indexOf("/create") != -1)
        {
            addCookie(request, response);
        }else if (requestURI.indexOf("/update") != -1)
        {
            updateCookie(request, response);
        }else if (requestURI.indexOf("/read") != -1)
        {
            readCookie(request, response);
        }else if (requestURI.indexOf("/delete") != -1)
        {
            deleteCookie(request, response);
        }
    }

    /**
     * 删除cookie
     */
    public void deleteCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("username", "ruhua");
        cookie.setMaxAge(0); // cookie刚刚创建就失效
        response.addCookie(cookie);
    }

    /**
     * 查询Cookie
     */
    public void readCookie(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
    {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
        {
            // 遍历cookie数组
            for (Cookie c : cookies)
            {
                if ("username".equals(c.getName()))
                {
                    // 记日志  Logger ---> log4j.jar(log4j.properties  自己搞----> 定位bugger 日志文件)  logback.jar   slf4j.jar
                    System.out.println(c.getName() + "=" + URLDecoder.decode(c.getValue(), "utf-8"));
                }
            }
        }
    }

    /**
     * 修改Cookie
     */
    public void updateCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("username", "xiaoyanzi");
        response.addCookie(cookie);
    }

    /**
     * 添加cookie
     * @param request
     * @param response
     */
    public void addCookie(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException 
    {
        String cookieValue = "李艳";
        // 给cookieValue值  ---> utf-8编码 
        // 取"username" cookie值的是时候  ---> utf-8解码
        cookieValue = URLEncoder.encode(cookieValue, "utf-8");
        Cookie cookie = new Cookie("username", cookieValue);
        
        // cookie.setDomain(domain); // 设置cookie的域    SSO Single Sign On  同父域SSO  单点登录 
        
        // 设置cookie的超时时间(单位:秒)  设置cookie的有效期为7天
        cookie.setMaxAge(1 * 60 * 60 * 24 * 7);
        
        // /servlet_demo1
        cookie.setPath(request.getContextPath());
        
        // 将cookie写入到浏览器(保存在浏览器端)
        response.addCookie(cookie);
        
        
        Cookie cookie2 = new Cookie("password", "123");
        response.addCookie(cookie2);
    }
    
    
}
