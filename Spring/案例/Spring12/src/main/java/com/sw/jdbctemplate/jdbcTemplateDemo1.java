package com.sw.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class jdbcTemplateDemo1 {

    public static void main(String[] args) {
        // 准备数据源
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/mybatis?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC\n");
        ds.setUsername("root");
        ds.setPassword("sw1111");
        // 1、创建jdbcTemplate对象
        JdbcTemplate jt = new JdbcTemplate();
        jt.setDataSource(ds);
        // 2、执行操作
        jt.execute("insert into account(name,money)values('张三',1000)");
    }
}
