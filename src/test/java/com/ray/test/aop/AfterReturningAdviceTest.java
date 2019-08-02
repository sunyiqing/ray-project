package com.ray.test.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AfterReturningAdviceTest {
    @AfterReturning(returning = "rvt",pointcut = "execution(* *..*.*(..))")
    public void log(Object rvt){
        System.out.println("AfterReturningAdviceTest==获取目标方法返回值 :" + rvt);
    }
}
