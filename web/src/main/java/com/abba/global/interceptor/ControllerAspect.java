package com.abba.global.interceptor;

import com.abba.config.ApiProperties;
import com.abba.entity.AbstractParam;
import com.abba.entity.request.RequestHolder;
import com.abba.util.AspectHelper;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author dengbojing
 */
@Slf4j
@Aspect
@Component
public class ControllerAspect {

    private final ApiProperties apiProperties = new ApiProperties();

    private volatile Map<String, AtomicInteger> apiVisits = new ConcurrentHashMap<>();

    private volatile AtomicLong totalVisit = new AtomicLong(0);

    /**
     * API接口调用切面配置
     */
    @Pointcut("execution(com.abba.entity.response.BaseResponse com.abba..controller..*Controller.*(..))")
    public void executeForAPI() {
    }

    /**
     * API接口调用切面配置
     */
    @Pointcut("execution(com.abba.entity.response.PageResponse com.abba.controller.*Controller.*(..))")
    public void executeForAPI0() {
    }

    /**
     * 环绕通知
     *
     * @param proceedingJoinPoint 切点信息
     * @return object
     * @throws Throwable ...
     */
    @Around("executeForAPI() || executeForAPI0() ")
    public Object aroundExecuteForAPI(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        val requestId = UUID.randomUUID().toString().replace("-", "");
        logRequest(requestId, proceedingJoinPoint);
        val start = System.currentTimeMillis();
        val requestAttributes = RequestContextHolder.getRequestAttributes();
        val args = proceedingJoinPoint.getArgs();
        if (requestAttributes == null || AspectHelper.hasFileArg(args)) {
            return proceedingJoinPoint.proceed();
        }
        val requestInfo = RequestHolder.info();
        final String post = "POST";
        if (!post.equals(requestInfo.getMethod())) {
            return proceedingJoinPoint.proceed();
        }
        if (args.length > 0) {
            for (Object arg : args) {
                if (arg instanceof AbstractParam) {
                    ((AbstractParam) arg).setUserId(requestInfo.getUser().getUserId());
                    ((AbstractParam) arg).setCompanyId(requestInfo.getUser().getCompanyId());
                    break;
                }
            }
        }
        if (apiProperties.getLog()) {
            log.info(new SimpleRequest(requestInfo.getPath(), args).string());
        }

        Object body = proceedingJoinPoint.proceed(args);
        // 请求时间
        val time = (System.currentTimeMillis() - start);
        // 记录请求次数
        totalVisit.incrementAndGet();
        if (!apiVisits.containsKey(requestInfo.getPath())) {
            apiVisits.put(requestInfo.getPath(), new AtomicInteger(0));
        } else {
            apiVisits.get(requestInfo.getPath()).incrementAndGet();
        }
        // 延迟较高的请求log
        if (time > apiProperties.getMs()) {
            log.warn(String.format("%-3sms %-52s", time, requestInfo.getPath()));
        }
        // 每指定次数打印log
        if (totalVisit.get() % apiProperties.getStep() == 0L) {
            log.info("总请求次数：" + totalVisit.get());
            log.info(JSON.toJSONString(apiVisits));
        }
        logResponse(requestId, time, body);
        return body;
    }

    private void logRequest(String requestId, ProceedingJoinPoint proceedingJoinPoint) {
        val logInfo = new HashMap<String, Object>();
        logInfo.put("requestId", requestId);
        logInfo.put("logType", "request");
        val requestInfo = RequestHolder.info();
        logInfo.put("path", requestInfo.getPath());
        logInfo.put("method", requestInfo.getMethod());
        logInfo.put("from", requestInfo.getFrom());
        logInfo.put("host", requestInfo.getHost());
        logInfo.put("userId", requestInfo.getUser().getUserId());
        List<Object> params = new ArrayList<>();
        val args = proceedingJoinPoint.getArgs();
        if (null != args) {
            for (Object arg : args) {
                if (!(arg instanceof MultipartFile || arg instanceof HttpServletRequest)) {
                    params.add(arg);
                }
            }
        }
        logInfo.put("params", params);
        log.info(JSON.toJSONString(logInfo));
    }

    private void logResponse(String requestId, long time, Object body) {
        val logInfo = new HashMap<String, Object>();
        logInfo.put("logType", "response");
        logInfo.put("requestId", requestId);
        logInfo.put("time", time);
        logInfo.put("body", body);
        log.info(JSON.toJSONString(logInfo));
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private class SimpleRequest {
        private String url;
        private Object param;

        private String string() {
            return JSON.toJSONString(this);
        }
    }

}
