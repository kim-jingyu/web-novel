package com.webnovel.global.interceptor;

import com.webnovel.aop.QueryCounter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class Logger implements HandlerInterceptor {
    private final QueryCounter queryCounter;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        int count = queryCounter.getCount();
        log.info("REQUEST METHOD = {}, URL = {}, STATUS_CODE = {}, QUERY_COUNT = {}", request.getMethod(), request.getRequestURI(), response.getStatus(), count);
        if (count >= 10) {
            log.warn("쿼리가 {}번 이상 실행되었습니다.");
        }
    }
}
