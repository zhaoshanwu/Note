package com.sw.dao.impl;

import com.sw.dao.AccountDao;
import com.sw.entity.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

/**
 * 如果采用继承的方法就是这个类这样的配置，但是缺点是不能使用注解\
 * jdbcDaoSupport spring也想到继承的方法了，所以也提供了这个类用来创建需要的变量
 */
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {
    /**
     * 就可以继承JdbcDaoSupport 使用里面声明过的jdbcTemplate
     * 然后把所有的jdbcTemplate改为super.getJdbcTemplate()
     */

    public Account findAccountById(Integer accountId) {
        List<Account> accounts = super.getJdbcTemplate().query("select * from account where id=?",new BeanPropertyRowMapper<Account>(Account.class),1);
        return accounts.isEmpty()?null:accounts.get(0);
    }


    public Account findAccountByName(String accountName) {
        List<Account> accounts = super.getJdbcTemplate().query("select * from account where name=?",new BeanPropertyRowMapper<Account>(Account.class),"张三");
        if(accounts.isEmpty()){
            return null;
        }else if(accounts.size() > 1) {
            throw new RuntimeException("结果集不唯一");
        }
        return accounts.get(0);
    }


    public void updateAccount(Account account) {
        super.getJdbcTemplate().update("update account set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());
    }
}
