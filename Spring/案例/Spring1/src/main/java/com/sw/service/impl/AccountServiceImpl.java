package com.sw.service.impl;

import com.sw.dao.AccountDao;
import com.sw.factory.BeanFactory;
import com.sw.service.AccountService;

public class AccountServiceImpl implements AccountService {

//    private AccountDao accountDao = new AccountDaoImpl();
    private AccountDao accountDao = (AccountDao) BeanFactory.getBean("accountDao");
    @Override
    public void saveAccount() {
        accountDao.saveAccount();
    }
}
