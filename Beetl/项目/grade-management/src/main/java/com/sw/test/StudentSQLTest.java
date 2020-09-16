package com.sw.test;

import com.sw.entity.Student;
import com.sw.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:springconfig.xml")
public class StudentSQLTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void testfindAll() {
        List<Student> students = studentService.findAll();
        for(Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void testfindById() {
        Student student = studentService.findById("001");
        System.out.println(student);
    }

    @Test
    public void testfindIdAll() {
        List<String> ids = studentService.findIdAll();
        for(String id : ids) {
            System.out.println(id);
        }
    }

    @Test
    public void testNameById() {
        String s_name = studentService.NameById("001");
        System.out.println(s_name);
    }
    @Test
    public void testfindByName() {
        List<Student> students = studentService.findByName("李四");
        for(Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void testcountBy() {
        Integer number = studentService.countBy();
        System.out.println(number);
    }

    @Test
    public void testinsert() {
        Student student = new Student();
        student.setS_id("009");
        student.setS_name("辰龙");
        student.setS_sex("男");
        student.setS_age(18);
        studentService.insertStudent(student);
    }

    @Test
    public void testupdate() {
        Student student = new Student();
        student.setS_id("009");
        student.setS_name("辰龙");
        student.setS_sex("男");
        student.setS_age(20);
        studentService.updateStudent(student);
    }

    @Test
    public void testdelete() {
        studentService.deleteStudent("009");
    }
}
