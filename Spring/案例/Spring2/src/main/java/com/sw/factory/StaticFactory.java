package com.sw.factory;

import com.sw.service.AccountService;
import com.sw.service.impl.AccountServiceImpl;

/**
 * 用该类模拟一个工厂类(该类可能存在于jar包内，无法通过修改源码的方式来提供默认构造函数)
 */
public class StaticFactory {
    public static AccountService getAccountService() {
        return new AccountServiceImpl();
    }
}
