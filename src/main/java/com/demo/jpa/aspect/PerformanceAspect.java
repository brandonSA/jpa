package com.demo.jpa.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {

  private static final long SLOW_THRESHOLD_MS = 500;

  @Around("execution(* com.demo.jpa.service..*(..))")
  public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.currentTimeMillis();
    Object result = joinPoint.proceed();
    long duration = System.currentTimeMillis() - start;

    if (duration > SLOW_THRESHOLD_MS) {
      log.warn("⚠ SLOW METHOD: {}.{}() took {}ms",
          joinPoint.getTarget().getClass().getSimpleName(),
          joinPoint.getSignature().getName(),
          duration);
    } else {
      log.debug("⏱ {}.{}() took {}ms",
          joinPoint.getTarget().getClass().getSimpleName(),
          joinPoint.getSignature().getName(),
          duration);
    }

    return result;
  }
}
