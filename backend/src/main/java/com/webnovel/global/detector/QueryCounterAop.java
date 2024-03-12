package com.webnovel.global.detector;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class QueryCounterAop {
    private final ThreadLocal<LoggingForm> currentLoggingForm;

    public QueryCounterAop() {
        this.currentLoggingForm = new ThreadLocal<>();
    }

    @Pointcut("execution(* javax.sql.DataSource.getConnection(..))")
    private void connectionPointCut() {}

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    private void restControllerPointCut() {}

    @Around("connectionPointCut()")
    public Object getConnection(ProceedingJoinPoint joinPoint) throws Throwable {
        Object connection = joinPoint.proceed();

        return new ConnectionProxyHandler(connection, getCurrentLoggingForm()).getProxy();
    }

    @After("restControllerPointCut()")
    public void logAfterApi() {
        LoggingForm loggingForm = getCurrentLoggingForm();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            loggingForm.setRouterUrl(request.getRequestURI());
            loggingForm.setRouterMethod(request.getMethod());
        }
        log.info("{}", getCurrentLoggingForm());
        currentLoggingForm.remove();
    }

    private LoggingForm getCurrentLoggingForm() {
        if (currentLoggingForm.get() == null) {
            currentLoggingForm.set(new LoggingForm());
        }
        return currentLoggingForm.get();
    }
}
