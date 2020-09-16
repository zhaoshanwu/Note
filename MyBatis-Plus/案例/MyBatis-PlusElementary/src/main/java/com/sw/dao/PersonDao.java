package com.sw.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sw.entity.Person;

/**
 * Mapper接口
 *
 * 基于MyBatis：
 *      在Mapper接口中编写CRUD相关的方法，提供Mapper接口所对应的SQL映射文件以及方法对应的SQL语句
 * 基于MyBatis-Plus：
 *      让Mapper接口继承BaseMapper接口即可
 *      BaseMapper<T> : 泛型指定的就是当前Mapper接口所操作的实体类类型
 */
public interface PersonDao extends BaseMapper<Person> {

}
