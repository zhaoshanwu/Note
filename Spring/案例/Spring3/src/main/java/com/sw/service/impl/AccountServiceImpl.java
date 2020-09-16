package com.sw.service.impl;

import com.sw.service.AccountService;

import java.util.Date;

public class AccountServiceImpl implements AccountService {
    // 经常需要变化的数据，不适用于注入的方式
    private String name;
    private Integer age;
    private Date birthday;

    public AccountServiceImpl(String name,Integer age,Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }


    public void saveAccount() {
        System.out.println("service中的saveAccount方法执行了" + name + " " + age + " " + birthday);
    }

}
