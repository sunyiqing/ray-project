package com.ray.test.aop;

import org.springframework.stereotype.Component;

@Component
public class Chinese {
    // 实现 Person 接口的 sayHello() 方法
    public String sayHello(String name) {
        String ret = name + " Hello , Spring AOP";
        System.out.println(ret);
        return ret;
    }

    // 定义一个 eat() 方法
    public void eat(String food) {
        System.out.println("我正在吃 :" + food);
    }
}