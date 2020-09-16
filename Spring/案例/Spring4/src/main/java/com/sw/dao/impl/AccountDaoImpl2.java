package com.sw.dao.impl;

import com.sw.dao.AccountDao;
import org.springframework.stereotype.Repository;

@Repository("accountDao2")
public class AccountDaoImpl2 implements AccountDao {

    @Override
    public void saveAccount() {
        System.out.println("保存了账户22222");
    }
}
