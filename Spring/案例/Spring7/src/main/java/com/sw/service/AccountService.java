package com.sw.service;

import com.sw.entity.Account;

import java.util.List;

/**
 * 账户的业务层接口
 */
public interface AccountService {
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

    // 转帐
    void transfer(String sourceName, String targetName, Float money);
}
