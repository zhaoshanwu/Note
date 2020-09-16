package com.sw.jdbctemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class jdbcTemplateDemo2 {

    public static void main(String[] args) {
        // 获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        // 获取对象
        JdbcTemplate jt = ac.getBean("jdbcTemplate",JdbcTemplate.class);
        // 执行操作
        jt.execute("insert into account(name,money)values('张三',1000)");
    }
}
