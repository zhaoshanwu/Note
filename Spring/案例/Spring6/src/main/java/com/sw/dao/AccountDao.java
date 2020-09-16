package com.sw.dao;

import com.sw.entity.Account;

import java.util.List;

public interface AccountDao {
    // 查询所有
    List<Account> findAllAccount();

    // 按id查询
    Account findAccountById(Integer accountId);

    // 添加
    void saveAccount(Account account);

    // 更新
    void updateAccount(Account account);

    // 删除
    void deleteAccount(Integer accountId);
}
