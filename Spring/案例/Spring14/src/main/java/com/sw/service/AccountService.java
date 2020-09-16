package com.sw.service;

import com.sw.entity.Account;

/**
 * 账户的业务层接口
 */
public interface AccountService {
    /**
     * 根据id查询账户
     * @param accountId
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 转帐
     * @param sourceName
     * @param targetName
     * @param money
     */
    void transfer(String sourceName, String targetName, Float money);
}
