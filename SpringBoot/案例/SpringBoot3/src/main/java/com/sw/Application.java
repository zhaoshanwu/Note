package com.sw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // 启动Spring Boot应用(固定写法)
        SpringApplication.run(Application.class,args);
    }
}