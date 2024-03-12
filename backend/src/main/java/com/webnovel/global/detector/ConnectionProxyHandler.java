package com.webnovel.global.detector;

import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

@RequiredArgsConstructor
public class ConnectionProxyHandler implements MethodInterceptor {
    private static final String PREPARE_STATEMENT = "prepareStatement";
    private static final String HIKARI_PROXY_CONNECTION = "HikariProxyConnection";

    private final Object connection;
    private final LoggingForm loggingForm;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result = invocation.proceed();

        if (hasConnection(result) && hasPreparedStatement(invocation)) {
            ProxyFactory proxyFactory = new ProxyFactory(result);
            proxyFactory.addAdvice(new PreparedStatementProxyHandler(loggingForm));
            return proxyFactory.getProxy();
        }

        return result;
    }

    private boolean hasConnection(Object result) {
        return result != null;
    }

    private boolean hasPreparedStatement(MethodInvocation invocation) {
        Object target = invocation.getThis();
        if (target == null) {
            return false;
        }
        Class<?> targetClass = target.getClass();
        Method targetMethod = invocation.getMethod();
        return targetClass.getName().contains(HIKARI_PROXY_CONNECTION)
                && targetMethod.getName().equals(PREPARE_STATEMENT);
    }

    public Object getProxy() {
        ProxyFactory proxyFactory = new ProxyFactory(connection);
        proxyFactory.addAdvice(this);
        return proxyFactory.getProxy();
    }
}
