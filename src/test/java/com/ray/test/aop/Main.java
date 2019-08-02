package com.ray.test.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Chinese outPut = (Chinese) context.getBean("chinese");
        outPut.sayHello("duan");
    }
}