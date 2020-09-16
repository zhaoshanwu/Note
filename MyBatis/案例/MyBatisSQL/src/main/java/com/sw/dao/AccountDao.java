package com.sw.dao;

import com.sw.entity.Account;
import com.sw.entity.AccountUser;

import java.util.List;

public interface AccountDao {
    /**
     * 查询所有信息，同时要获取到用户下所有账户的信息
     */
    List<Account> findAll();

    /**
     * 查询所有账户，并带有用户信息
     */
    List<AccountUser> findAllAccount();
}
