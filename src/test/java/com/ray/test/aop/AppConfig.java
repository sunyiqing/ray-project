package com.ray.test.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
@Import({AfterReturningAdviceTest.class})/*@Aspect可以生效,相当于Configuration类作用,都是配置类*/  
public class AppConfig {

    @Bean(name = "chinese")
    public Chinese chinese() {
        return new Chinese();
    }

}