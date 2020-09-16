package com.sw.dao;

import com.sw.entity.Sc;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScDao {

    // 查询课程最高成绩
    public Integer ByCourseMax(String c_id);
    // 查询课程最小成绩
    public Integer ByCourseMin(String c_id);
    // 查询课程平均分
    public double ByCourseAvg(String c_id);
    // 查询学生成绩信息
    public List<Sc> ByGrade(String s_id);
    // 查询学生课程排名
    public Integer countRank(String s_id, String c_id);
    // 查询学生成绩与该课程平均分差值
    public double ByGradeMinus(String s_id,String c_id);
    // 添加成绩
    public void insertGrade(Sc sc);
    // 更新成绩
    public void updateGrade(Sc sc);
    // 删除成绩
    public void deleteGrade(String s_id);
}
