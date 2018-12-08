package com.abba.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author dengbojing
 */
@WebFilter(urlPatterns = "/*",filterName = "XssFilter")
public class XssFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("filter do................................");
        HttpServletRequest servletRequeset = (HttpServletRequest) request;
        System.out.println(servletRequeset.getContextPath());
        System.out.println(servletRequeset.getServletPath());
        System.out.println(servletRequeset.getPathInfo());
        chain.doFilter(request,response);
    }
}
