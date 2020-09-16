package com.sw.utils;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 连接的工具类，用于在数据源中获取一个链接，并且实现和线程的绑定
 */
public class ConnectionUtils {
    private ThreadLocal<Connection> t1 = new ThreadLocal<Connection>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的连接
     */
    public Connection getThreadConnnection() {
        // 1、先从ThreadLocal上获取
        Connection conn = t1.get();
        try {
            // 2、判断当前线程上是否有连接
            if (conn == null) {
                // 3、从数据源中获取一个连接，放到ThreadLocal中
                conn = dataSource.getConnection();
                t1.set(conn);
            }
            // 4、返回当前线程上的连接
            return conn;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *把连接和线程解绑
     */
    public void removeConnection() {
        t1.remove();
    }
}
