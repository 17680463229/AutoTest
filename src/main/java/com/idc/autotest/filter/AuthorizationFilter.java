package com.idc.autotest.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * 为了便于演示，我们就使用UUID作为token，并将生成的token存放在session中。
 * 在实际开发中，我们也可以选择将其存放在数据库或者是redis中等等。
 * 先创建UserController和AuthorizationFilter
 */
//目前拦截所有请求，可以根据需求修url匹配样式
//AuthorizationFilter是一层拦截器，拦截需要查验登录情况的请求，将token取出并进行验证。
@WebFilter(filterName="AuthorizationFilter",urlPatterns= {"*"})
public class AuthorizationFilter implements Filter {

    /**
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /**在拦截器中，我们将请求头中的Authorization的值拿出来，
         * 并与session中的token值进行验证。从而甄别用户的登录状态。
         * 简单来说，登录状态可能有三种：
         * 请求头中没有token，标记为用户未登录
         * 请求头中的token与session中的失配，标记为token错误或超时
         * 请求头中的token配对成功，标记为用户已登录
         */

        //注意下转型request对象。ServletRequest中没有获取头部信息的方法
        HttpServletRequest req=(HttpServletRequest) request;

        //获取头部信息中的token
        String token = req.getHeader("Authorization");

        //打印token信息
        System.out.println("用户token为："+token);

        //判断token为空，表示没有登录
        if(token==null || "".equals(token)){
            System.out.println("用户未登录");
            request.setAttribute("verification result","not logged in");
            chain.doFilter(request,response);
        }

        //获取session
        HttpSession session=req.getSession();

        //从session中获取服务端存储的token
        String tokenServer = (String) session.getAttribute("token");

        //判断如果请求头中token与服务端token不一致，显示超时
        if (!token.equals(tokenServer)){
            System.out.println("token错误或超时");
            request.setAttribute("verification result", "wrong token");

            chain.doFilter(request,response);
            return;
        }

        

        //用户token匹配成功
        request.setAttribute("verification result", "logged in");
        chain.doFilter(request,response);
    }


}
