package com.sw.dao.impl;

import com.sw.dao.AccountDao;

public class AccountDaoImpl implements AccountDao {

    @Override
    public void saveAccount() {
        System.out.println("保存了账户");
    }
}
