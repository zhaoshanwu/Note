package com.sw.dao;

import com.sw.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentDao {
    //查询所有学生信息
    public List<Student> findAll();
    //根据学号查询学生信息
    public Student findById(String s_id);
    // 查询学生学号
    public List<String> findIdAll();
    //根据学号查询学生姓名
    public String NameById(String s_id);
    //根据姓名查询学生信息
    public List<Student> findByName(String s_name);
    //查询学生数量
    public int countBy();
    //添加学生
    public int insertStudent(Student student);
    //修改学生
    public int updateStudent(Student student);
    //删除学生
    public int deleteStudent(String s_id);
}
