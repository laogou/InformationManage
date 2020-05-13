//package cn.itcast.web.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//import java.util.ArrayList;
//import java.util.List;
//
///*
//敏感词汇过滤器
// */
//@WebFilter("/sensitiveWordsFilter")
//public class SensitiveWordsFilter implements Filter {
//    public void destroy() {
//    }
//
//    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        //创建代理对象，增强getParameter
//        ServletRequest proxy_req =(ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                //判断是否getParameter方法
//                if( method.getName().equals("getParameter"))
//               {
//                    //增强返回值，
//                    //获取返回值
//                    String value = (String) method.invoke(req,args);
//
//                    if(value!=null){
//                        for (String s : list) {
//                            if(value.contains(s)){
//                                value = value.replaceAll(s, "***");
//                            }
//                        }
//                    }
//                    return value;
//                }
//
//                return method.invoke(req,args);
//            }
//        });
//
//
//        //放行，把代理对象传过来
//
//
//        chain.doFilter(proxy_req, resp);
//    }
//    private List<String> list = new ArrayList<String>(); //敏感词汇集合
//    public void init(FilterConfig config) throws ServletException {
//
//
//
//        try {
//            //1加载文件，获取文件路径
//            ServletContext servletContext = config.getServletContext();
//            String realPath = servletContext.getRealPath("/WEB-INF/classes/word.txt");
//            //2读取文件
//            BufferedReader br = new BufferedReader(new FileReader(realPath));
//            String line = null;
//            while ((line=br.readLine()) !=null){
//                list.add(line);
//            }
//            br.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        //将文件的每一行数据添加到list中
//    }
//
//}
