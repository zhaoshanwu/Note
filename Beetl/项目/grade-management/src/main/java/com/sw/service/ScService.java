package com.sw.service;

import com.sw.entity.Sc;

import java.util.List;

public interface ScService {
    // 查询课程最高成绩
    public Integer ByCourseMax(String c_id);
    // 查询课程最小成绩
    public Integer ByCourseMin(String c_id);
    // 查询课程平均分
    public Double ByCourseAvg(String c_id);
    // 查询学生成绩信息
    public List<Sc> ByGrade(String s_id);
    // 查询学生课程排名
    public Integer countRank(String s_id, String c_id);
    // 查询学生成绩与该课程平均分差值
    public Double ByGradeMinus(String s_id,String c_id);
    // 添加成绩
    public void insertGrade(Sc sc);
    // 更新成绩
    public void updateGrade(Sc sc);
    // 同时插入学生和成绩信息
    public void insertSG(String s_id,String s_name,String s_sex,Integer s_age,Integer java,Integer c2,Integer c,Integer python);
}
