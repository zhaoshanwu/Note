package com.sw.service.impl;

import com.sw.dao.AccountDao;
import com.sw.entity.Account;
import com.sw.service.AccountService;
import com.sw.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountServiceImpl2 implements AccountService {

    @Autowired
    private AccountDao accountDao;
    private TransactionManager txManager;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    public List<Account> findAllAccount() {
        try{
            // 1、开启事务
            txManager.beginTransaction();
            // 2、执行操作
            List<Account> accounts =  accountDao.findAllAccount();
            // 3、提交事务
            txManager.commit();
            // 4、返回结果
            return accounts;
        }catch (Exception e) {
            // 5、回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        }finally {
            // 6、释放连接
            txManager.release();
        }
    }

    public Account findAccountById(Integer accountId) {
        try{
            // 1、开启事务
            txManager.beginTransaction();
            // 2、执行操作
            Account account =  accountDao.findAccountById(accountId);
            // 3、提交事务
            txManager.commit();
            // 4、返回结果
            return account;
        }catch (Exception e) {
            // 5、回滚操作
            txManager.rollback();
            throw new RuntimeException(e);
        }finally {
            // 6、释放连接
            txManager.release();
        }
    }

    public void saveAccount(Account account) {
        try{
            // 1、开启事务
            txManager.beginTransaction();
            // 2、执行操作
            accountDao.saveAccount(account);
            // 3、提交事务
            txManager.commit();
            // 4、返回结果
        }catch (Exception e) {
            // 5、回滚操作
            txManager.rollback();
        }finally {
            // 6、释放连接
            txManager.release();
        }
    }

    public void updateAccount(Account account) {
        try{
            // 1、开启事务
            txManager.beginTransaction();
            // 2、执行操作
            accountDao.updateAccount(account);
            // 3、提交事务
            txManager.commit();
            // 4、返回结果
        }catch (Exception e) {
            // 5、回滚操作
            txManager.rollback();
        }finally {
            // 6、释放连接
            txManager.release();
        }
    }

    public void deleteAccount(Integer accountId) {
        try{
            // 1、开启事务
            txManager.beginTransaction();
            // 2、执行操作
            accountDao.deleteAccount(accountId);
            // 3、提交事务
            txManager.commit();
            // 4、返回结果
        }catch (Exception e) {
            // 5、回滚操作
            txManager.rollback();
        }finally {
            // 6、释放连接
            txManager.release();
        }

    }

    public void transfer(String sourceName, String targetName, Float money) {
        try{
            // 1、开启事务
            txManager.beginTransaction();
            // 2、执行操作
                // 2.1、查询转出账户
                Account sourceaccount = accountDao.findAccountByName(sourceName);
                // 2.2、查询转入账户
                Account targetaccount = accountDao.findAccountByName(targetName);
                // 2.3、转出账户减钱
                sourceaccount.setMoney(sourceaccount.getMoney() - money);
                // 2.4、转入账户加钱
                targetaccount.setMoney(targetaccount.getMoney() + money);
                // 2.5、更新转出账户
                accountDao.updateAccount(sourceaccount);
                int i=1/0;
                // 2.6、更新转入账户
                accountDao.updateAccount(targetaccount);
            // 3、提交事务
            txManager.commit();
            // 4、返回结果
        }catch (Exception e) {
            // 5、回滚操作
            txManager.rollback();
            e.printStackTrace();
        }finally {
            // 6、释放连接
            txManager.release();
        }

    }

}
