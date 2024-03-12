package com.webnovel.global.detector;

import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.List;

@RequiredArgsConstructor
public class PreparedStatementProxyHandler implements MethodInterceptor {
    private static final List<String> JDBC_QUERY_METHOD = List.of("executeQuery", "execute", "executeUpdate");
    private final LoggingForm loggingForm;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();

        if (JDBC_QUERY_METHOD.contains(method.getName())) {
            long startTime = System.currentTimeMillis();
            Object result = invocation.proceed();
            long endTime = System.currentTimeMillis();

            loggingForm.addQueryTime(endTime - startTime);
            loggingForm.queryCountUp();

            return result;
        }

        return invocation.proceed();
    }
}
