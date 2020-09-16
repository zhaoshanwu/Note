package com.sw.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
@Component
// 在属性类中，前缀不能用驼峰模式，只能用羊肉串模式，但是在yml配置文件里可以使用驼峰模式来设置
@ConfigurationProperties("acme.my-person.person")
@Validated  //开启属性校验
public class OwnerProperties {

    @NotNull
    private String firstName;
    @Max(35)
    private int age;
    @Email
    private String email;

    private User user;

}
