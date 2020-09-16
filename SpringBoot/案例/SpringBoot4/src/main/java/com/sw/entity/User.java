package com.sw.entity;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated  //开启属性校验
public class User {
    @NotNull
    private String username;
}
