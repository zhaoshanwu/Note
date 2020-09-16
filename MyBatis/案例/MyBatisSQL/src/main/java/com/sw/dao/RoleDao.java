package com.sw.dao;

import com.sw.entity.Role;

import java.util.List;

public interface RoleDao {
    /**
     * 查询所有角色
     */
    List<Role> findAll();
}
