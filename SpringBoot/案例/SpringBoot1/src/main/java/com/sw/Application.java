package com.sw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 类名可自定义
 * 用@SpringBootApplication标注一个主程序类，说明这是一个Spring Boot应用
 *      如果不想使用@SpringBootApplication，可以使用其导入的@EnableAutoConfiguration和@ComponentScan，实现的功能是一样的
 * 必须要放在要扫描的包或类的上一级，否则无法扫描到
 */
@SpringBootApplication
//@ComponentScan
//@EnableAutoConfiguration
public class Application {
    public static void main(String[] args) {
        // 启动Spring Boot应用(固定写法)
        SpringApplication.run(Application.class,args);
    }
}