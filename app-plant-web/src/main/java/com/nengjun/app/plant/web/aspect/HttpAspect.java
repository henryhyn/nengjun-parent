package com.nengjun.app.plant.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Henry on 2017/7/13.
 */
@Slf4j
@Aspect
@Component
public class HttpAspect {
    @Pointcut("execution(* com.nengjun.app.plant.web.controller.*Controller.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("REQ: {}", formatJoinPoint(joinPoint, request));
    }

    private String formatJoinPoint(JoinPoint joinPoint, HttpServletRequest request) {
        return String.format("%s %s %s %s.%s(%s)"
                , request.getMethod()
                , request.getRequestURL()
                , request.getRemoteAddr()
                , joinPoint.getSignature().getDeclaringTypeName()
                , joinPoint.getSignature().getName()
                , StringUtils.join(joinPoint.getArgs(), ", "));
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        log.info("RES: {}", object);
    }
}
