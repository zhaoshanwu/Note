package com.sw.controller;

import com.sw.entity.Student;
import com.sw.service.Method;
import com.sw.service.ScService;
import com.sw.service.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/student")
public class StudentHandler {

    private static final Logger logger = Logger.getLogger(StudentHandler.class);

    @Autowired
    private StudentService studentService;
    @Autowired
    private ScService scService;
    @Autowired
    private Method method;

    /**
     * 查询所有学生的信息
     * @param session
     * @param active
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(HttpSession session, int active){
        try {
            List<Student> students = studentService.findAll();
            session.setAttribute("students", students);
        } catch(Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        session.setAttribute("active", active);
        return "redirect:/gather";
    }

    /**
     * 根据id查询学生
     * @param s_id
     * @param active
     * @param session
     * @return
     */
    @RequestMapping("/findById")
    public String findById(String s_id, int active, HttpSession session) {
        try {
            Student student = studentService.findById(s_id);
            if(method.ExistSid(s_id)) {
                session.setAttribute("student",student);
            } else {
                session.setAttribute("student",null);
            }
        } catch(Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        session.setAttribute("active", active);
        return "redirect:/gather";
    }

    /**
     * 查询学生个数
     * @param active
     * @param session
     * @return
     */
    @RequestMapping("/countBy")
    public String countBy(int active, HttpSession session) {
        try {
            int num = studentService.countBy();
            session.setAttribute("num",num);
        } catch(Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        session.setAttribute("active", active);
        return "redirect:/gather";
    }

    /**
     * 添加学生信息
     * @param s_id
     * @param s_name
     * @param s_sex
     * @param s_age
     * @param active
     * @param session
     * @return
     */
    @RequestMapping("/InsertStudent")
    public String InsertStudent(String s_id, String s_name, String s_sex, int s_age, String active, HttpSession session) {
        try {
            Student student = new Student();

            student.setS_id(s_id);
            student.setS_name(s_name);
            student.setS_sex(s_sex);
            student.setS_age(s_age);

            studentService.insertStudent(student);
        } catch(Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        session.setAttribute("active", active);
        return "redirect:/gather";
    }

    /**
     * 删除该学生的信息及成绩
     * @param s_id
     * @return
     */
    @RequestMapping("/delete/{s_id}")
    public String delete(@PathVariable String s_id) {
        try {
            studentService.deleteStudent(s_id);
        } catch(Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return "redirect:/gather";
    }
}
