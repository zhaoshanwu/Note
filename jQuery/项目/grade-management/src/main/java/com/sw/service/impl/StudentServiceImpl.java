package com.sw.service.impl;

import com.sw.dao.ScDao;
import com.sw.dao.StudentDao;
import com.sw.entity.Student;
import com.sw.service.ScService;
import com.sw.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ScDao scDao;


    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public Student findById(String s_id) {
        return studentDao.findById(s_id);
    }

    @Override
    public List<String> findIdAll() {
        return studentDao.findIdAll();
    }

    @Override
    public String NameById(String s_id) {
        return studentDao.NameById(s_id);
    }

    @Override
    public List<Student> findByName(String s_name) {
        return studentDao.findByName(s_name);
    }

    @Override
    public Integer countBy() {
        return studentDao.countBy();
    }

    @Override
    public void insertStudent(Student student) {
        studentDao.insertStudent(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

    @Override
    public void deleteStudent(String s_id) {
        scDao.deleteGrade(s_id);
        studentDao.deleteStudent(s_id);
    }
}
