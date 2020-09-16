package com.sw.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
@Component
public class ValueProperties {

    @Value("${acme.my-person.person.firstName}")
    private String firstName;
    @Value("#{12*3}")
    private int age;

    private String email;

    @Value("${acme.list}")
//    private List<String> list;
    private String list;
}
