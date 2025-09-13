package com.shubham.beandefinition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class MyConfig {

    @Bean
    @Lazy
    Dog dog(){
        return new Dog();
    }
}
