package com.sw.ui;

import com.sw.factory.BeanFactory;
import com.sw.service.AccountService;

/**
 * 模拟表现层，用于调用业务层
 */
public class Client {
    public static void main(String[] args) {
//        AccountService as = new AccountServiceImpl();
        AccountService as = (AccountService) BeanFactory.getBean("accountService");
        as.saveAccount();
    }
}
