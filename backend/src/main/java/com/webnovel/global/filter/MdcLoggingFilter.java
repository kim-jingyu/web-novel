package com.webnovel.global.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.webnovel.global.filter.MdcKey.*;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MdcLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        setMdc((HttpServletRequest) servletRequest);
        filterChain.doFilter(servletRequest, servletResponse);
        MDC.clear();
    }

    private void setMdc(HttpServletRequest request) {
        MDC.put(REQUEST_ID.name(), UUID.randomUUID().toString());
        MDC.put(REQUEST_METHOD.name(), request.getMethod());
        MDC.put(REQUEST_URL.name(), request.getRequestURI());
        MDC.put(REQUEST_TIME.name(), LocalDateTime.now().toString());
        MDC.put(REQUEST_IP.name(), request.getRemoteAddr());
    }
}
