package com.demo.jpa.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

  @Pointcut("execution(* com.demo.jpa.service..*(..))")
  public void serviceLayer() {
  }

  @Before("serviceLayer()")
  public void logBefore(JoinPoint joinPoint) {
    log.info("→ Calling: {}.{}() with args: {}",
        joinPoint.getTarget().getClass().getSimpleName(),
        joinPoint.getSignature().getName(),
        Arrays.toString(joinPoint.getArgs()));
  }

  @AfterReturning(pointcut = "serviceLayer()", returning = "result")
  public void logAfterReturning(JoinPoint joinPoint, Object result) {
    log.info("← Returned: {}.{}() → {}",
        joinPoint.getTarget().getClass().getSimpleName(),
        joinPoint.getSignature().getName(),
        result);
  }

  @AfterThrowing(pointcut = "serviceLayer()", throwing = "ex")
  public void logException(JoinPoint joinPoint, Exception ex) {
    log.error("✗ Exception in {}.{}() — {}",
        joinPoint.getTarget().getClass().getSimpleName(),
        joinPoint.getSignature().getName(),
        ex.getMessage());
  }
}
