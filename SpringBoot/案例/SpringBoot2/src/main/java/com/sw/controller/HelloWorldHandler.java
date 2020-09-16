package com.sw.controller;

import com.sw.entity.YmalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldHandler {

    @Autowired
    private YmalEntity ymalEntity;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping("/yaml")
    public YmalEntity yaml() {
        return ymalEntity;
    }
}
