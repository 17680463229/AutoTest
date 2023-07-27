package com.idc.autotest.filter;

import org.springframework.core.annotation.Order;
import org.springframework.http.server.ServletServerHttpRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 第一层拦截器，解决跨域问题。CROSFilter拦截之后再走AuthorizationFilter校验账号密码和token拦截
 * 这个看似前端的问题，其实是后端的，需要在后端给相应头中加上若干跨域相关字段
 * 由于后面我们还要频繁的设置响应头的信息。我们不妨在后端设置一个跨域拦截器CROSFilter，
 * 专门负责对跨域请求进行处理：
 */
@Order(1)
@WebFilter(filterName="CROSFilter",urlPatterns= {"*.action","*"})
public class CROSFilter implements Filter{

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse rep= (HttpServletResponse)response;

        System.out.println("跨域拦截器拦截到请求：" + req.getRequestURI());

        rep.setHeader("Access-Control-Allow-Origin", req.getHeader("origin"));
        rep.setHeader("Access-Control-Allow-Credentials", "true");
//        rep.setHeader("Access-Control-Allow-Headers","Authorization");
        rep.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, HEAD,PUT");
        rep.setHeader("Access-Control-Max-Age", "3600");
        rep.setHeader("Access-Control-Allow-Headers", "access-control-allow-origin, authority, content-type, version-info, X-Requested-With");

        if ("OPTIONS".equals(req.getMethod())) {
            rep.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        System.out.println("session id : " + req.getSession().getId());

        chain.doFilter(request, response);
    }
}

