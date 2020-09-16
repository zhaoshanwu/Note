package com.sw.ui;

import com.sw.service.AccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟表现层，用于调用业务层
 */
public class Client {

    public static void main(String[] args) {
//        AccountService as = new AccountServiceImpl();
        // 获取核心容器对象
//        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");

        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");

        AccountService as = (AccountService) ac.getBean("accountService");

        as.saveAccount();

        ac.close();

//        AccountService as2 = (AccountService) ac.getBean("accountService");
//        System.out.println(as == as2);

    }
}
