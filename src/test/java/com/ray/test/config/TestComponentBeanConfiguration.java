package com.ray.test.config;

import com.ray.test.bean.TestBean;
import com.ray.test.bean.TestBeanForComponent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.ray.test.bean")
public class TestComponentBeanConfiguration {
    public TestComponentBeanConfiguration(){
        System.out.println("TestBeanConfiguration容器启动初始化。。。");
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestComponentBeanConfiguration.class);
        TestBeanForComponent testBeanForComponent = (TestBeanForComponent) context.getBean("testBeanForComponent");
        testBeanForComponent.sayHello();
    }
}


