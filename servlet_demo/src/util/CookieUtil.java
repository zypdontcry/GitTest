package util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie的CRUD 工具类
 * @author zte
 *
 */
public class CookieUtil 
{
    /**
     * 添加Cookie  ---> Cookie的声明周期 Session(同一个会话)
     */
    public static void addCookie(String cookieName, String cookieValue, HttpServletResponse response)
    {
        try 
        {
            Cookie cookie = new Cookie(cookieName, URLEncoder.encode(cookieValue, "utf-8"));
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e)
        {
            // e.printStackTrace();
            // 日志处理
        }
        
    }
    
    /**
     * 添加Cookie ---> 自己设置cookie超时时间
     */
    public static void addCookie(String cookieName, String cookieValue, int maxAge, HttpServletResponse response)
    {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    
    /**
     * 删除cookie
     */
    public static void deleteCookie(String cookieName, HttpServletResponse response)
    {
        addCookie(cookieName, "", 0, response);
    }
    
    
    /**
     * 修改cookie ---> 修改cookie的值
     */
    public static void updateCookie(String cookieName, String cookieValue, HttpServletResponse response)
    {
        addCookie(cookieName, cookieValue, response);
    }
    
    /**
     * 查询cookie 
     * @param   String  根据指定的CookieName查询出CookieValue值
     */
    public static String findCookie(String cookieName, HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
        {
            // 遍历cookie数组
            for (Cookie c : cookies)
            {
                if (cookieName.equals(c.getName()))
                {
                    // 记日志  Logger ---> log4j.jar(log4j.properties  自己搞----> 定位bugger 日志文件)  logback.jar   slf4j.jar
                    try 
                    {
                        return URLDecoder.decode(c.getValue(), "utf-8");
                    } catch (UnsupportedEncodingException e) 
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        return cookieName;
    }
}
