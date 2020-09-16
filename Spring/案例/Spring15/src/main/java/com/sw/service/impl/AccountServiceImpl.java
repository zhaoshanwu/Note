package com.sw.service.impl;

import com.sw.dao.AccountDao;
import com.sw.entity.Account;
import com.sw.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)  // 这是只读型事务的配置
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public Account findAccountById(Integer accountId) {
        Account account =  accountDao.findAccountById(accountId);
        return account;
    }

    // 需要的是读写型事务配置，所以需要单独再次配置
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    @Override
    public void transfer(String sourceName,String targetName, Float money) {
        System.out.println("开始执行----");
        // 1、查询转出账户
        Account sourceaccount = accountDao.findAccountByName(sourceName);
        // 2、查询转入账户
        Account targetaccount = accountDao.findAccountByName(targetName);
        // 3、转出账户减钱
        sourceaccount.setMoney(sourceaccount.getMoney() - money);
        // 4、转入账户加钱
        targetaccount.setMoney(targetaccount.getMoney() + money);
        // 5、更新转出账户
        accountDao.updateAccount(sourceaccount);
        int i=1/0;
        // 6、更新转入账户
        accountDao.updateAccount(targetaccount);
    }

}
