package com.ray.test.bean;

import org.springframework.stereotype.Component;

@Component
public class TestBeanForComponent {
    private String userName;
    private String url;

    public void sayHello() {
        System.out.println("TestBean sayHello...");
    }

    public String toString() {
        return "username:" + this.userName + ",url:" + this.url ;
    }

    public void start() {
        System.out.println("TestBean 初始化。。。");
    }

    public void cleanUp() {
        System.out.println("TestBean 销毁。。。");
    }
}
