package com.sw.controller;

import com.sw.entity.Sc;
import com.sw.service.CourseService;
import com.sw.service.Method;
import com.sw.service.ScService;
import com.sw.service.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sc")
public class ScHandler {

    private static final Logger logger = Logger.getLogger(StudentHandler.class);

    @Autowired
    private StudentService studentService;
    @Autowired
    private ScService scService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private Method method;

    /**
     * 根据学号查询学生成绩
     */
    @RequestMapping(value = "/findGrade",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String,Object> findGrade(String s_id) {
        Map<String,Object> map = new HashMap<String,Object>();
        List<Sc> gradelist = scService.ByGrade(s_id);
        try {
            if(method.ExistSid(s_id)) {
                Integer[] grade = new Integer[4];
                int i = 0;
                String[] c_id = new String[4];
                int j = 0;
                for(Sc sc:gradelist) {
                    grade[i] = sc.getGrade();
                    i += 1;
                    c_id[j] = sc.getC_id();
                    j += 1;
                }

                String s_name = studentService.NameById(s_id);

                String[] c_name = new String[4];
                c_name[0] = courseService.NameById(c_id[0]);
                c_name[1] = courseService.NameById(c_id[1]);
                c_name[2] = courseService.NameById(c_id[2]);
                c_name[3] = courseService.NameById(c_id[3]);
                map.put("s_name", s_name);
                map.put("grade", grade);
                map.put("c_id", c_id);
                map.put("c_name", c_name);
            } else {
                map.put("s_name", null);
                map.put("grade", null);
                map.put("c_id", null);
                map.put("c_name", null);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e);
        }
        return map;
    }

    /**
     * 查询课程的最大成绩、最小成绩和平均分
     */
    @RequestMapping(value = "/findMaxMin",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String,Object> findMaxMin(String c_id) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            if(method.ExistCid(c_id)) {
                int max,min;
                double average;
                String c_name = null;
                c_name = courseService.NameById(c_id);
                max = scService.ByCourseMax(c_id);
                min = scService.ByCourseMin(c_id);
                average = scService.ByCourseAvg(c_id);

                map.put("c_name", c_name);
                map.put("max", max);
                map.put("min", min);
                map.put("average", average);
            } else {
                map.put("c_name", null);
                map.put("max", null);
                map.put("min", null);
                map.put("average", null);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e);
        }
        return map;
    }

    /**
     * 查询学生的最好排名和最差排名
     */
    @RequestMapping(value = "/findRanking",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String,Object> Ranking(String s_id) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            if(method.ExistSid(s_id)) {
                int rank101,rank102,rank103,rank104;
                int rank[][] = new int[4][2];
                rank101 = scService.countRank(s_id,"101");
                rank102 = scService.countRank(s_id,"102");
                rank103 = scService.countRank(s_id,"103");
                rank104 = scService.countRank(s_id,"104");

                rank[0][0] = rank101;
                rank[0][1] = 101;
                rank[1][0] = rank102;
                rank[1][1] = 102;
                rank[2][0] = rank103;
                rank[2][1] = 103;
                rank[3][0] = rank104;
                rank[3][1] = 104;

                int temp0,temp1;
                for(int i=0;i<3;i++){
                    for(int j=0;j<3;j++) {
                        if(rank[j][0]>rank[j+1][0]) {
                            temp0=rank[j][0];
                            temp1=rank[j][1];
                            rank[j][0] = rank[j+1][0];
                            rank[j][1] = rank[j+1][1];
                            rank[j+1][0] = temp0;
                            rank[j+1][1] = temp1;
                        }
                    }
                }

                map.put("max", rank[0][0]);
                map.put("min", rank[3][0]);
                map.put("maxC_name", method.GetCname(rank[0][1]));
                map.put("minC_name", method.GetCname(rank[3][1]));
            } else {
                map.put("max", null);
                map.put("min", null);
                map.put("maxC_name", null);
                map.put("minC_name", null);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e);
        }
        return map;
    }


    @RequestMapping(value = "/findMinus",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String,Object> findMinus(String s_id,String c_id) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            if(method.ExistSid(s_id) && method.ExistCid(c_id)) {
                double minus = scService.ByGradeMinus(s_id,c_id);
                String c_name = courseService.NameById(c_id);

                map.put("minus", minus);
                map.put("c_name", c_name);
            } else {
                map.put("minus", null);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e);
        }
        return map;
    }

    /**
     * 录入学生成绩
     * 如果录入的时候没有该学生信息则添加学生信息后再录入
     */
    @RequestMapping(value = "/InsertGrade",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public void InsertGrade(String s_id,String s_name,String s_sex,Integer s_age,Integer java,Integer c2,Integer c,Integer python) {
        Sc sc1 = new Sc();
        Sc sc2 = new Sc();
        Sc sc3 = new Sc();
        Sc sc4 = new Sc();

        sc1.setS_id(s_id);
        sc2.setS_id(s_id);
        sc3.setS_id(s_id);
        sc4.setS_id(s_id);

        sc1.setC_id("101");
        sc2.setC_id("102");
        sc3.setC_id("103");
        sc4.setC_id("104");

        sc1.setGrade(java);
        sc2.setGrade(c2);
        sc3.setGrade(c);
        sc4.setGrade(python);

        if (method.ExistSid(s_id)) { // 如果输入的学号存在于数据库，则对该学生进行成绩录入
            try {
                scService.insertGrade(sc1);
                scService.insertGrade(sc2);
                scService.insertGrade(sc3);
                scService.insertGrade(sc4);
            } catch (Exception e) {
                logger.error(e);
            }
        } else { // 如果不存在，则视为新学生，对学生信息及成绩进行录入
            try {
                scService.insertSG(s_id,s_name,s_sex,s_age,java,c2,c,python);
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }

    // 维护学生成绩
    @RequestMapping(value = "/UpdateGrade",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public void UpdateGrade(String s_id,Integer java,Integer c2,Integer c,Integer python) {
        try {
            if(method.ExistSid(s_id)) {
                Sc sc1 = new Sc();
                Sc sc2 = new Sc();
                Sc sc3 = new Sc();
                Sc sc4 = new Sc();

                sc1.setS_id(s_id);
                sc2.setS_id(s_id);
                sc3.setS_id(s_id);
                sc4.setS_id(s_id);

                sc1.setC_id("101");
                sc2.setC_id("102");
                sc3.setC_id("103");
                sc4.setC_id("104");

                sc1.setGrade(java);
                sc2.setGrade(c2);
                sc3.setGrade(c);
                sc4.setGrade(python);

                if(java != -1) {
                    scService.updateGrade(sc1);
                }
                if(c2 != -1) {
                    scService.updateGrade(sc2);
                }
                if(c != -1) {
                    scService.updateGrade(sc3);
                }
                if(python != -1) {
                    scService.updateGrade(sc4);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e);
        }
    }
}
