package com.sw.utils;

/**
 * 和事务管理相关的工具类，包含了开启事务，提交事务，回滚事务和释放连接
 */
public class TransactionManager {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启事务
     */
    public void beginTransaction() {
        try{
            connectionUtils.getThreadConnnection().setAutoCommit(false);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    public void commit() {
        try{
            connectionUtils.getThreadConnnection().commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    public void rollback() {
        try{
            connectionUtils.getThreadConnnection().rollback();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放连接
     */
    public void release() {
        try{
            connectionUtils.getThreadConnnection().close(); // 还给连接池
            connectionUtils.removeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
