package com.sw.dao;

import com.sw.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentDao {

    public Student findById(String s_id);

    public List<Student> findAll();

    public void insert(Student student);

    public void delete(String s_id);
}
