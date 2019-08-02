package com.ray.test.config;

import com.ray.test.bean.TestBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TestBeanConfiguration {
    public TestBeanConfiguration(){
        System.out.println("TestBeanConfiguration容器启动初始化。。。");
    }

//    @Bean
//    @Scope("prototype")
//    public TestBean testBean(){
//        return new TestBean();
//    }

    @Bean(name="testBean",initMethod="start",destroyMethod="cleanUp")
    @Scope("prototype")
    public TestBean testBean(){
        return new TestBean();
    }
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestBeanConfiguration.class);
        TestBean testBean = (TestBean) context.getBean("testBean");
        testBean.sayHello();
    }
}


