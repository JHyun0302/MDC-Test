package com.example.mdc.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Aspect
@Component
public class TestAspect {
    @Pointcut("execution(* com.example.mdc.controller.*.*(..))")
    public void controllerAdvice() {

    }

    /**
     * @Before() joinPoint.proceed(); 실행 직전까지 로직만 있으면 된다.
     * 그 뒤는 알아서 실행해줌
     */
    @Before("controllerAdvice()")
    public void requestLogging(JoinPoint joinPoint) {

        //MDC TraceId 세팅
        MDC.put("traceId", UUID.randomUUID().toString());


//        log.info("REQUEST -> {}", jointPoint.getTarget());
        log.info("Request TracingId -> {}", MDC.get("traceId"));
    }

    /**
     * @AfterReturning returning = "result"와 파라미터의 Object result가 매칭되서 return값 찍을 수 있음.
     * 하지만 result값을 return 하기 전에 조작하는 것 불가능.
     */
    @AfterReturning(pointcut = "controllerAdvice()", returning = "result")
    public void requestLogging(JoinPoint joinPoint, Object result) {

//        log.info("RESPONSE -> {}", joinPoint.getTarget());
        log.info("Response TracingId -> {} / RESULT -> {}", MDC.get("traceId"), result);

//        MDC Clear
        MDC.clear();
    }
}
