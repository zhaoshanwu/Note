package com.sw.service.impl;

import com.sw.service.AccountService;

public class AccountServiceImpl implements AccountService {

//    private AccountDao accountDao = new AccountDaoImpl();
//    @Override
//    public void saveAccount() {
//        accountDao.saveAccount();
//    }
    public AccountServiceImpl() {
        System.out.println("对象创建了");
    }

    public void saveAccount() {
        System.out.println("service中的saveAccount方法执行了");
    }

    public void init() {
        System.out.println("对象初始化");
    }
    public void destroy() {
        System.out.println("对象销毁了");
    }
}
