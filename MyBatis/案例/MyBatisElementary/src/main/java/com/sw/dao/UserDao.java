package com.sw.dao;

import com.sw.entity.User;

import java.util.List;

public interface UserDao {
    /**
     * 查询所有信息
     */
    List<User> findAll();

    /**
     * 添加用户信息
     */
    void saveUser(User user);

    /**
     * 更新用户
     */
    void updateUser(User user);

    /**
     * 删除用户
     */
    void deleteUser(Integer id);

    /**
     * 根据id查询用户信息
     */
    User findById(Integer userId);

    /**
     * 根据名称模糊查询用户信息
     */
    List<User> findByName(String username);

    /**
     * 查询总用户数
     */
    int findTotal();

    /**
     * 根据queryVo中的条件查询用户
     */
//    List<User> findUserByVo(QueryVo vo);
}
