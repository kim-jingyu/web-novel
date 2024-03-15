package com.webnovel.auth.inspector;

import com.webnovel.auth.exception.InvalidAuthorizationException;
import com.webnovel.login.domain.LoginMember;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class MemberInspector {
    @Pointcut("@annotation(com.webnovel.auth.Member)")
    public void memberPoint() {}

    @Before("memberPoint()")
    public void inspect(JoinPoint joinPoint) {
        Arrays.stream(joinPoint.getArgs())
                .filter(LoginMember.class::isInstance)
                .map(LoginMember.class::cast)
                .filter(LoginMember::isMember)
                .findFirst()
                .orElseThrow(InvalidAuthorizationException::new);
    }
}
