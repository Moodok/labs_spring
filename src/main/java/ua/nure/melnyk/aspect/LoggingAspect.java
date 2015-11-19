package ua.nure.melnyk.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ua.nure.melnyk.mbean.Config;

import java.util.Arrays;

@Aspect
public class LoggingAspect {

    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    private Config configBean;

    public void setConfigBean(Config configBean) {
        this.configBean = configBean;
    }

    @Before("execution(* ua.nure.melnyk.facade.impl.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        if (configBean.isShouldLog())
            logger.info("Method is being called : {} with args {} on object {}"
                    , joinPoint.getSignature().getName()
                    , Arrays.toString(joinPoint.getArgs())
                    , joinPoint.getTarget());
    }

    @Around("within(ua.nure.melnyk.dao.storage.impl.*)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        if (configBean.isShouldLog())
            logger.info("{} : ({}) is being called with params {}; Result = {}"
                    , joinPoint.getTarget().getClass()
                    , joinPoint.getSignature().getName()
                    , Arrays.toString(joinPoint.getArgs())
                    , result);
        return result;
    }


}
