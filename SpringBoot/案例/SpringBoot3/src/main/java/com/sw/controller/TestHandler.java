package com.sw.controller;

import com.sw.entity.AcmeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestHandler {

    @Autowired
    private AcmeProperties acmeProperties;
    @RequestMapping("/test")
    public AcmeProperties test() {
        return acmeProperties;
    }
}
