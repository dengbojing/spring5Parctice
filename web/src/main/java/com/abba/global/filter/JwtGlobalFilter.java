package com.abba.global.filter;

import com.abba.entity.response.BaseResponse;
import com.abba.entity.Subject;
import com.abba.global.filter.wrapper.MutableHttpServletRequest;
import com.abba.util.JwtUtil;
import com.abba.util.StringHelper;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author dengbojing
 */
@WebFilter(urlPatterns = "/*",filterName = "GlobalFilter",asyncSupported = false,
initParams = {@WebInitParam(name = "whiteList", value = "/;/index;/login;/login/cipher;/login/cipher/verify")})
@Slf4j
public class JwtGlobalFilter implements Filter{

    private List<String> whiteList;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        whiteList = StringHelper.split(filterConfig.getInitParameter("whiteList"),";");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest)request;
        ServletContext servletContext = request.getServletContext();
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        String path = servletRequest.getServletPath();
        BaseResponse base = BaseResponse.builder().build();
        if(!whiteList.contains(path) && !path.contains(".")){
            //获取,解析token
            String token = servletRequest.getHeader("X-Authorization");
            checkNotNull(context);
            JwtUtil jwtUtil = context.getBean(JwtUtil.class);
            if(StringHelper.isNotEmpty(token)){
                //验证token合法性
                try{
                    Subject subject = jwtUtil.subject(token);
                    MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(servletRequest);
                    mutableRequest.header("X-UserId", subject.getUserId())
                            .header("X-OrganizationId", subject.getCompanyId())
                            .header("X-OrganizationType", subject.getCompanyType().name())
                            .header("X-PreCaller", "GATEWAY");
                    chain.doFilter(mutableRequest,response);
                }catch(ExpiredJwtException ex){
                    base.setMessage("token expired");
                    base.setStatus(HttpStatus.UNAUTHORIZED.value());
                    log.error(ex.getMessage());
                    response.getWriter().write(JSON.toJSONString(base));
                }
            }else{
                base.setStatus(HttpStatus.UNAUTHORIZED.value());
                base.setMessage("Unauthorized");
                response.getWriter().write(JSON.toJSONString(base));
            }
        }else{
            chain.doFilter(request,response);
        }

    }

    @Override
    public void destroy() {

    }
}
