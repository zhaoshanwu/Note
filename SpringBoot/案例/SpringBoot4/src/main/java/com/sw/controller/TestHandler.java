package com.sw.controller;

import com.sw.entity.AcmeProperties;
import com.sw.entity.OwnerProperties;
import com.sw.entity.ValueProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties(AcmeProperties.class)
// 因为在AcmeProperties中没加@Component,所以需要在要使用的地方进行开启，还要指定开启的类是哪个，这是使用构造器函数所必须的注解
public class TestHandler {

    @Autowired
    private AcmeProperties acmeProperties;

    @RequestMapping("/test")
    public AcmeProperties test() {
        return acmeProperties;
    }

    @Autowired
    private OwnerProperties ownerProperties;

    @RequestMapping("/owner")
    public OwnerProperties owner() {
        return ownerProperties;
    }

    @Autowired
    private ValueProperties valueProperties;

    @RequestMapping("/value")
    public ValueProperties value() {
        return valueProperties;
    }
}
