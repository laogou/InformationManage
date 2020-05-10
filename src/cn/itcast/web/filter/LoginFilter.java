package cn.itcast.web.filter;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;


/**
 * 完成登录验证的过滤器
 *
 *
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //由于参数是servletRequest，首先要进行强制转换
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        //获取资源请求路径
        String requestURI = httpServletRequest.getRequestURI();
        //判断是否包含登录相关资源路径
        //这里注意不要仅仅排除某个文件，相关的jsp，css，js，图片等资源都要排除
        if (requestURI.contains("/login.jsp") || requestURI.contains("/loginServlet") ||requestURI.contains("/css/") ||requestURI.contains("/js/") ||requestURI.contains("/fonts/")||requestURI.contains("/checkCodeServlet")){
            //包含，就是用户想要登录，直接放行。
            chain.doFilter(req, resp);
        }
        else{
            //不包含，首先要验证用户是否已经登录
            Object user = httpServletRequest.getSession().getAttribute("user");
            if(user!=null){
                //已经登录，放行
                chain.doFilter(req, resp);
            }
            else {
                httpServletRequest.setAttribute("login_msg","未登录");
                httpServletRequest.getRequestDispatcher("/login.jsp").forward(httpServletRequest,resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
