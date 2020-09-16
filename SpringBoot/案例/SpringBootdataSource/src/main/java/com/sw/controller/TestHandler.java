package com.sw.controller;

import com.sw.entity.Student;
import com.sw.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestHandler {

    @Autowired
    private StudentDao studentDao;

    @RequestMapping("/Id")
    public Student selectById() {
        Student student = studentDao.findById("001");
        return student;
    }
    @RequestMapping("/All")
    public List<Student> selectfindAll() {
        List<Student> students = studentDao.findAll();
        return students;
    }
    @RequestMapping("/insert")
    public void insert() {
        Student student = new Student("011","露露","男",18);
        studentDao.insert(student);
    }
    @RequestMapping("/delete")
    public void delete() {
        studentDao.delete("011");
    }
}
