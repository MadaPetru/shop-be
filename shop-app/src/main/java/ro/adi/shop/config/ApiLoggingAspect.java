package ro.adi.shop.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Aspect
@Component
@Slf4j
public class ApiLoggingAspect {

    @Around("@annotation(ro.adi.shop.config.LoggingPerformanceApi)")
    public Object logApiExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        var methodName = joinPoint.getSignature().getName();
        var startDate = LocalDateTime.now();
        log.info("API execution started at {} for method: {}", startDate, methodName);

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("Exception occurred during API execution for method: {}", methodName);
            throw throwable;
        }
        var endDate = LocalDateTime.now();
        var timeElapsedInMilliseconds = endDate.toInstant(ZoneOffset.UTC).toEpochMilli() - startDate.toInstant(ZoneOffset.UTC).toEpochMilli();
        log.info("API execution completed at {} for method: {} with time elapsing in milliseconds of: {}", endDate, methodName, timeElapsedInMilliseconds);
        return result;
    }
}

