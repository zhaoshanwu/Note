package com.sw.dao.impl;

import com.sw.dao.AccountDao;
import org.springframework.stereotype.Repository;

@Repository("accountDao1")
public class AccountDaoImpl implements AccountDao {

    @Override
    public void saveAccount() {
        System.out.println("保存了账户1111");
    }
}
