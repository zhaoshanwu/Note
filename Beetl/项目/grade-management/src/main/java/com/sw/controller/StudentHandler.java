package com.sw.controller;

import com.sw.entity.Student;
import com.sw.service.Method;
import com.sw.service.ScService;
import com.sw.service.StudentService;
import org.apache.log4j.Logger;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    private String MainPath = "http://localhost:8080/gm";
    private Integer active;

    /**
     * 查询所有学生的信息
     */
    @RequestMapping(value = "/findAll")
    public ModelAndView findAll() {
        ModelAndView view = new ModelAndView("/template/test.btl");
        try {
            List<Student> students = studentService.findAll();

            view.addObject("students",students);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        active = 0;
        view.addObject("active",active);
        view.addObject("MainPath",MainPath);

        view.addObject("student",null);

        view.addObject("s_name", null);
        view.addObject("grade", null);
        view.addObject("c_id", null);
        view.addObject("c_name", null);

        view.addObject("c_name2", null);
        view.addObject("max", null);
        view.addObject("min", null);
        view.addObject("average", null);

        view.addObject("maxRanking", null);
        view.addObject("minRanking", null);
        view.addObject("maxC_name", null);
        view.addObject("minC_name", null);

        view.addObject("c_name3", null);
        view.addObject("minus", null);
        return view;
    }

    /**
     * 根据id查询学生
     */
    @RequestMapping(value = "/findById")
    public ModelAndView findById(String s_id) {
        ModelAndView view = new ModelAndView("/template/test.btl");
        try {
            Student student = studentService.findById(s_id);
            if(method.ExistSid(s_id)) {
                view.addObject("student",student);
            } else {
                view.addObject("student",null);
            }
        } catch(Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        active = 1;
        view.addObject("active",active);
        view.addObject("MainPath",MainPath);

        view.addObject("students",null);

        view.addObject("s_name", null);
        view.addObject("grade", null);
        view.addObject("c_id", null);
        view.addObject("c_name", null);

        view.addObject("c_name2", null);
        view.addObject("max", null);
        view.addObject("min", null);
        view.addObject("average", null);

        view.addObject("maxRanking", null);
        view.addObject("minRanking", null);
        view.addObject("maxC_name", null);
        view.addObject("minC_name", null);

        view.addObject("c_name3", null);
        view.addObject("minus", null);
        return view;
    }

    /**
     * 添加学生信息
     */
    @RequestMapping(value = "/InsertStudent")
    @ResponseBody
    public ModelAndView InsertStudent(String s_id,String s_name,String s_sex,int s_age) {
        ModelAndView view = new ModelAndView("/template/test.btl");
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
        active = 6;
        view.addObject("active",active);
        view.addObject("MainPath",MainPath);

        view.addObject("student",null);

        view.addObject("students",null);

        view.addObject("s_name", null);
        view.addObject("grade", null);
        view.addObject("c_id", null);
        view.addObject("c_name", null);

        view.addObject("c_name2", null);
        view.addObject("max", null);
        view.addObject("min", null);
        view.addObject("average", null);

        view.addObject("maxRanking", null);
        view.addObject("minRanking", null);
        view.addObject("maxC_name", null);
        view.addObject("minC_name", null);

        view.addObject("c_name3", null);
        view.addObject("minus", null);
        return view;
    }

    /**
     * 删除该学生的信息及成绩
     */
    @RequestMapping("/delete/{s_id}")
    public ModelAndView delete(@PathVariable String s_id) {
        ModelAndView view = new ModelAndView("/template/test.btl");
        try {
            studentService.deleteStudent(s_id);
        } catch(Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        active = 0;
        view.addObject("active",active);
        view.addObject("MainPath",MainPath);

        view.addObject("students",null);

        view.addObject("s_name", null);
        view.addObject("grade", null);
        view.addObject("c_id", null);
        view.addObject("c_name", null);

        view.addObject("c_name2", null);
        view.addObject("max", null);
        view.addObject("min", null);
        view.addObject("average", null);

        view.addObject("maxRanking", null);
        view.addObject("minRanking", null);
        view.addObject("maxC_name", null);
        view.addObject("minC_name", null);

        view.addObject("c_name3", null);
        view.addObject("minus", null);
        return view;
    }
}
