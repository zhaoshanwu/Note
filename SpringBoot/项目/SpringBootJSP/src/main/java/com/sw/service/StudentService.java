package com.sw.service;

import com.sw.entity.Student;

import java.util.List;

public interface StudentService {
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
    public Integer countBy();
    //添加学生
    public void insertStudent(Student student);
    //修改学生
    public void updateStudent(Student student);
    //删除学生
    public void deleteStudent(String s_id);
}
