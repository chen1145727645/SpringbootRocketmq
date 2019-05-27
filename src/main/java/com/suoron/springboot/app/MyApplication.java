package com.suoron.springboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.suoron.springboot.controller")
@ComponentScan("com.suoron.springboot.service")
@ComponentScan("com.suoron.springboot.config")
public class MyApplication {
/*

    CatEntity

    @Bean
    public CatEntity getCat(){
        return new CatEntity();
    }
*/

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class,args);
    }
}
