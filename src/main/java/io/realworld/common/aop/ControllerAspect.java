package io.realworld.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Aspect for logging execution of service and repository Spring components.
 * @author Ramesh Fadatare
 *
 */
@Slf4j
@Aspect
@Component
public class ControllerAspect {

    @Pointcut("execution(* io.realworld..*.api.*Controller.*(..) )")
    public void controllerAdvice() {
    }

    @AfterThrowing(pointcut = "controllerAdvice()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with cause = \'{}\' and exception = \'{}\'", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL", e.getMessage(), e);
    }

    @Before("controllerAdvice()")
    public void requestLogging(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        log.debug("[" + joinPoint.getSignature().toShortString() + "] -------------------------- Controller START --------------------------");
        log.debug("[" + joinPoint.getSignature().toShortString() + "] URL : " + request.getMethod() + " " + request.getRequestURI() );
        log.debug("[" + joinPoint.getSignature().toShortString() + "] Controller Parameters: " + Arrays.stream(joinPoint.getArgs()).map(String::valueOf).collect(Collectors.joining(",", "[", "]")));
    }

    @AfterReturning(pointcut = "controllerAdvice()", returning = "result")
    public void requestLogging(JoinPoint joinPoint, Object result) {
        log.debug("[" + joinPoint.getSignature().toShortString() + "] Controller Returned: " + result);
        log.debug("[" + joinPoint.getSignature().toShortString() + "] -------------------------- Controller FINISH --------------------------");

    }
}
