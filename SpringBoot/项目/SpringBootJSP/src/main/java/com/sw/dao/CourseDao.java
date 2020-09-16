package com.sw.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseDao {
    // 根据课程号查询课程名
    public String NameById(String c_id);

    // 查询所有课程号
    public List<String> findByIdAll();

    // 查询课程数
    public Integer countBy();

}
