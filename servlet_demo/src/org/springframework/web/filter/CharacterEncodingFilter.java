package org.springframework.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * java web的3大组件:  Servlet  Filter  Listener
 * 
 * 
 * 以后遇到缓存问题：
 *     1.删除tomcat的缓存  tomat的目录下:  .../work
 *     2.删除部署在tomcat里面下面此应用  ... webapps/servlet_demo1
 *     3.清除浏览器缓存  ctrl + shift + delete   ---> 要重新打开浏览器
 * 
 * 字符编码过滤器:
 *     1. 写一个类  implements Filter
 *     2. 在web.xml中配置此过滤器
 *     
 *     
 * 多个过滤器的执行顺序:哪一个过滤器的url-pattern在上面 谁优先
 * 
 * @author zte
 *
 */
public class CharacterEncodingFilter implements Filter
{

    /**
    <filter>
         <filter-name>character</filter-name>
         <filter-class>day01.filter.CharacterEncodingFilter</filter-class>
         
         <!-- 配置字符编码过滤器 初始化参数: 字符编码 -->
         <init-param>
              <param-name>encoding</param-name>
              <param-value>utf-8</param-value>
         </init-param>
    </filter>
    <filter-mapping>
        <filter-name>character</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
     */
    
    private String encoding;
    
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException 
    {
        encoding = filterConfig.getInitParameter("encoding");
    }

    
    /**
     * CharacterEncodingFilter 字符编码过滤器   放行 --->  doFilter
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
            throws IOException, ServletException 
    {
        HttpServletRequest request =  (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        
        request.setCharacterEncoding(encoding);
        
        response.setContentType("text/html;charset=" + encoding);
        response.setCharacterEncoding(encoding);
        
        // 过滤器放行
        filterChain.doFilter(request, response);
     
    }
    

    @Override
    public void destroy() 
    {
        
    }

   
}
