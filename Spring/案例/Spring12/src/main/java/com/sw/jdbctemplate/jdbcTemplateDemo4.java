package com.sw.jdbctemplate;

import com.sw.dao.AccountDao;
import com.sw.entity.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class jdbcTemplateDemo4 {

    public static void main(String[] args) {
        // 获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        // 获取对象
        AccountDao accountDao = ac.getBean("accountDao", AccountDao.class);

        Account account = accountDao.findAccountById(1);
        System.out.println(account);

    }
}
