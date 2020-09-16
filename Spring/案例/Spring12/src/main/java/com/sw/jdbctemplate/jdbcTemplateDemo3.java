package com.sw.jdbctemplate;

import com.sw.entity.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class jdbcTemplateDemo3 {

    public static void main(String[] args) {
        // 获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        // 获取对象
        JdbcTemplate jt = ac.getBean("jdbcTemplate",JdbcTemplate.class);
        // 执行操作
        // 保存
//        jt.update("insert into account(name,money)values(?,?)","张三",1000f);
        // 更新
//        jt.update("update account set name=?,money=? where id=?","王五",1000f,3);
        // 删除
//        jt.update("delete from account where id=?",3);
        // 查询所有
        // 函数众多，需深究，该函数是最适合的，第一个参数为sql语句，第二个参数为封装对象，第三个为sql语句的参数
//        List<Account> accounts = jt.query("select * from account where money > ?",new AccountRowMapper(),1000f);
        // spring有内置的封装对象，BeanPropertyRowMapper<T>,可以直接拿来用
//        List<Account> accounts = jt.query("select * from account where money > ?",new BeanPropertyRowMapper<Account>(Account.class),1000f);
//        for(Account account : accounts) {
//            System.out.println(account);
//        }
        // 查询一个
        List<Account> accounts = jt.query("select * from account where id=?",new BeanPropertyRowMapper<Account>(Account.class),1);
        System.out.println(accounts.isEmpty()?"没有内容":accounts.get(0));
        // 查询返回一行一列(使用聚合函数，不加group by子句)
        // 第二个参数是返回值类型
        Long count = jt.queryForObject("select count(*) from account where money > ?",Long.class,1000f);
        System.out.println(count);
    }
}

/**
 * 定义Account的封装策略
 */
class AccountRowMapper implements RowMapper<Account> {
    /**
     * 把结果集中的数据封装到Account中，然后由spring把每个Account加到集合中
     */
    @Override
    public Account mapRow(ResultSet rs, int i) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setName(rs.getString("name"));
        account.setMoney(rs.getFloat("money"));
        return account;
    }
}