package com.sw.dao;

import com.sw.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    /**
     * 查询所有信息
     */
    List<User> findAll();

    /**
     * 字符串替换
     */
    List<User> find(@Param("column") String column, @Param("value") String value);

    /**
     * 根据id查询用户信息
     */
    User findById(Integer userId);

    List<User> findByUser(User user);

    List<User> findByUser2(User user);

    List<User> findByUser3(User user);

    List<User> findByUser4(User user);

    void update(User user);
}
