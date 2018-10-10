package day01;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

/**
 * http://127.0.0.1:8080/servlet_demo1/servletContext
 * http://localhost:8080/servlet_demo1/servletContext
 * 
 * ServletContext域对象:Servlet上下文  ---> application(jsp的内置对象) 应用 ----> servlet_demo1
 * 
 * <servlet>
        <servlet-name>ServletContextTest</servlet-name>
        <servlet-class>day01.ServletContextTest</servlet-class>
        
        <init-param>
             <param-name>encoding</param-name>
             <param-value>utf-8</param-value>
        </init-param>
    </servlet>
    <servlet-mapping>
        <servlet-name>ServletContextTest</servlet-name>
        <url-pattern>/servletContext</url-pattern>
    </servlet-mapping> 
 *
 * 
 */
@WebServlet(value={"/servletContext"}, initParams={@WebInitParam(name="encoding", value="utf-8")})
public class ServletContextTest extends HttpServlet 
{

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;
    


    /**
     *  在web.xml设置局部初始化参数配置:
            <init-param>
                 <param-name>encoding</param-name>
                 <param-value>utf-8</param-value>
            </init-param>
            
                   获取局部初始化参数(某个servlet) ---> 采用ServletConfig:
                   if init(ServletConfig config)中的就要调用super.init(config); ---> getServletContext() ---> NPE NullPointerException
     */
    /*
    @Override
    public void init(ServletConfig config) throws ServletException 
    {
        // 避免getServletContext() ---> NPE NullPointerException
        super.init(config);
        
        String encoding = config.getInitParameter("encoding");
        System.out.println("========================================" + encoding);
    }
    */
    

    /**
     * HttpServlet  extends GenericServlet
     * GenericServlet implements Servlet
     * 
     *    GenericServlet类中有一个getServletContext();
     */
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        
        /**
         * 获取ServletContext方法1:从request对象中的取(建议 掌握)
         */
        ServletContext application1 = request.getServletContext();
        System.out.println(application1);
        
         /**
          *  获取ServletContext方法2: GenericServlet 类中有一个getServletContext();
          */
          ServletContext application2 = getServletContext();
          System.out.println(application2);
        
          
          /**
           * 获取ServletContext方法3:通过ServletConfig对象获取(GenericServlet 类中有一个getServletConfig())
           */
           ServletConfig servletConfig = getServletConfig();  // ServletConfig  Servlet配置对象
           String encoding = servletConfig.getInitParameter("encoding");
           System.out.println("========================================" + encoding);
           
           ServletContext application3 = servletConfig.getServletContext();
           System.out.println(application3);
           
           /**
            * ServletContext的作用:
            *    1.获取的是全局初始化参数
            *    2.文件上传时用:获取web服务器的根目录(最后带/)
            *    3.applicaton作用域的设置值给 ---> 前台页面取值用
            *    4.读取src下面的配置文件(了解)
            */
           String username = application3.getInitParameter("username");
           System.out.println(username);
           
           
           String realPath1 = application3.getRealPath("");
           System.out.println(realPath1);
           
           String realPath2 = application3.getRealPath("/");
           System.out.println(realPath2);
           
           // web服务器的根目录/upload
           String realPath3 = application3.getRealPath("/upload");
           System.out.println(realPath3);
           
           // 跨平台的 斜杠
           String separator = File.separator;
           System.out.println(separator);
           
           // className的值在整个应用servlet_demo1中都能取到
           application3.setAttribute("className", "zte6");
           
           InputStream in = ServletContextTest.class.getClassLoader().getResourceAsStream("dataSource.properties");
           System.out.println(in);
           
           InputStream in2 = application3.getResourceAsStream("/WEB-INF/classes/dataSource.properties");
           System.out.println(in2);
           
           long startTime = System.currentTimeMillis();
           
           // 文件copy  org.springframework.util.StreamUtils
           StreamUtils.copy(in2, new FileOutputStream("D:/1.properties"));
           long endTime = System.currentTimeMillis();
           System.out.println("cost: " + (endTime - startTime) + " 毫秒");
           
           // since jdk 1.7 有了 Files类 ---> 文件复制
           Files.copy(new File(application3.getRealPath("/") + "WEB-INF/classes/dataSource.properties").toPath(), new FileOutputStream("D:/2.properties"));
    }



}
