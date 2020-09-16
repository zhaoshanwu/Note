package com.sw.service;

import java.util.List;

public interface CourseService {
    // 根据课程号查询课程名
    public String NameById(String c_id);

    // 查询所有课程号
    public List<String> findByIdAll();

    // 查询课程数
    public Integer countBy();
}
