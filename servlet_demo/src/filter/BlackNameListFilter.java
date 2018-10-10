package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 黑名单过滤器
 * 
 *  <!-- 黑名单过滤器 -->
    <filter>
         <filter-name>blackNameListFilter</filter-name>
         <filter-class>filter.BlackNameListFilter</filter-class>
         
         <!-- 配置黑名单 -->
         <init-param>
              <param-name>blackNameList</param-name>
              <param-value>127.0.0.1</param-value>
         </init-param>
    </filter>
    <filter-mapping>
        <filter-name>blackNameListFilter</filter-name>
        <url-pattern>/servletContext</url-pattern>
    </filter-mapping>
 *
 */
// 此过滤器只拦截http://127.0.0.1:8080/servlet_demo1/servletContext请求
// @WebFilter(filterName="blackNameListFilter", urlPatterns="/servletContext", initParams={@WebInitParam(name="blackNameList", value="127.0.0.1")})
public class BlackNameListFilter implements Filter 
{
    private FilterConfig filterConfig;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        this.filterConfig = filterConfig;
    }
    
    

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException 
    {
        HttpServletRequest request =  (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        
        /**
         *  1.获取远程访问者的IP地址
         */
        String remoteAddr = request.getRemoteAddr();
        
        /**
         *  2. 获取在系统中配置的黑名单用户
         */
        
        String blackNameStr = filterConfig.getInitParameter("blackNameList");
        
        // 是黑名单用户
        if (blackNameStr.indexOf(remoteAddr) != -1)
        {
             System.out.println("对不起您是黑名单用户,不能访问!");
             response.sendRedirect(request.getContextPath() + "/black.jsp");
             
             // 加return:避免   HTTP Status 500 - Cannot forward after response has been committed
             return;
        }
        
        
        
        // 黑名单过滤器放行
        chain.doFilter(request, response);
        
    }

    @Override
    public void destroy() 
    {

    }

}
