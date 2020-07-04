package com.example.demo.throttle;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;


@Aspect
@Component
public class ThrottlingAspect {

    private final ThrottlingManager throttlingManager;

    @Autowired
    public ThrottlingAspect(ThrottlingManager throttlingManager) {
        this.throttlingManager = throttlingManager;
    }

    @Pointcut("within(@(@org.springframework.stereotype.Controller *) *)")
    public void controllerPointcut() {
        // pointuct
    }

    @Before("controllerPointcut()")
    public void log(JoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        ThrottlingConfig throttlingConfig = getThrottlingConfig(method);
        EndpointMethod endpointMethod = new EndpointMethod(pjp.getTarget().getClass(), method.getName());
        UserIdProvider.getCurrentuserId()
                .ifPresent(id -> throttlingManager.throttleRequest(endpointMethod, id, throttlingConfig));
    }

    private ThrottlingConfig getThrottlingConfig(Method method) {
        return Arrays.stream(method.getDeclaredAnnotations())
                .filter(d -> d.annotationType() == Throttle.class)
                .findFirst()
                .map(d -> {
                    Throttle t = (Throttle) d;
                    return new ThrottlingConfig(t.timeFrameInSeconds(), t.calls(), "${throttle.header}");
                })
                .orElse(ThrottlingConfig.DEFAULT);
    }

}
