package com.sw.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sw.entity.Student;
import com.sw.service.Method;
import com.sw.service.ScService;
import com.sw.service.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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

    /**
     * 查询所有学生的信息
     */
    @RequestMapping(value = "/findAll",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String,Object> findAll() {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            List<Student> students = studentService.findAll();
            map.put("students",students);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return map;
    }

    /**
     * 根据id查询学生
     */
    @RequestMapping(value = "/findById",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String,Object> findById(String s_id) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            Student student = studentService.findById(s_id);
            if(method.ExistSid(s_id)) {
                map.put("student",student);
            } else {
                map.put("student",null);
            }
        } catch(Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return map;
    }

    /**
     * 添加学生信息
     */
    @RequestMapping(value = "/InsertStudent",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public void InsertStudent(String s_id,String s_name,String s_sex,int s_age) {
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
    }

    /**
     * 删除该学生的信息及成绩
     */
    @RequestMapping("/delete/{s_id}")
    public String delete(@PathVariable String s_id) {
        try {
            studentService.deleteStudent(s_id);
        } catch(Exception e) {
            e.printStackTrace();
            logger.error(e);
        }
        return "redirect:/index.html";
    }
}
