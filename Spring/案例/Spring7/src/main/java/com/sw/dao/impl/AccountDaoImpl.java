package com.sw.dao.impl;

import com.sw.dao.AccountDao;
import com.sw.entity.Account;
import com.sw.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountDaoImpl implements AccountDao {

    @Autowired
    private QueryRunner runner;
    private ConnectionUtils connectionUtils;

    // 使用了Autowired注解，set方法不是必须的了
    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    @Override
    public List<Account> findAllAccount() {
        try {
            return runner.query(connectionUtils.getThreadConnnection(),"select * from account", new BeanListHandler<Account>(Account.class));
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findAccountById(Integer accountId) {
        try {
            return runner.query(connectionUtils.getThreadConnnection(),"select * from account where id=?", new BeanHandler<Account>(Account.class),accountId);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnnection(),"insert into account(name,money)values(?,?)",account.getName(),account.getMoney());
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            runner.update(connectionUtils.getThreadConnnection(),"update account set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAccount(Integer accountId) {
        try {
            runner.update(connectionUtils.getThreadConnnection(),"delete from account where id=?",accountId);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account findAccountByName(String accountName) {
        try {
            List<Account> accounts = runner.query(connectionUtils.getThreadConnnection(),"select * from account where name=?", new BeanListHandler<Account>(Account.class),accountName);
            if(accounts == null || accounts.size() == 0){
                return null;
            } else if(accounts.size() > 1){
                throw new RuntimeException("结果集不唯一");
            }else {
                return accounts.get(0);
            }
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
